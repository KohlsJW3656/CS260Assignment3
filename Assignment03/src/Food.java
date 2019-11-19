/**
 * Class Food - HumResource sub-class that holds information on Food table in database
 *
 * Create by Jonas W. Kohls 11 Nov 2019
 */

public class Food extends HumResource {

    //Food Constructor
    public Food() {
        tableName = "FOOD ";
    }

    //Method that inserts parameters into the Food table
    public void insert(int hrid, String type, int meals, String desc) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("INSERT INTO " + tableName + "VALUES (" + hrid + ", '" + type + "', " + meals
                + ", '" + desc + "')");
        dao.commit();
        dao.disconnect();
    }

    //Method that updates the data of an HRID by using the parameters given
    public void update(int hrid, String type, int meals, String desc) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("UPDATE " + tableName + "SET FType = '" + type + "', FMealsAvailable = " +
                meals + ", FSpecificDesc = '" + desc + "' WHERE HRID = " + hrid);
        dao.commit();
        dao.disconnect();
    }

    //Method that calls HumResource delete() method and passes in the table name parameter
    public void delete(int hrid) {
        delete(tableName, hrid);
    }

    //Method that calls HumResource displayHRID() method, passes in the table name parameter and returns a String array of all data with a specific HRID from a table
    public String[] displayHRID(int hrid) {
        return displayHRID(tableName, hrid);
    }
}
