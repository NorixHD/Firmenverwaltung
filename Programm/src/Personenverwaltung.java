//package de.lelonek.unternehmensverwaltung;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Personenverwaltung extends JFrame {
    
    private JButton btnAbschlussID;
    private JButton btnAllleLoeschen;
    private JButton btnBearbeitungID;
    private JButton btnEnde;
    private JButton btnIDFinden;
    private JButton btnLoeschen;
    private JButton btnUebernehmen;

    private JLabel lblAnzahl;
    private JLabel lblEmail;
    private JLabel lbID;
    private JLabel lblIDFinden;
    private JLabel lblTelefon;
    private JLabel lblMitarbeiterliste;
    private JLabel lblNachname;
    private JLabel lblPasswort;
    private JLabel lblVorname;

    private JPanel contentPane;
    
   private JPasswordField pfPasswort;
    
    private JTextField tfEmail;
    private JTextField tfID;
    private JTextField tfIDFinden;
    private JTextField tfNachname;
    private JTextField tfTelefon;
    private JTextField tfVorname;
   
    private boolean entfernt = false;
    private boolean idExistiert = false;
    
    private String filename = "information.txt";
    FileReader fileReader;
    BufferedReader bufferedReader;
    
    private int index;
    
    
   
    private DefaultListModel<String> mitarbeiterModel;
    private JList<String> listMitarbeiter;
    
    private ArrayList<String> alleIds=new ArrayList<String>();
    
     private File file;

    private String vorname, nachname, telefon, email, id, passwort;
    private JTextField tfPasswort;
    

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Personenverwaltung frame = new Personenverwaltung();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public Personenverwaltung() {
	setTitle("Personenverwaltung");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 1010, 597);
	//setExtendedState(JFrame.MAXIMIZED_BOTH);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	lblNachname = new JLabel("Nachname");
	lblNachname.setForeground(new Color(0, 0, 128));
	lblNachname.setBounds(159, 11, 105, 14);
	contentPane.add(lblNachname);

	tfNachname = new JTextField();
	tfNachname.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    tfVorname.requestFocus();
		}
	    }
	});
	tfNachname.setBounds(155, 27, 131, 20);
	contentPane.add(tfNachname);
	tfNachname.setColumns(10);

	lblVorname = new JLabel("Vorname");
	lblVorname.setForeground(new Color(0, 0, 128));
	lblVorname.setBounds(317, 11, 87, 14);
	contentPane.add(lblVorname);

	tfVorname = new JTextField();
	tfVorname.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    tfTelefon.requestFocus();
		}
	    }
	});
	tfVorname.setBounds(314, 27, 131, 20);
	contentPane.add(tfVorname);
	tfVorname.setColumns(10);
/*
	tfPasswort = new JTextField();
	tfPasswort.setEchoChar('*');
	tfPasswort.setBounds(728, 27, 105, 20);
	contentPane.add(tfPasswort);
	tfPasswort.setColumns(10);
	*/
	
	pfPasswort = new JPasswordField();
	pfPasswort.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    tfEmail.requestFocus();
		}
	});
	pfPasswort.setBounds(728, 27, 105, 20);
	contentPane.add(pfPasswort);
	pfPasswort.setEchoChar('*');
	pfPasswort.setColumns(10);
	
	lblTelefon = new JLabel("Telefon");
	lblTelefon.setForeground(new Color(0, 0, 128));
	lblTelefon.setBounds(491, 11, 116, 14);
	contentPane.add(lblTelefon);

	tfTelefon = new JTextField();
	tfTelefon.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    pfPasswort.requestFocus();
		}
	    }
	});
	tfTelefon.setBounds(483, 27, 218, 20);
	contentPane.add(tfTelefon);
	tfTelefon.setColumns(10);

	lblEmail = new JLabel("E-Mail");
	lblEmail.setForeground(new Color(0, 0, 128));
	lblEmail.setBounds(46, 58, 131, 14);
	contentPane.add(lblEmail);
	
	

	tfEmail = new JTextField();
	tfEmail.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    übernehmen();
		}
	    }
	});
	tfEmail.setBounds(37, 74, 276, 20);
	contentPane.add(tfEmail);
	tfEmail.setColumns(10);

	btnUebernehmen = new JButton("\u00DCbernehmen ");
	btnUebernehmen.setForeground(new Color(65, 105, 225));
	btnUebernehmen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		übernehmen();
	    }
	});
	btnUebernehmen.setBounds(619, 73, 214, 23);
	contentPane.add(btnUebernehmen);

	mitarbeiterModel = new DefaultListModel<>();

	lblMitarbeiterliste = new JLabel("Mitarbeiterliste");
	lblMitarbeiterliste.setForeground(new Color(0, 0, 128));
	lblMitarbeiterliste.setBounds(46, 134, 131, 14);
	contentPane.add(lblMitarbeiterliste);

	btnEnde = new JButton("Ende");
	btnEnde.setForeground(new Color(0, 0, 0));
	btnEnde.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	btnEnde.setBounds(797, 513, 89, 23);
	contentPane.add(btnEnde);

	mitarbeiterModel = new DefaultListModel<>();

	lbID = new JLabel("ID");
	lbID.setForeground(new Color(0, 0, 128));
	lbID.setBounds(46, 11, 25, 14);
	contentPane.add(lbID);

	tfID = new JTextField();
	tfID.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		    tfNachname.requestFocus();
		}
	    }
	});
	tfID.setBounds(37, 27, 86, 20);
	contentPane.add(tfID);
	tfID.setColumns(10);

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(37, 152, 664, 233);
	contentPane.add(scrollPane);

	listMitarbeiter = new JList<String>();
	listMitarbeiter.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	scrollPane.setViewportView(listMitarbeiter);

	mitarbeiterModel = new DefaultListModel<>();
	listMitarbeiter.setModel(mitarbeiterModel);

	JButton btnMarkiertenEintragLoeschen = new JButton("Markierten Eintrag l\u00F6schen");
	btnMarkiertenEintragLoeschen.setForeground(new Color(65, 105, 225));
	btnMarkiertenEintragLoeschen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		mitarbeiterModel.remove(listMitarbeiter.getSelectedIndex());
		lblAnzahl.setText("Anzahl: " + mitarbeiterModel.size());
		
		aktualisieren();
	    }
	});
	btnMarkiertenEintragLoeschen.setBounds(37, 407, 207, 23);
	contentPane.add(btnMarkiertenEintragLoeschen);

	JButton btnBearbeiten = new JButton("Bearbeiten");
	btnBearbeiten.setForeground(new Color(65, 105, 225));
	btnBearbeiten.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		index = listMitarbeiter.getSelectedIndex();
		if (index >= 0) {
		    String zeile = (String) mitarbeiterModel.get(index);
		    String[] einzelwert = zeile.split(";");
		    mitarbeiterModel.remove(listMitarbeiter.getSelectedIndex());
		    lblAnzahl.setText("Anzahl: " + mitarbeiterModel.size());
		    tfID.setText(einzelwert[0]);
		    tfNachname.setText(einzelwert[1]);
		    tfVorname.setText(einzelwert[2]);
		    tfTelefon.setText(einzelwert[3]);
		    tfEmail.setText(einzelwert[4]);
		    pfPasswort.setText(einzelwert[5]);

		    tfID.requestFocus();
		}
	    }
	});
	btnBearbeiten.setBounds(284, 407, 112, 23);
	contentPane.add(btnBearbeiten);

	lblIDFinden = new JLabel("ID:");
	lblIDFinden.setBounds(46, 503, 25, 14);
	contentPane.add(lblIDFinden);
	lblIDFinden.setVisible(false);

	tfIDFinden = new JTextField();
	tfIDFinden.setBounds(91, 500, 86, 20);
	contentPane.add(tfIDFinden);
	tfIDFinden.setColumns(10);
	tfIDFinden.setVisible(false);

	btnIDFinden = new JButton("Bearbeiten");
	btnIDFinden.setForeground(new Color(65, 105, 225));
	btnIDFinden.setVisible(false);
	btnIDFinden.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		// System.out.println("Hallo");
		try {
		    if (tfIDFinden.getText().isEmpty()) throw new Exception("Geben Sie Werte für das/die Textfelde/r ein");
		    String suchID = tfIDFinden.getText();

		    /*
		    String zeile = (String) mitarbeiterModel.get(0);
			System.out.println(zeile);
			String[] einzelwert = zeile.split(";");
			String tfID_String = einzelwert[0];
			System.out.println(einzelwert[0]);
			*/
		    System.out.println( "Länge"+mitarbeiterModel.size());
		    for (int i = 0; i < mitarbeiterModel.size(); i++) {
			String zeile = (String) mitarbeiterModel.get(i);
			System.out.println(zeile);
			String[] einzelwert = zeile.split(";");
			String tfID_String = einzelwert[0];
			System.out.println("id"+einzelwert[0]);
			if (suchID.equals(tfID_String)) {
			    mitarbeiterModel.remove(i);
			    lblAnzahl.setText("Anzahl: " + mitarbeiterModel.size());
			    tfID.setText(einzelwert[0]);
			    tfNachname.setText(einzelwert[1]);
			    tfVorname.setText(einzelwert[2]);
			    tfTelefon.setText(einzelwert[3]);
			    tfEmail.setText(einzelwert[4]);
			    pfPasswort.setText(einzelwert[5]);

			    tfID.requestFocus();
			    tfIDFinden.setText("");
			    break;
			}
		    }

		} catch (Exception ex) {
		    JOptionPane.showMessageDialog(null, ex.getMessage(), "Falsche Eingabe", JOptionPane.ERROR_MESSAGE);

		}
	    }
	});
	btnIDFinden.setBounds(198, 499, 105, 23);
	contentPane.add(btnIDFinden);

	lblAnzahl = new JLabel("Anzahl:");
	lblAnzahl.setForeground(new Color(0, 0, 128));
	lblAnzahl.setBounds(37, 441, 179, 14);
	contentPane.add(lblAnzahl);
	lblAnzahl.setText("Anzahl: 0");

	btnLoeschen = new JButton("L\u00F6schen");
	btnLoeschen.setForeground(new Color(65, 105, 225));
	btnLoeschen.setVisible(false);
	btnLoeschen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		try {
		    if (tfIDFinden.getText().isEmpty())
			throw new Exception("Geben Sie eine ID für das Textfeld ein");
		    String id = tfIDFinden.getText();

		    for (int i = 0; i < mitarbeiterModel.size(); i++) {
			String zeile = (String) mitarbeiterModel.get(i);
			String[] einzelwert = zeile.split(";");
			String tfID = einzelwert[0];
			if (id.equals(tfID)) {
			    mitarbeiterModel.remove(i);
			    lblAnzahl.setText("Anzahl: " + mitarbeiterModel.size());
			    entfernt = true;
			  aktualisieren();
			    break;
			}
		    }
		    if (entfernt == false) {
			JOptionPane.showMessageDialog(null, "ID konnte nicht gefunden werden", "Falsche Eingabe",
				JOptionPane.ERROR_MESSAGE);
		    }
		    entfernt = false;

		} catch (Exception ex) {
		    JOptionPane.showMessageDialog(null, ex.getMessage(), "Falsche Eingabe", JOptionPane.ERROR_MESSAGE);
		}
	    }
	});
	btnLoeschen.setBounds(331, 499, 89, 23);
	contentPane.add(btnLoeschen);

	btnBearbeitungID = new JButton("Mitarbeiter anhand ID bearbeiten");
	btnBearbeitungID.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (mitarbeiterModel.size() != 0) {
		    lblIDFinden.setVisible(true);
		    tfIDFinden.setVisible(true);
		    btnIDFinden.setVisible(true);
		    btnLoeschen.setVisible(true);
		    btnAbschlussID.setVisible(true);
		    btnBearbeitungID.setVisible(false);
		    tfIDFinden.requestFocus();
		} else {
		    JOptionPane.showMessageDialog(null, "Die Mitarbeiterliste ist leer", "Fehlermeldung",
			    JOptionPane.ERROR_MESSAGE);
		}
	    }
	});
	btnBearbeitungID.setForeground(new Color(65, 105, 225));
	btnBearbeitungID.setBounds(37, 466, 276, 23);
	contentPane.add(btnBearbeitungID);

	btnAllleLoeschen = new JButton("Alles L\u00F6schen");
	btnAllleLoeschen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (mitarbeiterModel.size() != 0)
		    mitarbeiterModel.clear();
		if (file.exists()) {
	                // Datei löschen
	                if (file.delete()) {
	                    JOptionPane.showMessageDialog(null, "Datei wurde erfolgreich gelöscht");
	                } else {
	                    JOptionPane.showMessageDialog(null,"Fehler beim Löschen der Datei", "Falsche Eingabe",JOptionPane.ERROR_MESSAGE);
	                }
	            
	            }
		
	           
		lblAnzahl.setText("Anzahl: " + mitarbeiterModel.size());
		
	    }
	    /*
	     * else { JOptionPane.showMessageDialog(null,
	     * "Die Mitarbeiterliste ist bereits leer", "Falsche Eingabe",
	     * JOptionPane.ERROR_MESSAGE); }
	     */
	});
	btnAllleLoeschen.setForeground(new Color(65, 105, 225));
	btnAllleLoeschen.setBounds(456, 407, 151, 23);
	contentPane.add(btnAllleLoeschen);

	btnAbschlussID = new JButton("Bearbeitung anhand der ID abgeschlossen");
	btnAbschlussID.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		lblIDFinden.setVisible(false);
		tfIDFinden.setVisible(false);
		btnIDFinden.setVisible(false);
		btnLoeschen.setVisible(false);
		btnAbschlussID.setVisible(false);
		btnBearbeitungID.setVisible(true);
	    }
	});
	btnAbschlussID.setForeground(new Color(65, 105, 225));
	btnAbschlussID.setBounds(37, 531, 276, 23);
	contentPane.add(btnAbschlussID);
	
	lblPasswort = new JLabel("Passwort");
	lblPasswort.setForeground(new Color(0, 0, 128));
	lblPasswort.setBounds(737, 11, 60, 14);
	contentPane.add(lblPasswort);
	
	JButton btnBildEinfuegen = new JButton("Bild einf\u00FCgen");
	btnBildEinfuegen.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    ImageDragAndDrop frame = new ImageDragAndDrop();
		    frame.setVisible(true);
		}
	});
	btnBildEinfuegen.setForeground(new Color(100, 149, 237));
	btnBildEinfuegen.setBounds(406, 73, 131, 23);
	contentPane.add(btnBildEinfuegen);
	
	
	
	
	btnAbschlussID.setVisible(false);
	
	 file = new File(filename);
        if (file.exists()) {
            // Wenn die Datei existiert, Werte zur Liste hinzufügen und anzeigen
            try {
                 fileReader = new FileReader(file);
                 bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    mitarbeiterModel.addElement(line);
                }
                bufferedReader.close();
            } catch (IOException e) {
        	 JOptionPane.showMessageDialog(null,e.getMessage(), "Fehlermeldung",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

    private void übernehmen() {
	    try {
	        idExistiert = false; 

	        if (tfNachname.getText().isEmpty()
	                || tfVorname.getText().isEmpty()
	                || tfEmail.getText().isEmpty()
	                || tfID.getText().isEmpty()
	                || tfTelefon.getText().isEmpty()) throw new Exception("Geben Sie Werte für das/die Textfelde/r ein");

	        char[] passwordChars = pfPasswort.getPassword();
	        if (passwordChars.length == 0) throw new Exception("Geben Sie Werte für das Passwort ein");


	        passwort = new String(passwordChars);
	        nachname = tfNachname.getText();
	        vorname = tfVorname.getText();
	        telefon = tfTelefon.getText();
	        email = tfEmail.getText();
	        id = tfID.getText();


	        for (int i = 0; i < mitarbeiterModel.size(); i++) {
	            String zeile = (String) mitarbeiterModel.get(i);
	            String[] einzelwert = zeile.split(";");
	            String tfID = einzelwert[0];
	            if (id.equals(tfID)) {
	                JOptionPane.showMessageDialog(null, "Die ID existiert bereits", "Falsche Eingabe",
	                        JOptionPane.ERROR_MESSAGE);
	                idExistiert = true;
	                this.tfID.requestFocus();
	                break;
	            }
	        }
	        if (idExistiert == false) {

	            mitarbeiterModel.addElement(id + ";" + nachname + ";" + vorname + ";" + telefon + ";" +passwort +";"+ email);
	            saveData();
	            tfNachname.setText("");
	            tfVorname.setText("");
	            tfTelefon.setText("");
	            tfEmail.setText("");
	            tfID.setText("");
	            pfPasswort.setText("");
	            lblAnzahl.setText("Anzahl: " + mitarbeiterModel.size());
	            tfID.requestFocus();

	        }

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, ex.getMessage(), "Falsche Eingabe", JOptionPane.ERROR_MESSAGE);
	    }
	}


    private String getID(int i) {
	String zeile = (String) mitarbeiterModel.get(i);
	String[] einzelwert = zeile.split(";");
	String tfID = einzelwert[0];
	return tfID;
    }
    
    

    private void sortieren(String id) {
	if (tfIDFinden.getText().isEmpty()) {
	    JOptionPane.showMessageDialog(null, "Keine ID vorhanden", "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
	} else {
	    int newId = Integer.parseInt(tfID.getText());
	    for (int i = 0; i < mitarbeiterModel.size(); i++) {
		String zeile = (String) mitarbeiterModel.get(i);
		String[] einzelwert = zeile.split(";");
		int tfID = Integer.parseInt(einzelwert[0]);
		if (newId < tfID) {
		    mitarbeiterModel.add(i - 1, zeile);
		    ;
		    entfernt = true;
		    break;
		}
	    }
	}
	

    }
    
    private void saveData() {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < mitarbeiterModel.size(); i++) {
                String name = mitarbeiterModel.getElementAt(i);
                bufferedWriter.write(name);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Die ID existiert bereits", "Falsche Eingabe",
		    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void aktualisieren() {
	if (file.exists()) {
            // Datei löschen
            if (file.delete()) {
                saveData();
            }
        }
    }
}



