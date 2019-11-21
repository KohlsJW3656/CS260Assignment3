import java.sql.*;
/**
 * Class HumResource - class that holds information on HumResource table in database
 *
 * Create by Jonas W. Kohls 11 Nov 2019
 */
public class HumResource {

    DataAccessObject dao = new DataAccessObject();                  //JDBC Data Access Object used to connect to database and create queries
    String tableName;                                               //String representing the name of the database table
    private ResultSet rs;                                           //JDBC Result Set used to store results from queries
    private int hridProcessed;                                      //int representing an HRID in the database
    private String result;                                          //String to store processed results from a result set

    /**
     * HumResource Constructor
     */
    public HumResource() {
        tableName = "HumResource";
    }

    /**
     * Method that inserts data into the HumResource table
     * @param name String representing name of resource
     * @param address String representing the street address
     * @param phone String representing the phone number
     * @param latitude int representing the resource geographical location latitude
     * @param longitude int representing the resource geographical location longitude
     * @param type String representing the type of resource
     * @param desc String representing a short description of the resource
     * @param hours String representing a list of open hours of the resource
     * @return the HRID created from createHRID() method
     */
    public int insert(String name, String address, String phone, Double latitude, Double longitude, String type, String desc, String hours) {
        hridProcessed = createHRID();
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("INSERT INTO " + tableName + " VALUES (" + hridProcessed + ", '" + name +
                "', '" + address + "', '" + phone + "', " + latitude + ", " + longitude + ", '" + type + "', '" + desc
                + "', '" + hours + "')");
        dao.commit();
        dao.disconnect();
        return hridProcessed;
    }

    /**
     * Method that updates the data of an HRID by using the parameters given
     * @param hrid int representing the overall humanitarian resource id number
     * @param name String representing name of resource
     * @param address String representing the street address
     * @param phone String representing the phone number
     * @param latitude int representing the resource geographical location latitude
     * @param longitude int representing the resource geographical location longitude
     * @param type String representing the type of resource
     * @param desc String representing a short description of the resource
     * @param hours String representing a list of open hours of the resource
     */
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

    /**
     * Method that deletes all data with a specific HRID in HumResource table, and a
     * table in a parameter
     * @param tableName String representing the name of the table to be modified
     * @param hrid int representing the overall humanitarian resource id number to be deleted
     */
    public void delete(String tableName, int hrid) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("DELETE FROM " + tableName + " WHERE HRID = " + hrid);
        dao.executeSQLNonQuery("DELETE FROM HumResource WHERE HRID = " + hrid);
        dao.commit();
        dao.disconnect();
    }

    /**
     * Method that grabs all data with a specific HRID
     * @param tableName String representing the name of a table
     * @param hrid int representing the overall humanitarian resource id number to be grabbed
     * @return String array of all data with specific HRID
     */
    public String[] displayData(String tableName, int hrid) {
        String[] resultArray;                                   //String array to store processed results from a result set
        dao.connect();
        dao.setAutoCommit(false);
        rs = dao.executeSQLQuery("SELECT * FROM " + tableName + " WHERE HRID = " + hrid);
        resultArray = dao.processUpdateResultSet(rs);
        dao.disconnect();
        return resultArray;
    }

    /**
     * Method that grabs all HRIDs from a specific table
     * @param tableName String representing the name of a table
     * @return String array of all HRIDs from a specific table
     */
    public String[] displayAllHRIDs(String tableName) {
        String[] hridArray;                                     //Int array to store processed HRIDs from a result set
        int countHRID;                                          //Int that is the count of all HRIDs
        dao.connect();
        dao.setAutoCommit(false);

        //Count how many HRIDs are in a table
        rs = dao.executeSQLQuery("SELECT COUNT(*) FROM " + tableName);
        result = dao.processResultSet(rs);
        countHRID = Integer.parseInt(result);

        //Put all HRIDs into an int array
        rs = dao.executeSQLQuery("SELECT HRID FROM " + tableName);
        hridArray = dao.processIntResultSet(rs, countHRID);
        dao.disconnect();
        return hridArray;
    }

    /**
     * Private method that creates a new HRID
     * @return int representing a new HRID
     */
    private int createHRID() {
        dao.connect();
        dao.setAutoCommit(false);
        rs = dao.executeSQLQuery("Select MAX(HRID) FROM " + tableName);
        result = dao.processResultSet(rs);
        hridProcessed = Integer.parseInt(result);
        dao.disconnect();
        return hridProcessed + 1;
    }
}
