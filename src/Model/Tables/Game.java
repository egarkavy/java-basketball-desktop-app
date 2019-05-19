package Model.Tables;

import java.util.Date;

/**
 * Created by super on 5/19/2019.
 */
public class Game {
    private int Id;
    private int FirstTeamId;
    private String FirstTeamName;
    private int SecondTeamId;
    private String SecondTeamName;
    private int WinnerId;
    private String WinnerName;
    private Date DateOfGame;
    private int FirstTeamScore;
    private int SecondTeamScore;

    public Game() {}

    public Game(int firstTeamId, int secondTeamId, int winnerId, Date dateOfGame, int firstTeamScore, int secondTeamScore) {
        FirstTeamId = firstTeamId;
        SecondTeamId = secondTeamId;
        WinnerId = winnerId;
        DateOfGame = dateOfGame;
        FirstTeamScore = firstTeamScore;
        SecondTeamScore = secondTeamScore;
    }

    public String getSecondTeamName() {
        return SecondTeamName;
    }

    public void setSecondTeamName(String secondTeamName) {
        SecondTeamName = secondTeamName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getFirstTeamId() {
        return FirstTeamId;
    }

    public void setFirstTeamId(int firstTeamId) {
        FirstTeamId = firstTeamId;
    }

    public String getFirstTeamName() {
        return FirstTeamName;
    }

    public void setFirstTeamName(String firstTeamName) {
        FirstTeamName = firstTeamName;
    }

    public int getSecondTeamId() {
        return SecondTeamId;
    }

    public void setSecondTeamId(int secondTeamId) {
        SecondTeamId = secondTeamId;
    }

    public int getWinnerId() {
        return WinnerId;
    }

    public void setWinnerId(int winnerId) {
        WinnerId = winnerId;
    }

    public String getWinnerName() {
        return WinnerName;
    }

    public void setWinnerName(String winnerName) {
        WinnerName = winnerName;
    }

    public Date getDateOfGame() {
        return DateOfGame;
    }

    public void setDateOfGame(Date dateOfGame) {
        DateOfGame = dateOfGame;
    }

    public int getFirstTeamScore() {
        return FirstTeamScore;
    }

    public void setFirstTeamScore(int firstTeamScore) {
        FirstTeamScore = firstTeamScore;
    }

    public int getSecondTeamScore() {
        return SecondTeamScore;
    }

    public void setSecondTeamScore(int secondTeamScore) {
        SecondTeamScore = secondTeamScore;
    }
}
