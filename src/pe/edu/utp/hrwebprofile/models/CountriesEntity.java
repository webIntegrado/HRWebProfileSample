package pe.edu.utp.hrwebprofile.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountriesEntity extends BaseEntity{


    public CountriesEntity(){
        super();
        setTableName("countries");
    }

    public CountriesEntity(Connection connection,String tableName){
        super(connection,tableName);
        setTableName("countries");
    }

    public Country findById(String id, RegionsEntity regionsEntity) {
        return findByCriteria(
                String.format("WHERE country_id = '%s'", id),
                regionsEntity).get(0);
    }

    public List<Country> findByCriteria(String criteria, RegionsEntity regionsEntity){
        try {
            ResultSet rs = getConnection()
                    .createStatement()
                    .executeQuery(
                            getBaseStatement()
                                    .concat(criteria));
            List<Country> countries = new ArrayList<>();
            while (rs.next()){
                countries.add(Country.from(rs,regionsEntity));
            }
            return countries;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Country findByName(String name, RegionsEntity regionsEntity) {
        return findByCriteria(
                String.format("WHERE country_name = '%s'", name),
                regionsEntity).get(0);
    }

    public List<Country> findAll(RegionsEntity regionsEntity) {
        return findByCriteria(
                "",
                regionsEntity);
    }

    public boolean create(Country country) {
        return executeUpdate(String.format(
                "INSERT INTO %s(country_id, country_name, region_id) VALUES('%s', '%s', %d)",
                getTableName(), country.getId(), country.getName(),country.getRegion().getId()));
    }

    public boolean create(String id, String name, Region region) {
        return create(new Country(id, name, region));
    }

    public boolean update(String id, String name, int regionId) {
        return executeUpdate(String.format(
                "UPDATE %s SET country_name = '%s', region_id = %d WHERE country_id = '%s'", getTableName(), name,regionId ,id));
    }

    public boolean update(Country country) {
        return update(country.getId(), country.getName(),country.getRegion().getId());
    }

    public boolean erase(String id) {
        return executeUpdate(String.format("DELETE FROM %s WHERE country_id = '%s'",
                getTableName(), id));
    }

    public boolean erase(Country country) {
        return executeUpdate(String.format("DELETE FROM %s WHERE country_id = '%s'",
                getTableName(), country.getId()));
    }
}
