package Model.Tables;

/**
 * Created by super on 5/18/2019.
 */
public class Player {
    private int Id;
    private int TeamId;
    private int CountryId;
    private String Name;
    private String Surname;
    private int Number;
    private String CountryName;
    private String TeamName;

    public Player() {

    }

    public Player(int teamId, int countryId, String name, String surname, int number) {
        TeamId = teamId;
        CountryId = countryId;
        Name = name;
        Surname = surname;
        Number = number;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTeamId() {
        return TeamId;
    }

    public void setTeamId(int teamId) {
        TeamId = teamId;
    }

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

}
