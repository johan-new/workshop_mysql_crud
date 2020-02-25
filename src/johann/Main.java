package johann;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        Db db = new Db("root","my-secret-pw");
    }
}
