
public class Food extends HumResource {

    private String tableName = "FOOD ";

    public void update() {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLQuery(update + "" + from + tableName + "");
        dao.commit();
        dao.disconnect();
    }

    public void delete() {
        delete(tableName);
    }

    public void insert(String type, int meals, String desc) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLQuery(insert + tableName + values + createHRID() + comma + "\'" + type + "\'" + comma + meals + comma + "\'" + desc + "\')");
        dao.commit();
        dao.disconnect();
    }
}
