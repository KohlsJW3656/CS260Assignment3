/*
 * Class DataAccessObject - class to hold all Oracle database references and methods
 *
 * Created by Paul J. Wagner, 25 March 2019
 *
 */

import java.sql.*;

public class DataAccessObject {

	private Connection daoConn = null;			// JDBC connection
	private ResultSet  daoRset = null;			// result set for queries
	private int returnValue;					// return value for all other commands

	// --- connect() - connect to the Oracle database
	public void connect() {
		// --- set the username and password
        //String user = "BLACKDT0976";
		//String pass = "E5J9QMIY";

		String user = "KOHLSJW3656";
		String pass = "78KRXHRP";

		// --- 1) get the Class object for the driver
		try {
		   Class.forName ("oracle.jdbc.OracleDriver");
		}
		catch (ClassNotFoundException e) {
		   System.err.println ("Could not get class object for Driver");
		}

		// --- 2) connect to database
		try {
		   daoConn = DriverManager.getConnection(
		   "jdbc:oracle:thin:@alfred.cs.uwec.edu:1521:csdev",user,pass);
		   }
		catch (SQLException sqle) {
		   System.err.println ("Could not make connection to database");
		   System.err.println(sqle.getMessage());
		}
	}	// end - method connect

	// --- executeSQLQuery() - execute an SQL query
	public ResultSet executeSQLQuery (String sqlQuery) {
		// --- 3a) execute SQL query
		Statement stmt = null;		// SQL statement object
		daoRset = null;				// initialize result set

		try	{
		   stmt = daoConn.createStatement();
		   daoRset = stmt.executeQuery(sqlQuery);
		}
		catch (SQLException sqle) {
			System.err.println("Could not execute SQL statement: >" + sqlQuery + "<");
			System.err.println(sqle.getMessage());
			// rollback
			rollback();
		}
		return daoRset;
	}	// end - method executeSQLQuery

	// --- executeSQLNonQuery() - execute an SQL command that is not a query
	public int executeSQLNonQuery (String sqlCommand) {
		// --- 3b) execute SQL non-query command
		Statement stmt = null;		// SQL statement object
		returnValue = -1;			// initialize return value
		try	{
		   stmt = daoConn.createStatement();
		   returnValue = stmt.executeUpdate(sqlCommand);
		}
		catch (SQLException sqle) {
			System.err.println("Could not execute SQL command: >" + sqlCommand + "<");
			System.err.println("Return value: " + returnValue);
			System.err.println(sqle.getMessage());
			// rollback
			rollback();
		}
		return returnValue;
	}	// end - method executeSQLNonQuery

	// --- processResultSet() - process the result set
	public String processResultSet(ResultSet rs) {
		// --- 4) process result set, only applicable if executing an SQL SELECT statement
		ResultSetMetaData rsmd = null;		// result set metadata object
		int columnCount = -1;				// column count
		String resultString = "";			// result string

		try {
			rsmd = daoRset.getMetaData();

			// get number of columns from result set metadata
			columnCount = rsmd.getColumnCount();

			// row processing of result set
			while (daoRset.next()) {
				for (int index = 1; index <= columnCount; index++) {
					resultString += daoRset.getString(index);
				}
			}
		}
		catch (SQLException sqle) {
			System.err.println("Error in processing result set");
			System.err.println(sqle.getMessage());
		}
		catch (NullPointerException npe) {
			System.err.println("DAO, processResultSet() - no result set generated");
			System.err.println(npe.getMessage());
		}
		return resultString;
	}	// end - method processResultSet

	// --- processResultSet() - process the result set
	public String[] processUpdateResultSet(ResultSet rs) {
		// --- 4) process result set, only applicable if executing an SQL SELECT statement
		ResultSetMetaData rsmd = null;		// result set metadata object
		int columnCount = -1;				// column count
		String[] resultString = new String[10];			// result string

		try {
			rsmd = daoRset.getMetaData();

			// get number of columns from result set metadata
			columnCount = rsmd.getColumnCount();

			// row processing of result set
			while (daoRset.next()) {
				for (int index = 1; index <= columnCount; index++) {
					resultString[index-1] = daoRset.getString(index);
				}
			}
		}
		catch (SQLException sqle) {
			System.err.println("Error in processing result set");
			System.err.println(sqle.getMessage());
		}
		catch (NullPointerException npe) {
			System.err.println("DAO, processResultSet() - no result set generated");
			System.err.println(npe.getMessage());
		}
		return resultString;
	}	// end - method processResultSet

	public int[] processIntResultSet(ResultSet rs, int length) {
		// --- 4) process result set, only applicable if executing an SQL SELECT statement
		ResultSetMetaData rsmd = null;		// result set metadata object
		int columnCount = -1;				// column count
		int[] resultInt = new int[length];			// result string

		try {
			rsmd = daoRset.getMetaData();

			// get number of columns from result set metadata
			columnCount = rsmd.getColumnCount();

			// row processing of result set
			for (int i = 0; i < length; i++) {
				if (daoRset.next()) {
					resultInt[i] = daoRset.getInt(1);
				}
			}
		}
		catch (SQLException sqle) {
			System.err.println("Error in processing result set");
			System.err.println(sqle.getMessage());
		}
		catch (NullPointerException npe) {
			System.err.println("DAO, processResultSet() - no result set generated");
			System.err.println(npe.getMessage());
		}
		return resultInt;
	}	// end - method processResultSet

	// --- setAutoCommit(flag) - set autocommit on or off based on flag
	public void setAutoCommit (boolean flag) {
		try {
			daoConn.setAutoCommit(flag);
		}
		catch (SQLException sqle) {
			System.err.println("DAO, setAutoCommit() - error in setting");
			System.err.println(sqle.getMessage());
		}
	}	// end - method setAutoCommit()

	// --- commit() - commit current transaction on connection
	public void commit () {
		try {
			daoConn.commit();
		}
		catch (SQLException sqle) {
			System.err.println("DAO, commit() - error in commit");
			System.err.println(sqle.getMessage());
		}
	}	// end - method commit()

	// --- rollback() - rollback current transactino on connection
	public void rollback () {
		try {
			daoConn.rollback();
		}
		catch (SQLException sqle) {
			System.err.println("DAO, rollback() - error in rollback");
			System.err.println(sqle.getMessage());
		}
	}	// end - method rollback()

	// --- disconnect() - disconnect from the Oracle database
	public void disconnect () {
		// --- 5) disconnect from database
		try {
			if (daoConn != null) {
				daoConn.close();
			}
			if (daoRset != null) {
				daoRset = null;
			}
		}
		catch (SQLException sqle) {
			System.err.println ("Error in closing database connection");
			System.err.println(sqle.getMessage());
		}
		finally {
			if (daoConn != null) {
				try {
					daoConn.close();
				}
				catch (SQLException sqlerb) {
					daoConn = null;
				}
			}
			if (daoRset != null) {
				try {
					daoRset = null;
				}
				catch (Exception e) {
					daoRset = null;
				}
			}
		}
	}	// end - method disconnect

}	// end - class DataAccessObject
