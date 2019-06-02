package Model.Repositories;

import Model.BasketballDriver;
import Model.Tables.News;
import Model.Tables.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by super on 5/5/2019.
 */
public class PlayersRepository {
    private BasketballDriver driver;

    public PlayersRepository() throws SQLException {
        driver = new BasketballDriver();
    }

    public List<Player> Get() throws SQLException {
        String sql = String.format("SELECT p.Id, p.TeamId, p.CountryId, p.Name, p.Surname, p.Number, c.Name, t.TeamName\n" +
                                        "FROM basketball.players p\n" +
                                        "join basketball.countries c on c.Id = p.CountryId\n" +
                                        "join basketball.teams t on t.id = p.teamid");

        ResultSet results = driver.statement.executeQuery(sql);

        List<Player> playerList = new ArrayList<Player>();

        while (results.next()) {
            int id = results.getInt(1);
            int tId = results.getInt(2);
            int cId = results.getInt(3);
            String name = results.getString(4);
            String surname = results.getString(5);
            int number = results.getInt(6);
            String cName = results.getString(7);
            String tName = results.getString(8);

            Player player = new Player();
            player.setId(id);
            player.setTeamId(tId);
            player.setTeamName(tName);
            player.setCountryId(cId);
            player.setCountryName(cName);
            player.setName(name);
            player.setSurname(surname);
            player.setNumber(number);

            playerList.add(player);
        }

        return  playerList;
    }

    public void Save(Player player) throws SQLException {
        String sql = String.format("INSERT into `players` (`TeamId`, `CountryId`, `Name`, `Surname`, `Number`) values (%s, %s, '%s', '%s', %s)", player.getTeamId(), player.getCountryId(), player.getName(), player.getSurname(), player.getNumber());

        int result = driver.statement.executeUpdate(sql);
    }

    public void Delete(int id) throws SQLException {
        String sql = String.format("delete from `players` where id = %s", id);

        int result = driver.statement.executeUpdate(sql);
    }

    public void Update(Player player) throws SQLException {
        String sql = String.format("update basketball.players\n" +
                                    "set Name = '%s', Surname = '%s', TeamId = %s, CountryId = %s, Number = %s\n" +
                                    "where Id = %s", player.getName(), player.getSurname(), player.getTeamId(), player.getCountryId(), player.getNumber(), player.getId());

        int result = driver.statement.executeUpdate(sql);
    }
}
