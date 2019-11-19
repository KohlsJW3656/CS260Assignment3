/**
 * Class HumResource - class that holds information on HumResource table in database
 *
 * Create by Jonas W. Kohls 11 Nov 2019
 */

import java.sql.*;

public class HumResource {

    DataAccessObject dao = new DataAccessObject();                  //JDBC Data Access Object used to connect to database and create queries
    String tableName;                                               //String representing the name of the database table
    private ResultSet rs;                                           //JDBC Result Set used to store results from queries
    private int hridProcessed;                                      //int representing an HRID in the database

    //HumResource Constructor
    public HumResource() {
        tableName = "HumResource";
    }

    //Method that inserts parameters into the HumResource table
    public int insert(String name, String address, String phone, Double latitude, Double longitude, String type, String desc, String hours) {
        hridProcessed = createHRID();
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("INSERT INTO " + tableName + " VALUES (" + hridProcessed + ", '" + name + "', '" +
                address + "', '" + phone + "', " + latitude + ", " + longitude + ", '" + type + "', '" + desc + "', '" +
                hours + "')");
        dao.commit();
        dao.disconnect();
        return hridProcessed;
    }

    //Method that updates the data of an HRID by using the parameters given
    public void update(int hrid, String name, String address, String phone, Double latitude, Double longitude, String type, String desc, String hours) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("UPDATE " + tableName + " SET HRName = '" + name + "', HRAddressString = '"
                + address + "', HRPhoneNumber = '" + phone + "', HRLatitude = " + latitude + ", HRLongitude = " +
                longitude + ", HRType = '" + type + "', HRDesc = '" + desc + "', HROpenHoursString = '" + hours +
                "' WHERE HRID = " + hrid);
        dao.commit();
        dao.disconnect();
    }

    //Method that deletes all data with a specific HRID in HumResource table, and a table in a parameter
    public void delete(String tableName, int hrid) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("DELETE FROM " + tableName + " WHERE HRID = " + hrid);
        dao.executeSQLNonQuery("DELETE FROM " + this.tableName + " WHERE HRID = " + hrid);
        dao.commit();
        dao.disconnect();
    }

    //Method that returns a String array of all data with a specific HRID from a table
    public String[] displayHRID(String tableName, int hrid) {
        String[] resultArray;                                       //String array to store processed results from a result set
        dao.connect();
        dao.setAutoCommit(false);
        rs = dao.executeSQLQuery("SELECT * FROM " + tableName + " WHERE HRID = " + hrid);
        resultArray = dao.processUpdateResultSet(rs);
        dao.disconnect();
        return resultArray;
    }

    //Method that grabs the max HRID from HumResource table and returns an int one higher than it
    private int createHRID() {
        String result;                                              //String to store processed results from a result set
        dao.connect();
        dao.setAutoCommit(false);
        rs = dao.executeSQLQuery("Select MAX(HRID) FROM " + tableName);
        result = dao.processResultSet(rs);
        hridProcessed = Integer.parseInt(result);
        dao.disconnect();
        return hridProcessed + 1;
    }
}
