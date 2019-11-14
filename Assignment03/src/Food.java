
public class Food extends HumResource {

    private String tableName = "FOOD ";
    private String type = "FType ";
    private String meals = "FMealsAvailable ";
    private String desc = "FSpecificDesc ";

    public void update(int hrid, String type, int meals, String desc) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery(update + tableName + set + this.type + equals + quotation + type + quotation + comma +
                this.meals + equals + meals + comma + this.desc + equals + quotation + desc + quotation + space +
                where + this.hrid + space + equals + hrid);
        dao.commit();
        dao.disconnect();
    }

    public void delete(int hrid) {
        delete(tableName, hrid);
    }

    public void insert(int hrid, String type, int meals, String desc) {
        dao.connect();
        dao.setAutoCommit(false);
        dao.executeSQLNonQuery(insert + tableName + values + hrid + comma + quotation + type + quotation +
                comma + meals + comma + quotation + desc + quotation + endParenthesis);
        dao.commit();
        dao.disconnect();
    }
}
