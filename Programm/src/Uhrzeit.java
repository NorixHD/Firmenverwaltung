package de.lelonek.unternehmensverwaltung;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Uhrzeit implements Runnable {

    private JTextField tfAktuelleZeit;
    private JTextField tfStartZeit;

    public Uhrzeit(JTextField tfAktuelleZeit, JTextField tfStartZeit) {
        this.tfAktuelleZeit = tfAktuelleZeit;
        this.tfStartZeit = tfStartZeit;
    }

    public void run() {
        while (true) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            String formattedTime = now.format(formatter);

            SwingUtilities.invokeLater(() -> {
                tfAktuelleZeit.setText(formattedTime);
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void zeitEintragen(JTextField feld) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String formattedTime = now.format(formatter);

        SwingUtilities.invokeLater(() -> {
            feld.setText(formattedTime);
        });
    }

}
