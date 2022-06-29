//Name: Syed Aftaabuddin
//Submission Date: 03/14/2021
//Project 7
// Source code file Display.java
// Display data from stock table
// given the id.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Display {
    public static void main(String[] args) {
        // Declare local variables.
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        int id = 0, price = 0, bid = 0, ask = 0;
        String sql = null, exchange = null, ticker = null;
        try (Scanner fromKeyboard = new Scanner(System.in)) {
            // Define Connection and Statement objects.
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:kids2.db");
            s = c.createStatement();

            while (id != -1) {
                // Read id and display corresponding table row.
                System.out.print("Enter ID: ");
                id = fromKeyboard.nextInt();
                sql = "select exchange, ticker, price, bid, ask" +
                        " from stock where stockid = " + id + ";";
                System.out.println(sql);
                rs = s.executeQuery(sql);
                while (rs.next()) {
                    System.out.println(" ");
                    exchange = rs.getString("exchange");
                    ticker = rs.getString("ticker");
                    price = rs.getInt("price");
                    bid = rs.getInt("bid");
                    ask = rs.getInt("ask");

                    System.out.println("exchange: " + exchange);
                    System.out.println("ticker: " + ticker);
                    System.out.println("price: " + price);
                    System.out.println("bid: " + bid);
                    System.out.println("ask: " + ask);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException.");
            System.err.println(e.getClass().getName() +
                    ": " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getClass().getName() +
                    ": " + e.getMessage());
        }
    } //end of main method
} //end of Display.java
