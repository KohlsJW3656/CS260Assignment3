import java.sql.*;

public class HumResource {

    private String tableName = "HumResource ";
    private String name = "HRName ";
    private String address = "HRAddressString ";
    private String phone = "HRPhoneNumber ";
    private String latitude = "HRLatitude ";
    private String longitude = "HRLongitude ";
    private String type = "HRType ";
    private String desc = "HRDesc ";
    private String hours = "HROpenHoursString ";

    DataAccessObject dao = new DataAccessObject();

    String insert = "INSERT INTO ";
    String delete = "DELETE ";
    String update = "UPDATE ";
    String set = "SET ";
    String where = "WHERE ";
    String equals = "= ";
    String values = "VALUES (";
    String hrid = "HRID ";
    String from = "FROM ";
    String comma = ", ";
    String quotation = "\'";
    String space = " ";
    String endParenthesis = ")";

    public void delete(String tableName, int hrid) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery(delete + from + tableName + where + this.hrid + equals + hrid);
        dao.executeSQLNonQuery(delete + from + this.tableName + where + this.hrid + equals + hrid);
        dao.commit();
        dao.disconnect();
    }

    public void update(int hrid, String name, String address, String phone, Double latitude, Double longitude, String type, String desc, String hours) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery(update + tableName + set + this.name + equals + quotation + name + quotation +
                comma + this.address + equals + quotation + address + quotation + comma + this.phone + equals +
                quotation + phone + quotation + comma + this.latitude + equals + latitude +
                comma + this.longitude + equals + longitude + comma + this.type + equals +
                quotation + type + quotation + comma + this.desc + equals + quotation + desc + quotation + comma +
                this.hours + equals + quotation + hours + quotation + space + where + this.hrid + equals + hrid);
        dao.commit();
        dao.disconnect();
    }

    public int insert(String name, String address, String phone, Double latitude, Double longitude, String type, String desc, String hours) {
        int hrid = createHRID();
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery(insert + tableName + values + hrid + comma + quotation + name + quotation +
                comma + quotation + address + quotation + comma + quotation + phone + quotation + comma + latitude +
                comma + longitude + comma + quotation + type + quotation + comma + quotation + desc + quotation + comma + quotation + hours + quotation + endParenthesis);
        dao.commit();
        dao.disconnect();
        return hrid;
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
