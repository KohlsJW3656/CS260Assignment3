/**
 * Class Water - HumResource sub-class that holds information on Water table in database
 *
 * Create by Jonas W. Kohls 11 Nov 2019
 */
public class Water extends HumResource {

    /**
     * Water Constructor
     */
    public Water() {
        tableName = "WATER";
    }

    /**
     * Method that inserts parameters into the Water table
     * @param hrid int representing the overall humanitarian resource id number
     * @param numOf10Bottles int representing the number of 10Oz bottles
     * @param numOfHalfLitter int representing the number of half liter bottles
     * @param numOf5Gal int representing the number of 5Gal Jugs
     */
    public void insert(int hrid, int numOf10Bottles, int numOfHalfLitter, int numOf5Gal) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("INSERT INTO " + tableName + " VALUES (" + hrid + ", " + numOf10Bottles +
                ", " + numOfHalfLitter + ", " + numOf5Gal + ")");
        dao.commit();
        dao.disconnect();
    }

    /**
     * Method that updates the data of an HRID by using the parameters given
     * @param hrid int representing the overall humanitarian resource id number
     * @param numOf10Bottles int representing the number of 10Oz bottles
     * @param numOfHalfLitter int representing the number of half liter bottles
     * @param numOf5Gal int representing the number of 5Gal Jugs
     */
    public void update(int hrid, int numOf10Bottles, int numOfHalfLitter, int numOf5Gal) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("UPDATE " + tableName + " SET Num10OzBottlesAvailable = " + numOf10Bottles +
                        ", NumHalfLiterBottlesAvailable = " + numOfHalfLitter + ", Num5GallonJugsAvailable = " +
                numOf5Gal + " WHERE HRID = " + hrid);
        dao.commit();
        dao.disconnect();
    }

    /**
     * Method that calls parent method deletes all data with a specific HRID in
     * HumResource table, and a table in a parameter
     * @param hrid int representing the overall humanitarian resource id number
     */
    public void delete(int hrid) {
        delete(tableName, hrid);
    }

    /**
     * Method that calls parent method that grabs all data with a specific HRID
     * @param hrid int representing the overall humanitarian resource id number to be grabbed
     * @return String array of all data with specific HRID
     */
    public String[] displayData(int hrid) {
        return displayData(tableName, hrid);
    }

    /**
     * Method that calls parent method that grabs all HRIDs from a specific table
     * @return String array of all HRIDs from a specific table
     */
    public String[] displayAllHRIDs() {
        return displayAllHRIDs(tableName);
    }
}
