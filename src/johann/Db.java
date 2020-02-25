package johann;

import java.sql.*;

public class Db {

    private Connection c;
    private Statement s;
    int rows;

    Db(String dbUser, String dbPassword) throws SQLException, ClassNotFoundException    {
        String dbURL = "jdbc:mysql://localhost:3306/employees?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        //establish connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        System.out.println("Ansluten");

        s = c.createStatement();


        
        //READ
        
        //UPDATE
        rows = s.executeUpdate("UPDATE Employee SET SALARY=31000 WHERE id=3");
        System.out.println("nya rader " + rows);
        
        //DELETE


        read();
       

        c.close();
    }
    
    public void createPost(String salary, String name) {
        try {
			rows = s.executeUpdate("INSERT INTO Employee(" + salary + "," + name +  "VALUES(40000,'Direktör Wallenberg');");
			System.out.println("nya rader " + rows);
		} catch (SQLException e) {
			System.out.println("Det gick inte att skriva till databasen");
		}
    }
    
    public void deletePost(int id) {
        try {
			rows = s.executeUpdate("DELETE FROM Employee WHERE id =" + id);
			if(rows > 0) {
		     System.out.println("nya rader " + rows);
			}
			else {
				System.out.println("Det fanns ingen post med det id:t att radera");
			}
		} catch (SQLException e) {
			System.out.println("Det gick inte att koppla till databasen");
		}
   
    	
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
