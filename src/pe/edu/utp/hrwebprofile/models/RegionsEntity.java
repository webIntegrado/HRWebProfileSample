package pe.edu.utp.hrwebprofile.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionsEntity extends BaseEntity {

    public RegionsEntity() {
        super();
        setTableName("regions");
    }

    public Region findById(int id) {
        return findByCriteria(
                String.format("WHERE region_id = %d", id)).get(0);
    }

    public List<Region> findByCriteria(String criteria) {
        try {
            ResultSet rs = getConnection()
                    .createStatement()
                    .executeQuery(
                            getBaseStatement()
                            .concat(criteria));
            List<Region> regions = new ArrayList<>();
            while(rs.next())
                regions.add(Region.from(rs));

            return regions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    public Region findByName(String name) {
        return findByCriteria(
                String.format("WHERE region_name = '%s'", name)).get(0);
    }

    public List<Region> findAll() {
        return findByCriteria("");
    }

    public List<Region> findAllWithCountries() {
        return findByCriteria("region_id IN (SELECT DISTINCT region_id FROM countries)");
    }

    public boolean create(Region region) {
        return executeUpdate(String.format(
                "INSERT INTO %s(region_id, region_name) VALUES(%d, '%s')",
                getTableName(), region.getId(), region.getName()));
    }

    public boolean create(int id, String name) {
        return create(new Region(id, name));
    }

    public boolean update(int id, String name) {
        return executeUpdate(String.format(
                "UPDATE %s SET region_name = '%s' WHERE region_id = %d", getTableName(), name, id));
    }

    public boolean update(Region region) {
        return update(region.getId(), region.getName());
    }

    public boolean erase(int id) {
        return executeUpdate(String.format("DELETE FROM %s WHERE region_id = %d",
                getTableName(), id));
    }

    public boolean erase(Region region) {
        return executeUpdate(String.format("DELETE FROM %s WHERE region_id = %d",
                getTableName(), region.getId()));
    }

}






