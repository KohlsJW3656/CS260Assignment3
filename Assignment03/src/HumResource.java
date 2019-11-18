import java.sql.*;

public class HumResource {

    DataAccessObject dao = new DataAccessObject();
    String tableName;

    public HumResource() {
        tableName = "HumResource ";
    }

    public int insert(String name, String address, String phone, Double latitude, Double longitude, String type, String desc, String hours) {
        int hrid = createHRID();
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("INSERT INTO " + tableName + "VALUES (" + hrid + ", '" + name + "', '" +
                address + "', '" + phone + "', " + latitude + ", " + longitude + ", '" + type + "', '" + desc + "', '" +
                hours + "')");
        dao.commit();
        dao.disconnect();
        return hrid;
    }

    public void update(int hrid, String name, String address, String phone, Double latitude, Double longitude, String type, String desc, String hours) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("UPDATE " + tableName + "SET HRName = '" + name + "', HRAddressString = '"
                + address + "', HRPhoneNumber = '" + phone + "', HRLatitude = " + latitude + ", HRLongitude = " +
                longitude + ", HRType = '" + type + "', HRDesc = '" + desc + "', HROpenHoursString = '" + hours +
                "WHERE HRID = " + hrid);
        dao.commit();
        dao.disconnect();
    }

    public void delete(String tableName, int hrid) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery("DELETE FROM " + tableName + "WHERE HRID = " + hrid);
        dao.executeSQLNonQuery("DELETE FROM " + this.tableName + "WHERE HRID = " + hrid);
        dao.commit();
        dao.disconnect();
    }

    public String displayHRID(String tableName, int hrid) {
        String result;
        dao.connect();
        dao.setAutoCommit(false);
        ResultSet rs = dao.executeSQLQuery("SELECT * FROM " + tableName + "WHERE HRID = " + hrid);
        result = dao.processResultSet(rs);
        dao.disconnect();
        return result;
    }

    private int createHRID() {
        String id;
        dao.connect();
        dao.setAutoCommit(false);
        ResultSet rs = dao.executeSQLQuery("Select MAX(HRID) FROM " + tableName);
        id = dao.processResultSet(rs);
        int hrid = Integer.parseInt(id);
        dao.disconnect();
        return hrid + 1;
    }
}
