public class MedicalCenter extends HumResource {

    public MedicalCenter() {
        tableName = "MedicalCenter ";
    }

    public void update(int hrid, int beds, int roomCap, int doctors, int nurses) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("UPDATE " + tableName + "SET NumBeds = " + beds + ", EmergencyRoomCapacity = "
                + roomCap + ", NumDoctors = " + doctors + "NumNurses = " + nurses + " WHERE HRID = " + hrid);
        dao.commit();
        dao.disconnect();
    }

    public void insert(int hrid, int beds, int roomCap, int doctors, int nurses) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("INSERT INTO " + tableName + "VALUES (" + hrid + ", " + beds + ", " + roomCap
                + "," + doctors + ", " + nurses + ")");
        dao.commit();
        dao.disconnect();
    }

    public void delete(int hrid) {
        delete(tableName, hrid);
    }

    public String displayHRID(int hrid) {
        return displayHRID(tableName, hrid);
    }
}
