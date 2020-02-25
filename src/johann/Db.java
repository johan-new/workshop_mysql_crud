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

        //CREATE
        int rows = s.executeUpdate("INSERT INTO Employee(salary, name) VALUES(40000,'Direktör Wallenberg');");
        System.out.println("nya rader " + rows);

        //READ
        
        //UPDATE
        rows = s.executeUpdate("UPDATE Employee SET SALARY=31000 WHERE id=3");
        System.out.println("nya rader " + rows);
        
        //DELETE
        rows = s.executeUpdate("DELETE FROM Employee WHERE salary=40000");
        System.out.println("nya rader " + rows);

        read();
       

        c.close();
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


    }
}
