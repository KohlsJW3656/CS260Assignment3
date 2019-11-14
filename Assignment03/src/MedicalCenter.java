public class MedicalCenter extends HumResource {

    private String tableName = "MedicalCenter ";


    public void update() {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLQuery(update + "" + from + tableName);
        dao.commit();
        dao.disconnect();
    }

    public void delete() {
        delete(tableName);
    }

    public void insert(int beds, int roomCap, int doctors, int nurses) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLQuery(insert + tableName + values + createHRID() + comma + beds + comma + roomCap + comma + doctors + comma + nurses);
        dao.commit();
        dao.disconnect();
    }
}
