/**
 * Class MedicalCenter - HumResource sub-class that holds information on MedicalCenter table in database
 *
 * Create by Jonas W. Kohls 11 Nov 2019
 */
public class MedicalCenter extends HumResource {

    /**
     * HumResource Constructor
     */
    public MedicalCenter() {
        tableName = "MedicalCenter";
    }

    /**
     * Method that inserts parameters into the MedicalCenter table
     * @param hrid int representing the overall humanitarian resource id number
     * @param beds int representing the number of beds available
     * @param roomCap int representing the number of emergency patients that can be treated simultaneously
     * @param doctors int representing the number of doctors
     * @param nurses int representing the number of nurses
     */
    public void insert(int hrid, int beds, int roomCap, int doctors, int nurses) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("INSERT INTO " + tableName + " VALUES (" + hrid + ", " + beds + ", " + roomCap
                + "," + doctors + ", " + nurses + ")");
        dao.commit();
        dao.disconnect();
    }

    /**
     * Method that updates the data of an HRID by using the parameters given
     * @param hrid int representing the overall humanitarian resource id number
     * @param beds int representing the number of beds available
     * @param roomCap int representing the number of emergency patients that can be treated simultaneously
     * @param doctors int representing the number of doctors
     * @param nurses int representing the number of nurses
     */
    public void update(int hrid, int beds, int roomCap, int doctors, int nurses) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("UPDATE " + tableName + " SET NumBeds = " + beds +
                ", EmergencyRoomCapacity = " + roomCap + ", NumDoctors = " + doctors + ", NumNurses = " + nurses +
                " WHERE HRID = " + hrid);
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
