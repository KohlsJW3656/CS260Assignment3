public class Water extends HumResource {

    public Water() {
        tableName = "WATER ";
    }

    public void insert(int hrid, int numOf10Bottles, int numOfHalfLitter, int numOf5Gal) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("INSERT INTO " + tableName + "VALUES (" + hrid + ", " + numOf10Bottles + ", "
                + numOfHalfLitter + ", " + numOf5Gal + ")");
        dao.commit();
        dao.disconnect();
    }

    public void update(int hrid, int numOf10Bottles, int numOfHalfLitter, int numOf5Gal) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("UPDATE " + tableName + "SET Num10OzBottlesAvailable = " + numOf10Bottles +
                        ", NumHalfLiterBottlesAvailable = " + numOfHalfLitter + ", Num5GallonJugsAvailable = " +
                numOf5Gal + " WHERE HRID = " + hrid);
        dao.commit();
        dao.disconnect();
    }

    public void delete(int hrid) {
        delete(tableName, hrid);
    }

    public String[] displayHRID(int hrid) {
        return displayHRID(tableName, hrid);
    }
}
