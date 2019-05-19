package Model.Repositories;

import Model.BasketballDriver;
import Model.Tables.Game;
import Model.Tables.Player;
import Model.Tables.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by super on 5/5/2019.
 */
public class GamesRepository {
    private BasketballDriver driver;

    public GamesRepository() throws SQLException {
        driver = new BasketballDriver();
    }

    public List<Game> Get() throws SQLException {
        String sql = String.format("SELECT g.*, t1.TeamName, t2.TeamName FROM basketball.games g\n" +
                                        "join basketball.teams t1 on t1.id = g.FirstTeamId\n" +
                                        "join basketball.teams t2 on t2.id = g.SecondTeamId");

        ResultSet results = driver.statement.executeQuery(sql);

        List<Game> gameList = new ArrayList<Game>();

        while (results.next()) {
            int id = results.getInt(1);
            int fId = results.getInt(2);
            int sId = results.getInt(3);
            int winner = results.getInt(4);
            int fScope = results.getInt(5);
            int sScope = results.getInt(6);
            Date date = results.getDate(7);
            String fName = results.getString(8);
            String sName = results.getString(9);

            Game game = new Game();
            game.setId(id);
            game.setFirstTeamId(fId);
            game.setFirstTeamName(fName);
            game.setFirstTeamScore(fScope);
            game.setSecondTeamId(sId);
            game.setSecondTeamName(sName);
            game.setSecondTeamScore(sScope);
            game.setWinnerId(winner);

            String winnerName = winner == 1 ? fName : sName;

            game.setWinnerName(winnerName);
            game.setDateOfGame(date);

            gameList.add(game);
        }

        return gameList;
    }

    public void Save(Game game) throws SQLException {
        String sql = String.format("INSERT INTO `basketball`.`games`\n" +
                "(`FirstTeamId`,\n" +
                "`SecondTeamId`,\n" +
                "`Winner`,\n" +
                "`FirstTeamScore`,\n" +
                "`SecondTeamScore`,\n" +
                "`DateOfGame`)\n" +
                "VALUES\n" +
                "(\n" +
                "%s,\n" +
                "%s,\n" +
                "%s,\n" +
                "%s,\n" +
                "%s,\n" +
                "'%s');\n", game.getFirstTeamId(), game.getSecondTeamId(), game.getWinnerId(), game.getFirstTeamScore(), game.getSecondTeamScore(), new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(game.getDateOfGame()));
        //2011-12-18 13:17:17

        int result = driver.statement.executeUpdate(sql);
    }

    public void Delete(int id) throws SQLException {
        String sql = String.format("delete from `games` where id = %s", id);

        int result = driver.statement.executeUpdate(sql);
    }

    public void Update(Game game) throws SQLException {
        String sql = String.format("UPDATE `basketball`.`games`\n" +
                "SET\n" +
                "`FirstTeamId` = %s,\n" +
                "`SecondTeamId` = %s,\n" +
                "`Winner` = %s,\n" +
                "`FirstTeamScore` = %s,\n" +
                "`SecondTeamScore` = %s,\n" +
                "`DateOfGame` = %s\n" +
                "WHERE `Id` = %s;\n", game.getFirstTeamId(), game.getSecondTeamId(), game.getWinnerId(), game.getFirstTeamScore(), game.getSecondTeamScore(), game.getDateOfGame(), game.getId());

        int result = driver.statement.executeUpdate(sql);
    }
}
