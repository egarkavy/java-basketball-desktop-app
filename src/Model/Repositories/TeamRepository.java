package Model.Repositories;

import Model.BasketballDriver;
import Model.Tables.Country;
import Model.Tables.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by super on 5/5/2019.
 */
public class TeamRepository {
    private BasketballDriver driver;

    public TeamRepository() throws SQLException {
        driver = new BasketballDriver();
    }

    public List<Team> Get() throws SQLException {
        String sql = String.format("SELECT * from teams");

        ResultSet results = driver.statement.executeQuery(sql);

        List<Team> teamList = new ArrayList<Team>();

        while (results.next()) {
            int id = results.getInt("Id");
            String text = results.getString("TeamName");

            Team team = new Team();
            team.setId(id);
            team.setTeamName(text);

            teamList.add(team);
        }

        return teamList;
    }

    public void Save(Team team) throws SQLException {
        String sql = String.format("INSERT into `teams` (`TeamName` `CountryId`) values ('%s', %s)", team.getTeamName(), team.getCountryId());

        int result = driver.statement.executeUpdate(sql);
    }

    public void Delete(int id) throws SQLException {
        String sql = String.format("delete from `teams` where id = %s", id);

        int result = driver.statement.executeUpdate(sql);
    }

    public void Update(Team team) throws SQLException {
        String sql = String.format("update basketball.teams\n" +
                "set TeamName = '%s', CountryId = %s \n" +
                "where Id = %s", team.getTeamName(), team.getCountryId(), team.getId());

        int result = driver.statement.executeUpdate(sql);
    }

}
