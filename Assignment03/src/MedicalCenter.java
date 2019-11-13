public class MedicalCenter extends HumResource {

    private String tableName = "MedicalCenter ";

    public MedicalCenter() {

    }

    public void update() {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLQuery(update + "" + from + tableName);
        dao.commit();
        dao.disconnect();
    }

    public void delete() {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLQuery(delete + everything + from + tableName);
        dao.commit();
        dao.disconnect();
    }

    public void insert() {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLQuery(insert + "" + into + tableName + "");
        dao.commit();
        dao.disconnect();
    }
}
