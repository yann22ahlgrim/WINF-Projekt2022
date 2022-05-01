package Backend;

import java.awt.EventQueue;
import UI.UI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Datentypen.Grafikkarte;
import Datentypen.Produkt;

public class Hauptklasse {

    static Connection conn = null;
    static String url = "jdbc:mysql://3.69.96.96:3306/";
    static String dbName = "db4";
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String userName = "db4";
    static String password = "!db4.hfts22?";
    static Tabelleneintraege tab = new Tabelleneintraege();

    public static void main(String[] args) {

        // uI(grafikkartenQuery());
        uI(establishConnection());

    }

    public static String[][] establishConnection() {
        String[][] ergebnis = {};
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connected to the database");
            ergebnis = grafikkartenQuery(new Grafikkarte());
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ergebnis;

    }

    public static String[][] grafikkartenQuery(Produkt p) throws SQLException {
        String result = "";
        Statement stmt = conn.createStatement();
        ResultSet rs;
        // Tabelle Spalten benennen
        for (int i = 0; i < p.getTabelleneintraege().length; i++) {
            result += p.getTabelleneintraege()[i] + " ,";
        }
        result += "# ";
        // Query Spalten benennen
        String query = "";
        for (int i = 0; i < p.getTabelleneintraege().length; i++) {
            query += p.getTabelleneintraege()[i] + ", ";
        }
        query=query.substring(0,query.length()-2);
        System.out.println(query);
        rs = stmt.executeQuery("SELECT " + query + " FROM " + p.produktTyp());
        int counter = 1;
        while (rs.next()) {
            int vram = rs.getInt("VRAM");
            String typ = rs.getString("Name");
            String hersteller = rs.getString("Hersteller");
            result += vram + ", " + typ + ", " + hersteller + "# ";
            System.out
                    .println(" Typname: " + typ + "VRAM: " + vram + " Hersteller: " + hersteller);
            counter++;
        }
        String[][] ergebnis = new String[counter][p.getTabelleneintraege().length];
        for (int i = 0; i < counter; i++) {
            ergebnis[i] = result.split("#")[i].split(",");
        }
        System.out.println("Disconnected from database");

        return ergebnis;
    }

    public static String[][] cpuQuery() {
        String result = "";
        String[][] ergebnis = {};
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connected to the database");
            Statement stmt = conn.createStatement();
            ResultSet rs;
            result += "Typname, VRAM, Hersteller# ";
            rs = stmt.executeQuery("SELECT Typname, VRAM, Hersteller FROM Grafikkarten");
            int counter = 1;
            while (rs.next()) {
                int vram = rs.getInt("VRAM");
                String typ = rs.getString("Typname");
                String hersteller = rs.getString("Hersteller");
                result += vram + ", " + typ + ", " + hersteller + "# ";
                System.out.println(
                        " Typname: " + typ + "VRAM: " + vram + " Hersteller: " + hersteller);
                counter++;
            }
            System.out.println(result.split("# ").length);
            System.out.println(counter);

            ergebnis = new String[counter][3];
            for (int i = 0; i < counter; i++) {
                ergebnis[i] = result.split("#")[i].split(",");
                System.out.println("hihi: " + ergebnis[i][0]);
            }
            conn.close();
            System.out.println("Disconnected from database");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ergebnis;
    }

    public static void uI(String[][] input) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UI frame = new UI(input);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
