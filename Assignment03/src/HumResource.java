import java.sql.ResultSet;
import java.sql.SQLException;

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
    String select = "SELECT ";
    String max = "MAX(HRID) ";

    void delete(String tableName, int hrid) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery(delete + from + tableName + where + this.hrid + equals + hrid);
        dao.executeSQLNonQuery(delete + from + this.tableName + where + this.hrid + equals + hrid);
        dao.commit();
        dao.disconnect();
    }

    void Update(int hrid, String name, String address, String phone, String latitude, String longitude, String type, String desc, String hours) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery(update + tableName + set + this.name + equals + quotation + name + quotation +
                comma + this.address + equals + quotation + address + quotation + comma + this.phone + equals +
                quotation + phone + quotation + comma + this.latitude + equals + quotation + latitude + quotation +
                comma + this.longitude + equals +  quotation + longitude + quotation + comma + this.type + equals +
                quotation + type + quotation + comma + this.desc + equals + quotation + desc + quotation + comma +
                this.hours + equals + hours + quotation + space + where + this.hrid + equals + hrid);
        dao.commit();
        dao.disconnect();
    }

    int Insert(String name, String address, String phone, String latitude, String longitude, String type, String desc, String hours) {
        dao.connect();
        dao.setAutoCommit(false);
        int hrid = createHRID();
        dao.executeSQLNonQuery(insert + tableName + values + hrid + comma + quotation + name + quotation +
                comma + quotation + address + quotation + comma + quotation + phone + quotation + comma +
                quotation + latitude + quotation + comma + quotation + longitude + quotation + comma +
                quotation + type + quotation + comma + quotation + desc + quotation + comma + hours + quotation);
        dao.commit();
        dao.disconnect();
        return hrid;
    }

    private int createHRID() {
        int hrid;
        dao.connect();
        dao.setAutoCommit(false);
        ResultSet rs = dao.executeSQLQuery(select + max + from + tableName);
        System.out.println(rs);
        try {
            hrid =  rs.getInt(1);
        }
        catch (SQLException e) {
            System.out.println("sad");
            hrid = 999;
        }
        dao.disconnect();
        return hrid + 1;
    }
}
