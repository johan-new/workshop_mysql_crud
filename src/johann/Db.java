package johann;

import java.sql.*;

public class Db {

    private Connection c;
    private Statement s;

    Db(String dbPassword) throws SQLException, ClassNotFoundException, InterruptedException {
        String dbURL = "jdbc:mysql://localhost:3306/Employee?allowPublicKeyRetrieval=true&password="
                + dbPassword + "&useSSL=false&user=root&serverTimezone=UTC";

        //establish connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection(dbURL);
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

       

        c.close();
    }
    
    String read() {
        https://github.com/johan-new/workshop_mysql_crud.git
    }
}
