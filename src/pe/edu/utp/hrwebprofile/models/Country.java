package pe.edu.utp.hrwebprofile.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Country {
    private String id;
    private String name;
    private Region region;

    public Country(String id, String name, Region region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }

    public Country() {
    }

    public String getId() {
        return id;
    }

    public Country setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }

    public Region getRegion() {
        return region;
    }

    public Country setRegion(Region region) {
        this.region = region;
        return this;
    }

    public static Country from(ResultSet rs, RegionsEntity regionsEntity) {
        Country country = new Country();
        try {
            return country.setId(rs.getString("country_id"))
                    .setName(rs.getString("country_name"))
                    .setRegion(regionsEntity.findById(rs.getInt("region_id")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
