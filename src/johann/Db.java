package johann;

import java.sql.*;

public class Db {

    private Connection c;
    private Statement s;

    Db(String dbUser, String dbPassword) throws SQLException, ClassNotFoundException    {
        String dbURL = "jdbc:mysql://localhost:3306/employees?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        //establish connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        System.out.println("Ansluten");
        s = c.createStatement();
        
    }
    
    String read() {
        StringBuilder returnStr = new StringBuilder();

        try {
        
            ResultSet rs = s.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                returnStr.append("Anstid:" + rs.getInt("id") + ", Namn " + rs.getString("name") +
                        ", Lön: " + rs.getInt("salary") + "\n");
            }
        } catch (SQLException e) {
            returnStr = new StringBuilder("Cannot read database");
            }

        return returnStr.toString();

        }
    
    String read(String name) {
        StringBuilder returnStr = new StringBuilder();

        try {
            ResultSet rs = s.executeQuery("SELECT * FROM employee WHERE name='" + name + "'");
            while (rs.next()) {
                returnStr.append("Anstid:" + rs.getInt("id") + ", Namn " + rs.getString("name") +
                        ", Lön: " + rs.getInt("salary"));
            }
        } catch (SQLException e) {
            returnStr = new StringBuilder("Cannot read database");
            }

        return returnStr.toString();

        }
    
    void dbClose() {
    	try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


 }
