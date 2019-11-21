/**
 * Class Food - HumResource sub-class that holds information on Food table in database
 *
 * Create by Jonas W. Kohls 11 Nov 2019
 */
public class Food extends HumResource {

    /**
     * Food Constructor
     */
    public Food() {
        tableName = "FOOD";
    }

    /**
     * Method that inserts parameters into the Food table
     * @param hrid int representing the overall humanitarian resource id number
     * @param type String representing the type of food
     * @param meals int representing the number of meals available
     * @param desc String representing a further description
     */
    public void insert(int hrid, String type, int meals, String desc) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("INSERT INTO " + tableName + " VALUES (" + hrid + ", '" + type + "', " +
                meals + ", '" + desc + "')");
        dao.commit();
        dao.disconnect();
    }

    /**
     * Method that updates the data of an HRID by using the parameters given
     * @param hrid int representing the overall humanitarian resource id number
     * @param type String representing the type of food
     * @param meals int representing the number of meals available
     * @param desc String representing a further description
     */
    public void update(int hrid, String type, int meals, String desc) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("UPDATE " + tableName + " SET FType = '" + type + "', FMealsAvailable = " +
                meals + ", FSpecificDesc = '" + desc + "' WHERE HRID = " + hrid);
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
