package Model.Repositories;

import Model.BasketballDriver;
import Model.Tables.Country;
import Model.Tables.News;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by super on 5/5/2019.
 */
public class CountryRepository {
    private BasketballDriver driver;

    public CountryRepository() throws SQLException {
        driver = new BasketballDriver();
    }

    public List<Country> Get() throws SQLException {
        String sql = String.format("SELECT * from countries");

        ResultSet results = driver.statement.executeQuery(sql);

        List<Country> countryList = new ArrayList<Country>();

        while (results.next()) {
            int id = results.getInt("Id");
            String text = results.getString("Name");

            Country country = new Country();
            country.setId(id);
            country.setName(text);

            countryList.add(country);
        }

        return  countryList;
    }

}
