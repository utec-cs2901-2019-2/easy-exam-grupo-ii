import java.sql.*;

import java.io.PrintWriter;
import java.io.File;


class JsonCon {
 
    public static void main (String[] args) {
        try {
            //TODO use correct credentials url, user, pass
            String url = "jdbc:msql://localhost:5001/";
            String user = "";
            String pass = "";
            Connection conn = DriverManager.getConnection(url,user,pass);
            Statement stmt = conn.createStatement();
            ResultSet rs;
 
            rs = stmt.executeQuery("SELECT * FROM problem FOR JSON AUTO");
            while ( rs.next() ) {
                PrintWriter writer = new PrintWriter("problems.json");
                writer.println(rs);
                writer.close();
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
}

