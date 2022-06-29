//Name: Syed Aftaabuddin
//Submission Date: 03/14/2021
//Project 7
// Source code file Display.java
// Source code file Load.java
// Load data from space delimited
// file stock_data.txt into the database


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Load{
    public static void main(String[] args) {
        // Define local variables.
        Connection c = null;
        Statement s = null;
        Scanner fromFile = null;
        String sql1 = null, sql2 = null;
        String line = null, exchange = null, ticker = null;
        int price = 0, bid = 0, ask = 0;

        String[ ] fields;
        int age = 0;

        try {
            // Define Connection and Statement objects.
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:kids2.db");
            s = c.createStatement();

            // Instantiate scanner to read from file.
            fromFile = new Scanner(new File ("stock_data.txt"));

            // Create stock table.
            sql1 = "create table if not exists " +
                    "stock(stockid integer, " +
                    "exchange varchar(10), " +
                    "ticker varchar(1), " +
                    "price integer, " +
                    "bid integer, " +
                    "ask integer);";
            System.out.println("sql1: " + sql1);
            s.executeUpdate(sql1);

            // Read and throw away header line.
            fromFile.nextLine( );

            // Populate stock table.
            for (int id = 1001; fromFile.hasNextLine( ); id++) {
                line = fromFile.nextLine( );
                fields = line.split(" ");
                exchange = fields[0].trim( );
                ticker = fields[1].trim( );
                price = Integer.parseInt(fields[2].trim( ));
                bid = Integer.parseInt(fields[3].trim( ));
                ask = Integer.parseInt(fields[4].trim( ));

                sql2 = String.format(
                        "insert into stock (stockid, exchange, ticker, price, bid, ask) " +
                                "values (%d, '%s', '%s', %d, %d, %d);",
                        id, exchange,  ticker, price, bid, ask);
                System.out.println(sql2);
                s.executeUpdate(sql2);
            }

            // Close Connection
            c.close( );
        }
        catch (FileNotFoundException e) {
            System.out.println("File queries.sql not found.");
            System.err.println( e.getClass( ).getName( ) +
                    ": " + e.getMessage( ) );
        }
        catch(SQLException e) {
            System.out.println("SQLException.");
            System.err.println( e.getClass( ).getName( ) +
                    ": " + e.getMessage( ) );
        }
        catch (ClassNotFoundException e ) {
            System.err.println( e.getClass().getName( ) +
                    ": " + e.getMessage( ));
        }
        finally {
            fromFile.close( );
        }
    } //end of main method for Load class
} //end of Load.java class

