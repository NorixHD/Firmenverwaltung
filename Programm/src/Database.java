import java.sql.*;
import java.util.Scanner;

//https://falconbyte.net/java-mysql-bearbeiten.php
public class Database {
    private static final String host = "127.0.0.1", port = "3306", database = "lager_1", username = "Steff", Passwort = "Steff1234";
    public static Connection connection = null;

    {








    try {
       connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, Passwort);


        Statement statement = connection.createStatement();

        // Ausgabe von den Namen
        ResultSet resultSet = statement.executeQuery("select * from benutzer");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("nachname"));
        }







    } catch (SQLException ex) {
        ex.printStackTrace();
    }
       // einfuegen_Teile(1, "Kolben",1,"keller","k1");
         //bearbeiten_Teile_Anzahl(1, 1);
        //loeschen_Teile(1);
       // einfuegen_Zeit(1,"10.01.202",10);
    }

        public void einfuegen_Teile(int Teilenr, String Teilename, int Anzahl, String Lagerort, String Lagerbezeichnung){

            try {





                String einfuegen = "insert into lager_1.teile(`Teilenr`,`Teilename`,`Anzahl`,`Lagerort`,`Lagerbezeichnung`)VALUES("+ Teilenr +",'"+ Teilename +"'," + Anzahl +",'" + Lagerort +"','" + Lagerbezeichnung + "');";
                Statement stm = connection.createStatement();
                stm.execute(einfuegen);
            } catch (SQLException e) {
                e.printStackTrace();
            }

}
    public static void einfuegen_Benutzer(int id, String nachname, String vorname, String telefonnr, String email, String Passwort){

        try {





            String einfuegen = "insert into lager_1.benutzer(`id`,`nachname`,`vorname`,`telefonnr`,`email`,`Passwort`)VALUES("+ id +",'"+ nachname +"','" + vorname +"','" + telefonnr +"','" + email +"','"+ Passwort + "');";
            Statement stm = connection.createStatement();
            stm.execute(einfuegen);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void bearbeiten_Teile_Anzahl(int nr, int Anzahl ){
        try {
            String update =
                    "UPDATE `lager_1`.`teile` SET `Anzahl`=" + Anzahl + " WHERE `Teilenr`=" + nr + ";";
            Statement stm = connection.createStatement();
            stm.execute(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void loeschen_Teile(int nr){
        try {
            String loeschen =
                    "DELETE FROM `lager_1`.`teile` WHERE `Teilenr`=" + nr + ";";
            Statement stm = connection.createStatement();
            stm.execute(loeschen);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//Zeitmessung    Sstempel datenbank


    public static void einfuegen_Zeit(int id, String Datum, double Arbeitszeit){

        try {

            String einfuegen = "insert into lager_1.stempeln(`MitarbeiterID`,`Datum`,`Zeit`)VALUES("+ id +",'"+ Datum +"'," + Arbeitszeit +");";
            Statement stm = connection.createStatement();
            stm.execute(einfuegen);
        } catch (SQLException e) {
            e.printStackTrace();
        }}

    public void ausgabe_nachmonat(int id,String Datum,double Arbeitszeit){

        try {
            String ausgeben = "insert into lager_1.stempeln(`MitarbeiterID`,`Datum`,`Zeit`)VALUES("+ id +",'"+ Datum +"'," + Arbeitszeit +");";
            Statement stm = connection.createStatement();
            stm.execute(ausgeben);
        } catch (SQLException e) {
            e.printStackTrace();
        }}



// Datenbankabgleich Login

public static void Abgleich(String wert1,String wert2) {





            try {


                // SQL-Abfrage vorbereiten
                String query = "SELECT * FROM benutzer WHERE id = ? AND Passwort = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                // Werte setzen
                preparedStatement.setString(1, wert1);
                preparedStatement.setString(2, wert2);

                // Abfrage ausführen und Ergebnisse abrufen
                ResultSet resultSet = preparedStatement.executeQuery();

                // Prüfen, ob Ergebnisse vorhanden sind
                boolean ergebnisseVorhanden = resultSet.next();

                // Variable auf true setzen, wenn Ergebnisse vorhanden sind, andernfalls auf false setzen
               Var.Eingeloggt = ergebnisseVorhanden ? true : false;

                // Ausgabe des Ergebnisses
                System.out.println("Werte gefunden: " + Var.Eingeloggt);

                // Ressourcen schließen
                /*resultSet.close();
                preparedStatement.close();
                connection.close();*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
    
    
    // Speichern der Gesamten mitarbeiter in einer JList

/*





    // public static void main(String[] args) {SwingUtilities.invokeLater(DatabaseToListExample::createAndShowGUI);}

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Database to JList Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JList<String> dataList = new JList<>(getDataFromDatabase());
        frame.getContentPane().add(new JScrollPane(dataList));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static String[] getDataFromDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL, username, Passwort);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM tablename")) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] data = new String[columnCount];

            while (resultSet.next()) {
                StringBuilder rowData = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.append(resultSet.getString(i)).append(" ");
                }
                data[resultSet.getRow() - 1] = rowData.toString();
            }

            return data;
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0];
        }
    }
}

*/
    }










