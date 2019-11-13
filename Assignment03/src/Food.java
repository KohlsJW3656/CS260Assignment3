
public class Food extends HumResource {

    private String tableName = "FOOD ";

    public Food(int hrid) {
        this.hrid = hrid;
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
        dao.executeSQLQuery(delete + from + tableName + where + "hrid " + "= " + hrid);
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
