/**
 * Class MedicalCenter - HumResource sub-class that holds information on MedicalCenter table in database
 *
 * Create by Jonas W. Kohls 11 Nov 2019
 */

public class MedicalCenter extends HumResource {

    //MedicalCenter Constructor
    public MedicalCenter() {
        tableName = "MedicalCenter";
    }

    //Method that inserts parameters into the MedicalCenter table
    public void insert(int hrid, int beds, int roomCap, int doctors, int nurses) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("INSERT INTO " + tableName + " VALUES (" + hrid + ", " + beds + ", " + roomCap
                + "," + doctors + ", " + nurses + ")");
        dao.commit();
        dao.disconnect();
    }

    //Method that updates the data of an HRID by using the parameters given
    public void update(int hrid, int beds, int roomCap, int doctors, int nurses) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("UPDATE " + tableName + " SET NumBeds = " + beds +
                ", EmergencyRoomCapacity = " + roomCap + ", NumDoctors = " + doctors + ", NumNurses = " + nurses +
                " WHERE HRID = " + hrid);
        dao.commit();
        dao.disconnect();
    }

    //Method that calls HumResource delete() method and passes in the table name parameter
    public void delete(int hrid) {
        delete(tableName, hrid);
    }

    //Method that calls HumResource displayHRID() method, passes in the table name parameter,
    // and returns a String array of all data with a specific HRID from a table
    public String[] displayHRID(int hrid) {
        return displayHRID(tableName, hrid);
    }

    //Method that calls HumResource displayAllHRIDs() method, passes in the table name parameter,
    // and returns a string array of all HRIDs in a specific table
    public String[] displayAllHRIDs() {
        return displayAllHRIDs(tableName);
    }
}
