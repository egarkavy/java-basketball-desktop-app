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
        String sql = String.format("select t.*, c.Name as CountryName from basketball.teams t\n" +
                "join basketball.countries c on c.id = t.countryid ");

        ResultSet results = driver.statement.executeQuery(sql);

        List<Team> teamList = new ArrayList<Team>();

        while (results.next()) {
            int id = results.getInt("Id");
            String text = results.getString("TeamName");
            int countryId = results.getInt("CountryId");
            String countryName = results.getString("CountryName");

            Team team = new Team();
            team.setId(id);
            team.setTeamName(text);
            team.setCountryName(countryName);
            team.setCountryId(countryId);

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
