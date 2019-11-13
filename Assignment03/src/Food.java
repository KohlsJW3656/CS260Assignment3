import java.sql.ResultSet;

public class Food extends HumResource {

    private String tableName = "Food ";

    public Food() {

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
        //ResultSet rs = dao.executeSQLNonQuery("");

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
