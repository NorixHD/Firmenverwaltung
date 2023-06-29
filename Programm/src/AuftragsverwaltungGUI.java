

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



public class AuftragsverwaltungGUI {
    private static String DATEI_NAME;

    private JFrame frame;
    private JTextField datumTextField;
    //private JTextField auftragsnummerTextField;
    private JTextField anschriftTextField;
    private JTextArea beschreibungTextArea;
    private static final String DATEINAME = "variablen.txt";

    public static void main() {
        AuftragsverwaltungGUI auftragsverwaltung = new AuftragsverwaltungGUI();
        auftragsverwaltung.start();
        
    }

    public void start() {
        frame = new JFrame("Auftragsverwaltung");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel datumLabel = new JLabel("Datum: (dd.mm.yyyy)");
        datumTextField = new JTextField(20);
        //JLabel auftragsnummerLabel = new JLabel("Auftragsnummer:");
        //auftragsnummerTextField = new JTextField(20);
        JLabel anschriftLabel = new JLabel("Anschrift: (Straße, Hausnummer, Ort, Postleitzahl)");
        anschriftTextField = new JTextField(20);
        JLabel beschreibungLabel = new JLabel("Beschreibung:");
        beschreibungTextArea = new JTextArea(5, 20);
        JScrollPane beschreibungScrollPane = new JScrollPane(beschreibungTextArea);
        JButton auftragHinzufuegenButton = new JButton("Auftrag hinzufügen");
        auftragHinzufuegenButton.addActionListener(new AuftragHinzufuegenListener());

        panel.add(datumLabel);
        panel.add(datumTextField);
        //panel.add(auftragsnummerLabel);
        //panel.add(auftragsnummerTextField);
        panel.add(anschriftLabel);
        panel.add(anschriftTextField);
        panel.add(beschreibungLabel);
        panel.add(beschreibungScrollPane);
        panel.add(auftragHinzufuegenButton);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(400, 300);
        frame.setVisible(true);
        
        
    }

    private void auftragHinzufuegen() {
        String datum = datumTextField.getText();
        //String auftragsnummer = auftragsnummerTextField.getText();
        String anschrift = anschriftTextField.getText();
        String beschreibung = beschreibungTextArea.getText();

        try {
            DATEI_NAME = "Auftrag" + auslesen() + ".txt";
        	BufferedWriter writer = new BufferedWriter(new FileWriter(DATEI_NAME, true));
            writer.write("Datum: " + datum);
            writer.newLine();
            writer.write("Auftragsnummer: " + auslesen());
            writer.newLine();
            writer.write("Anschrift: " + anschrift);
            writer.newLine();
            writer.newLine();
            writer.write("Beschreibung: ");
            writer.newLine();
            writer.write(beschreibung);
            writer.newLine();
            writer.close();

            datumTextField.setText("");
            //auftragsnummerTextField.setText("");
            anschriftTextField.setText("");
            beschreibungTextArea.setText("");

            JOptionPane.showMessageDialog(frame, "Auftrag wurde hinzugefügt.");
            speichern(auslesen()+1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void speichern(int zahl) {
        try {
            FileWriter writer = new FileWriter(DATEINAME);
            writer.write(Integer.toString(zahl));
            writer.close();
            System.out.println("Variable erfolgreich in der Datei gespeichert.");
        } catch (IOException e) {
            System.out.println("Fehler beim Speichern der Variable: " + e.getMessage());
        }
    }

    private static int auslesen() {
        try {
            Scanner scanner = new Scanner(new java.io.File(DATEINAME));
            int gespeicherteZahl = scanner.nextInt();
            scanner.close();
            return gespeicherteZahl;
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Variable: " + e.getMessage());
        }

        return 0;
    }

    private class AuftragHinzufuegenListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            auftragHinzufuegen();
        }
    }
}
