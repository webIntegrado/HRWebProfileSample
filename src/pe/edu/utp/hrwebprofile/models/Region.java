package pe.edu.utp.hrwebprofile.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Region {
    private int id;
    private String name;

    public Region(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Region() {
    }

    public int getId() {
        return id;
    }

    public Region setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Region setName(String name) {
        this.name = name;
        return this;
    }

    public static Region from(ResultSet rs) {
        try {
            return new Region(
                    rs.getInt("region_id"),
                    rs.getString("region_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }










}
