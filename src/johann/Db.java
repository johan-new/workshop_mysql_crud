package johann;

import java.sql.*;

public class Db {
	private int rows;
	private Connection c;
	private Statement s;


	Db(String dbPassword) throws SQLException, ClassNotFoundException, InterruptedException {
		String dbURL = "jdbc:mysql://localhost:3306/Employee?allowPublicKeyRetrieval=true&password=" + dbPassword
				+ "&useSSL=false&user=root&serverTimezone=UTC";

		// establish connection
		Class.forName("com.mysql.cj.jdbc.Driver");
		c = DriverManager.getConnection(dbURL);
		System.out.println("Ansluten");

		s = c.createStatement();


		// CREATE
		int rows = s.executeUpdate("INSERT INTO Employee(salary, name) VALUES(40000,'Direktör Wallenberg');");
		System.out.println("nya rader " + rows);

		// READ

		// UPDATE

		rows = s.executeUpdate("UPDATE Employee SET SALARY=31000 WHERE id=3");
		System.out.println("nya rader " + rows);

		// DELETE
		rows = s.executeUpdate("DELETE FROM Employee WHERE salary=40000");
		System.out.println("nya rader " + rows);

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


    

 
	public void UpdateSalary(int id, int salary)  {
		try {
			
			rows = s.executeUpdate("UPDATE Employee SET SALARY=" + salary + "WHERE id=" + id);
			if (rows == 0) {
				System.out.println("Id:t hittades inte");
			} else {
				System.out.println("Posten uppdaterades");
			}
			
		} catch(SQLException e) {
			System.out.println("Det gick inte att koppla till databasen");
		}
	}
}
