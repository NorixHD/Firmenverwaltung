



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

    public class MitarbeiterverwaltungsGui extends JFrame {

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
        private JLabel lblVorname;

        private JPanel contentPane;

        private JTextField tfEmail;
        private JTextField tfID;
        private JTextField tfIDFinden;
        private JTextField tfNachname;
        private JTextField tfTelefon;
        private JTextField tfVorname;

        private boolean entfernt = false;
        private boolean idExistiert = false;

        private int index;

        private DefaultListModel<String> mitarbeiterModel;
        private JList<String> listMitarbeiter;

        private String vorname, nachname, telefon, email, id;


        /**
         * Launch the application.
         */
        public static void main() {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Gui frame = new Gui();
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
        public MitarbeiterverwaltungsGui() {
            setTitle("Unternehmensverwaltung");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 817, 597);
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

            lblTelefon = new JLabel("Telefon");
            lblTelefon.setForeground(new Color(0, 0, 128));
            lblTelefon.setBounds(491, 11, 116, 14);
            contentPane.add(lblTelefon);

            tfTelefon = new JTextField();
            tfTelefon.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        tfEmail.requestFocus();
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
		    uebernehmen();
                    }
                }
            });
            tfEmail.setBounds(37, 74, 276, 20);
            contentPane.add(tfEmail);
            tfEmail.setColumns(10);

            btnUebernehmen = new JButton("Übernehmen ");
            btnUebernehmen.setForeground(new Color(65, 105, 225));
            btnUebernehmen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
		uebernehmen();
                }
            });
            btnUebernehmen.setBounds(396, 73, 214, 23);
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
            btnEnde.setBounds(686, 499, 89, 23);
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
                        if (tfIDFinden.getText().isEmpty()) throw new Exception("Geben Sie Werte f�r das/die Textfelde/r ein");
                        String suchID = tfIDFinden.getText();

		    /*
		    String zeile = (String) mitarbeiterModel.get(0);
			System.out.println(zeile);
			String[] einzelwert = zeile.split(";");
			String tfID_String = einzelwert[0];
			System.out.println(einzelwert[0]);
			*/
                        System.out.println( "L�nge"+mitarbeiterModel.size());
                        for (int i = 0; i < mitarbeiterModel.size(); i++) {
                            String zeile = (String) mitarbeiterModel.get(i);
                            System.out.println(zeile);
                            String[] einzelwert = zeile.split(";");
                            String tfID_String = einzelwert[0];
                            System.out.println("id"+einzelwert[0]);
                            if (id.equals(tfID_String)) {
                                mitarbeiterModel.remove(i);
                                lblAnzahl.setText("Anzahl: " + mitarbeiterModel.size());
                                tfID.setText(einzelwert[0]);
                                tfNachname.setText(einzelwert[1]);
                                tfVorname.setText(einzelwert[2]);
                                tfTelefon.setText(einzelwert[3]);
                                tfEmail.setText(einzelwert[4]);

                                tfID.requestFocus();
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
                            throw new Exception("Geben Sie eine ID f�r das Textfeld ein");
                        String id = tfIDFinden.getText();

                        for (int i = 0; i < mitarbeiterModel.size(); i++) {
                            String zeile = (String) mitarbeiterModel.get(i);
                            String[] einzelwert = zeile.split(";");
                            String tfID = einzelwert[0];
                            System.out.println(einzelwert[0]);
                            if (id.equals(tfID)) {
                                mitarbeiterModel.remove(i);
                                entfernt = true;
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
                    if (mitarbeiterModel.size() == 0)
                        mitarbeiterModel.clear();
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
            btnAbschlussID.setVisible(false);
        }

        private void uebernehmen() {
            try {
                if (tfNachname.getText().isEmpty()
                        || tfVorname.getText().isEmpty()
                        || tfEmail.getText().isEmpty()
                        || tfID.getText().isEmpty()
                        || tfTelefon.getText().isEmpty()) throw new Exception("Geben Sie Werte f�r das/die Textfelde/r ein");
                nachname = tfNachname.getText();
                vorname = tfVorname.getText();
                telefon = tfTelefon.getText();
                email = tfEmail.getText();
                id = tfID.getText();

int Identnr = Integer.parseInt(id);
                Database.einfuegen_Benutzer(Identnr,nachname,vorname,telefon,email,"1");

                for (int i = 0; i < mitarbeiterModel.size(); i++) {
                    String zeile = (String) mitarbeiterModel.get(i);
                    String[] einzelwert = zeile.split(";");
                    String tfID = einzelwert[0];
                    System.out.println(einzelwert[0]);
                    if (id.equals(tfID)) {
                        JOptionPane.showMessageDialog(null, "Die ID existiert bereits", "Falsche Eingabe",
                                JOptionPane.ERROR_MESSAGE);
                        idExistiert = true;
                        this.tfID.requestFocus();
                        // uebernehmen();
                        break;
                    }
                }
                if (idExistiert == false) {
                    mitarbeiterModel.addElement(id + ";" + nachname + ";" + vorname + ";" + telefon + ";" + email);
                    tfNachname.setText("");
                    tfVorname.setText("");
                    tfTelefon.setText("");
                    tfEmail.setText("");
                    tfID.setText("");
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
            System.out.println(einzelwert[0]);
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
                    System.out.println(einzelwert[0]);
                    if (newId < tfID) {
                        mitarbeiterModel.add(i - 1, zeile);
                        ;
                        entfernt = true;
                        break;
                    }
                }
            }


        }

    }
