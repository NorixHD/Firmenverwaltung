

import java.util.Date;

import javax.swing.JTextField;

public class Uhrzeit implements Runnable {

    Date now;

    private JTextField tfAktuelleZeit;
    private JTextField tfStartZeit;
    int hours;
    int minutes;
    int seconds;

    int hours2; // Des ist nur für die Zwei unteren Textfelder
    int minutes2;
    int seconds2;

    public Uhrzeit(JTextField tfAktuelleZeit, JTextField tfStartZeit) {
        this.tfAktuelleZeit = tfAktuelleZeit;
        this.tfStartZeit = tfStartZeit;
    }

    public void run() {
        while (true) {
            now = new Date();

            hours = now.getHours();
            minutes = now.getMinutes();
            seconds = now.getSeconds();

            // tfZeit.setText(String.format("%02d:%02d:%02d\n",Integer.toString(hours),
            // Integer.toString(minutes), Integer.toString(seconds)));
            tfAktuelleZeit.setText(String.format("%02d:%02d:%02d\n", hours, minutes, seconds));

            try {
                Thread.sleep(1000); // Die Uhr aktualisiert sich jede Sekunde
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void zeitEintragen(JTextField feld) {

        hours2 = now.getHours();
        minutes2 = now.getMinutes();
        seconds2 = now.getSeconds();
        feld.setText(String.format("%02d:%02d:%02d\n", hours2, minutes2, seconds2));
        // semaphor1=false;

    }

}

//tfZeit.setText("%02d:%02d:%02d\n",Integer.toString(hours), Integer.toString(minutes), Integer.toString(seconds));
//System.out.printf("%02d:%02d:%02d\n", hours, minutes, seconds);