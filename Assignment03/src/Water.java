public class Water extends HumResource {

    private String tableName = "Water ";
    private String numOf10Bottles = "Num10OzBottlesAvailable ";
    private String numOfHalfLitter = "NumHalfLiterbootlesAvailable ";
    private String numOf5Gal = "Num5GallonJugsAvailable ";

    public void update(int hrid, int numOf10Bottles, int numOfHalfLitter, int numOf5Gal) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery(update + tableName + set + this.numOf10Bottles + equals + numOf10Bottles + comma +
                this.numOfHalfLitter + equals + numOfHalfLitter + comma + this.numOf5Gal + equals + numOf5Gal + space +
                where + this.hrid + space + equals + hrid);
        dao.commit();
        dao.disconnect();
    }

    public void delete(int hrid) {
        delete(tableName,hrid);
    }

    public void insert(int hrid, int numOf10Bottles, int numOfHalfLitter, int numOf5Gal) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery(insert + tableName + values + hrid + comma + numOf10Bottles + comma +
                numOfHalfLitter + comma + numOf5Gal + endParenthesis);
        dao.commit();
        dao.disconnect();
    }
}
