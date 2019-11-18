public class Food extends HumResource {

    public Food() {
        tableName = "FOOD ";
    }

    public void update(int hrid, String type, int meals, String desc) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("UPDATE " + tableName + "SET FType = '" + type + "', FMealsAvailable = " +
                meals + ", FSpecificDesc = '" + desc + "' WHERE HRID = " + hrid);
        dao.commit();
        dao.disconnect();
    }

    public void insert(int hrid, String type, int meals, String desc) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("INSERT INTO " + tableName + "VALUES (" + hrid + ", '" + type + "', " + meals
                + ", '" + desc + "')");
        dao.commit();
        dao.disconnect();
    }

    public void delete(int hrid) {
        delete(tableName, hrid);
    }

    public String displayHRID(int hrid) {
        return displayHRID(tableName, hrid);
    }
}
