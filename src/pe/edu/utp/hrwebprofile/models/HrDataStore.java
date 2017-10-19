package pe.edu.utp.hrwebprofile.models;

import java.sql.Connection;
import java.util.List;

public class HrDataStore {
    private Connection connection;
    private RegionsEntity regionsEntity;
    private CountriesEntity countriesEntity;


    public HrDataStore() {
    }

    public HrDataStore(Connection connection) {
        this.connection = connection;
    }

    public Region findRegionById(int id) {
        return connection == null ? null : getRegionsEntity().findById(id);
    }

    public Country findCountryById(String id){
        return connection == null ? null : getCountriesEntity().findById(id,getRegionsEntity());
    }

    public List<Region> findAllRegions(){
        return connection == null ? null : getRegionsEntity().findAll();
    }

    public List<Country> findAllCountries(){
        return connection == null ? null : getCountriesEntity().findAll(getRegionsEntity());
    }

    public boolean eraseRegion(int id){
        return connection != null && getRegionsEntity().erase(id);
    }

    public boolean eraseCountry(String id){
        return connection != null && getCountriesEntity().erase(id);
    }

    public boolean updateRegion(int id,String name){
        return connection != null && getRegionsEntity().update(id,name);
    }

    public boolean updateCountries(String id, String name, int regionId){
        return connection != null && getCountriesEntity().update(id,name,regionId);
    }

    private RegionsEntity getRegionsEntity(){
        if(regionsEntity == null){
            regionsEntity = new RegionsEntity();
            regionsEntity.setConnection(connection);
        }
        return regionsEntity;
    }

    private CountriesEntity getCountriesEntity(){
        if(countriesEntity == null){
            countriesEntity = new CountriesEntity();
            countriesEntity.setConnection(connection);
        }
        return countriesEntity;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
