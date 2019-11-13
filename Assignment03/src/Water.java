public class Water extends HumResource {

    private String tableName = "Water ";

    public Water() {

    }

    public void update() {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLQuery(update + "" + from + tableName + "");
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
