package Model.Tables;

/**
 * Created by super on 5/18/2019.
 */
public class Team {
    private int Id;
    private String TeamName;
    private String CountryName;
    private int CountryId;

    public Team() {

    }

    public Team(String teamName, int countryId) {
        TeamName = teamName;
        CountryId = countryId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public int getCountryId() {
        return CountryId;
    }

    public void setCountryId(int countryId) {
        CountryId = countryId;
    }
}
