
public abstract class HumResource {

    DataAccessObject dao = new DataAccessObject();

    String insert = "INSERT INTO ";
    String delete = "DELETE ";
    String update = "UPDATE ";
    String from = "FROM ";
    String where = "WHERE ";
    String values = "VALUES (";
    String hridEquals = "hrid = ";
    int hrid;


    public void delete(String tableName) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLQuery(delete + from + tableName + where + hridEquals + hrid);
        dao.commit();
        dao.disconnect();
    }

    int createHRID() {
        return 0;
    }
}
