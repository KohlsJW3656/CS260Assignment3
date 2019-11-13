import java.sql.*;

public abstract class HumResource {

    DataAccessObject dao = new DataAccessObject();
    String insert = "INSERT ";
    String delete = "DELETE ";
    String update = "UPDATE ";
    String from = "FROM ";
    String into = "INTO ";
    String everything = "* ";
    String select = "SELECT ";
    String countEverything = "COUNT(*) ";
    Statement stmt = null;
}
