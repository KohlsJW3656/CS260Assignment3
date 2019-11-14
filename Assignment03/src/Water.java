public class Water extends HumResource {

    private String tableName = "Water ";

    public void update() {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLQuery(update + "" + from + tableName + "");
        dao.commit();
        dao.disconnect();
    }

    public void delete(int hrid) {
        delete(tableName,hrid);
    }

    public void insert(int numOf10Bottles, int numOfHalfLitter, int numOf5Gal) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLQuery(insert + tableName + values + createHRID() + comma + numOf10Bottles + comma + numOfHalfLitter + comma + numOf5Gal + ")");
        dao.commit();
        dao.disconnect();
    }
}
