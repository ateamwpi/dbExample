/**
 * Created by WilsonWong on 3/19/2017.
 */

import java.sql.*;
import java.util.Scanner;
public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("-------Embedded Java DB Connection Testing --------");
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Java DB Driver not found. Add the classpath to your module.");
            e.printStackTrace();
            return;
        }

        System.out.println("Java DB driver registered!");
        Connection connection = null;

        try {
            // substitute your database name for myDB
            connection = DriverManager.getConnection("jdbc:derby:myDB;create=true");

            Statement stmt = connection.createStatement();
            ResultSet rset = stmt.getResultSet();

            //while(true) {
                System.out.print("SQL> ");
                String in = "CREATE TABLE Location ( " +
                                "locID integer primary key, " +
                                "name varchar2(50), " +
                                "room varchar2(10), " +
                                "locType varchar2(10) check locType in ('Physician', 'Service')" +
                            ");";
                //if(in.equals("quit")) break;
                rset = stmt.executeQuery(in);


            //}

            rset.close();
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection failed. Check output console.");
            e.printStackTrace();
            return;
        }
        System.out.println("Java DB connection established!");
    }
}
