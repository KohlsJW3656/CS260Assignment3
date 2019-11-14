public class MedicalCenter extends HumResource {

    private String tableName = "MedicalCenter ";
    private String beds = "NumBeds ";
    private String roomCap = "EmergencyRoomCapacity ";
    private String doctors = "NumDoctors ";
    private String nurses = "NumNurses ";


    public void update(int hrid, int beds, int roomCap, int doctors, int nurses) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLQuery(update + tableName + set + this.beds + equals + beds + comma + this.roomCap + equals +
                roomCap + comma + this.doctors + equals + doctors + comma + this.nurses + equals + nurses + space +
                where + quotation + this.hrid + quotation + equals + hrid);
        dao.commit();
        dao.disconnect();
    }

    public void delete(int hrid) {
        delete(tableName, hrid);
    }

    public void insert(int hrid, int beds, int roomCap, int doctors, int nurses) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLQuery(insert + tableName + values + hrid + comma + beds + comma + roomCap + comma + doctors + comma + nurses + ")");
        dao.commit();
        dao.disconnect();
    }
}
