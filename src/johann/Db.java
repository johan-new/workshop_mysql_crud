package johann;

import java.sql.*;

public class Db {
	private int rows;
	private Connection c;
	private Statement s;


	Db(String dbUser, String dbPassword) throws SQLException, ClassNotFoundException, InterruptedException {
		String dbURL = "jdbc:mysql://localhost:3306/employees?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";;

        //establish connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        System.out.println("Ansluten");
        s = c.createStatement();



		

	}

	
    
    public void createPost(int salary, String name) {
        try {
			rows = s.executeUpdate("INSERT INTO employee (name, salary) VALUES ('" +name + "' ,"+  salary + ")");
			System.out.println(name + " tillag i databasen");
		} catch (SQLException e) {
			System.out.println("Det gick inte att skriva till databasen");
		}

    }
    
    public void deletePost(String namn) {
        try {
			rows = s.executeUpdate("DELETE FROM employee WHERE name =" + "'" + namn + "'");
			if(rows > 0) {
		     System.out.println(namn +" borttagen");
			}
			else {
				System.out.println("Det fanns ingen post med det namnet " + namn + " att radera");
			}
		} catch (SQLException e) {
			System.out.println("Det gick inte att koppla till databasen");
		}	
    }
    
    public void deleteAll() {
    	try {
			rows = s.executeUpdate("DELETE FROM employee");
			if(rows >1)
			System.out.println(rows + " personer raderade");
			else if (rows == 1) {
				System.out.println("En person raderad");
			}
			else {
				System.out.println("Databasen är redan tom");
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


 

 
	public void UpdateSalary(String namn, int salary)  {
		try {
			
			rows = s.executeUpdate("UPDATE employee SET SALARY=" + salary + " WHERE name='" + namn + "'");
			if (rows == 0) {
				System.out.println("Namnet" + namn + "hittades inte");
			} else {
				System.out.println(namn + " nya lön är nu " + salary);
			}
			
		} catch(SQLException e) {
			System.out.println("Det gick inte att koppla till databasen");
		}
	}
}
