
abstract class HumResource {

    DataAccessObject dao = new DataAccessObject();

    String insert = "INSERT INTO ";
    String delete = "DELETE FROM ";
    String update = "UPDATE ";
    String set = "SET ";
    String where = "WHERE ";
    String equals = "= ";
    String values = "VALUES (";
    String hrid = "HRID ";
    String comma = ", ";
    String quotation = "\'";
    String space = " ";
    String endParenthesis = ")";

    void delete(String tableName, int hrid) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery(delete + tableName + where + this.hrid + equals + hrid);
        dao.commit();
        dao.disconnect();
    }
}
