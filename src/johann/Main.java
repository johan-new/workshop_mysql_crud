package johann;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        Db db = new Db("root","my-secret-pw");
        db.deleteAll();

        db.createPost(23000, "Magnus");
        db.createPost(23000, "Milla");
        System.out.println(db.read());
        db.UpdateSalary("Magnus", 35000);
        System.out.println(db.read("Magnus"));
        db.deletePost("Magnus");
        System.out.println(db.read());
        
        
        
        
    }
}
