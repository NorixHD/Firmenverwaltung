



import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.util.HashMap;


public class LoginGUI extends JFrame {

    private HashMap<String, String> userCredentials; // HashMap zur Speicherung von Benutzername und Passwort

    private JTextField inputTextField;

    private JPasswordField passwordField;


    public LoginGUI() {

        // Fenstertitel setzen

        setTitle("Login");


        // Fenstergröße und Layout festlegen

        setSize(400, 200);

        setLayout(new BorderLayout());


        // Textfeld für Benutzername oder ID erstellen

        inputTextField = new JTextField();


        // Passwort-Feld erstellen

        passwordField = new JPasswordField();


        // Login-Button erstellen

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String input = inputTextField.getText();

                String password = new String(passwordField.getPassword());

                Database.Abgleich(input,password);
                System.out.println(input+password);
                if (Var.Eingeloggt) {

                    JOptionPane.showMessageDialog(null, "Erfolgreich eingeloggt!");
                    MyGUI.main();
                    dispose();

                } else {

                    JOptionPane.showMessageDialog(null, "Falscher Benutzername/ID oder Passwort!");

                }


// erster login
               /* if (userCredentials.containsKey(input) && userCredentials.get(input).equals(password)) {

                    JOptionPane.showMessageDialog(null, "Erfolgreich eingeloggt!");
                    MyGUI.main();
                    dispose();

                } else {

                    JOptionPane.showMessageDialog(null, "Falscher Benutzername/ID oder Passwort!");

                }*/

            }

        });


        // Panel für Login-Elemente erstellen

        JPanel loginPanel = new JPanel(new GridLayout(3, 2));

        loginPanel.add(new JLabel("Benutzername oder ID:"));

        loginPanel.add(inputTextField);

        loginPanel.add(new JLabel("Passwort:"));

        loginPanel.add(passwordField);

        loginPanel.add(new JLabel());

        loginPanel.add(loginButton);


        // Panel zur GUI hinzufügen

        add(loginPanel, BorderLayout.CENTER);


        // Benutzer-Credentials initialisieren

        userCredentials = new HashMap<>();

        userCredentials.put("user1", "password1");

        userCredentials.put("user2", "password2");

        userCredentials.put("user3", "password3");


        // Fenster schließen

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Fenster anzeigen

        setVisible(true);

    }


    public static void main() {

        // GUI starten

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                new LoginGUI();

            }

        });

    }

}

