/**
 * Class Water - HumResource sub-class that holds information on Water table in database
 *
 * Create by Jonas W. Kohls 11 Nov 2019
 */

public class Water extends HumResource {

    //Water Constructor
    public Water() {
        tableName = "WATER";
    }

    //Method that inserts parameters into the Water table
    public void insert(int hrid, int numOf10Bottles, int numOfHalfLitter, int numOf5Gal) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("INSERT INTO " + tableName + " VALUES (" + hrid + ", " + numOf10Bottles +
                ", " + numOfHalfLitter + ", " + numOf5Gal + ")");
        dao.commit();
        dao.disconnect();
    }

    //Method that updates the data of an HRID by using the parameters given
    public void update(int hrid, int numOf10Bottles, int numOfHalfLitter, int numOf5Gal) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("UPDATE " + tableName + " SET Num10OzBottlesAvailable = " + numOf10Bottles +
                        ", NumHalfLiterBottlesAvailable = " + numOfHalfLitter + ", Num5GallonJugsAvailable = " +
                numOf5Gal + " WHERE HRID = " + hrid);
        dao.commit();
        dao.disconnect();
    }

    //Method that calls HumResource delete() method and passes in the table name parameter
    public void delete(int hrid) {
        delete(tableName, hrid);
    }

    //Method that calls HumResource displayHRID() method, passes in the table name parameter
    // and returns a String array of all data with a specific HRID from a table
    public String[] displayHRID(int hrid) {
        return displayHRID(tableName, hrid);
    }

    //Method that calls HumResource displayAllHRIDs() method, passes in the table name parameter,
    // and returns String array of all HRIDs in a specific table
    public String[] displayAllHRIDs() {
        return displayAllHRIDs(tableName);
    }
}
