//package de.lelonek.unternehmensverwaltung;


import java.awt.BorderLayout;
import java.awt.EventQueue;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class Stempel extends JFrame {

    private JPanel contentPane;
	public String Auftragsnummer;

    private JButton btnDatum;
    private JButton btnPause;
    private JButton btnSchließen;
    private JButton btnStop;
    private JButton btnWeiter;

    private JLabel lblDatum;
    private JLabel lblStartZeit;
    private JLabel lblStop;
    private JLabel lblAktuelleZeit;
    private JLabel lblLaueft;
    private JLabel lblPausen ;
	private JLabel lblAuftragsNr ;
	private JTextField tfAuftragsNr;

    private JTextField tfDatum;
    private JTextField tfStop;
    private JTextField tfZeit;

    
    private JButton btnStart;
    private JTextField tfStartZeit;
    private JTextField tfEndeZeit;

    private LocalDateTime end;
    private LocalDateTime start;
    private de.lelonek.unternehmensverwaltung.Uhrzeit uhrzeit;
    
    
    public long hours, minutes, seconds;
    public double gearbeiteteZeit;
    private ArrayList<LocalDateTime> pausen = new ArrayList<LocalDateTime>();
    private ArrayList<LocalDateTime> weiter =new ArrayList<LocalDateTime>();
    private JTextField tfAnzahlPausen;


    
   
    
    /**
     * Launch the application.
     */
    public static void main() {


	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Stempel frame = new Stempel();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
}




	/*
     * Create the frame.
     */
    public Stempel() {
	setTitle("Stempel Karte f\u00FCr die Mitarbeiter");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 907, 548);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);





		Map<String, Integer> hashMap = loadHashMapFromFile("hashmap.txt");
		System.out.println("HashMap erfolgreich geladen:");
		for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
			System.out.println("Key: " + entry.getKey() + ", Wert: " + entry.getValue());
		}


	btnPause = new JButton("Pause");
	btnPause.setForeground(new Color(0, 0, 128));
	btnPause.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    btnStop.setVisible(false);
		    btnPause.setVisible(false);
		    btnWeiter.setVisible(true);
		    pausen.add(LocalDateTime.now());
		    //System.out.println("Pausen" +pausen.get(0));
		    tfAnzahlPausen.setText(Integer.toString(pausen.size()));
		   // lblLaueft.setVisible(false);
		    lblLaueft.setText("Ihre Zeit wird pausiert");
		    
		}
	});
	btnPause.setBounds(152, 310, 89, 23);
	contentPane.add(btnPause);
	btnPause.setVisible(false);

	btnStop = new JButton("Stop");
	btnStop.setForeground(new Color(0, 0, 128));
	btnStop.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		lblStop.setVisible(true);
		tfStop.setVisible(true);



		 end = LocalDateTime.now();
		Duration d = Duration.between(start, end); 
		gearbeiteteZeit=d.toSeconds();

			Auftragsnummer=tfAuftragsNr.getText();
			if (Auftragsnummer== null){
				Auftragsnummer = "0";
			}
			hashMap.put(Auftragsnummer, (int) gearbeiteteZeit);
			saveHashMapToFile(hashMap);
		System.out.println(gearbeiteteZeit + Auftragsnummer);
		for(int i=0; i<pausen.size(); i++) {
		   // LocalDateTime differenz= weiter.get(i)-pausen.get(i);
		    Duration d1= Duration.between(pausen.get(i),weiter.get(i));
		    long diffSek=d1.toSeconds();
		    gearbeiteteZeit = gearbeiteteZeit-diffSek;
		    System.out.println("Gearbeitet: " +gearbeiteteZeit);
		    
		}
		hours = (int) (gearbeiteteZeit / 3600);
		int restsekunden = (int) (gearbeiteteZeit % 3600);
		minutes= restsekunden / 60;
		seconds = restsekunden % 60;
		
		/*
		hours = d.toHours(); 
		minutes = d.toMinutes() % 60; 
		seconds = d.getSeconds() % 60; 
		*/
		
		 String result = String.format("%02d:%02d:%02d", hours, minutes, seconds); 
		 //System.out.println("Result: " + result);
		 
		 uhrzeit.zeitEintragen(tfEndeZeit);
		 tfStop.setText(result);
		 
		 btnStop.setVisible(false);
		 btnPause.setVisible(false);
		 btnWeiter.setVisible(false);
		 
		// System.out.println(gearbeiteteZeit);
		 lblLaueft.setText("Ihre Arbeitszeit ist beendet");
		 
	    }
	});
	btnStop.setBounds(32, 310, 89, 23);
	contentPane.add(btnStop);
	btnStop.setVisible(false);

	tfZeit = new JTextField();
	tfZeit.setEditable(false);
	tfZeit.setBounds(171, 148, 111, 20);
	contentPane.add(tfZeit);
	tfZeit.setColumns(10);

	lblAktuelleZeit = new JLabel("Aktuelle Uhrzeit:");
	lblAktuelleZeit.setBounds(32, 151, 109, 14);
	contentPane.add(lblAktuelleZeit);

	btnDatum = new JButton("Aktuelles Datum eintragen lassen");
	btnDatum.setForeground(new Color(0, 0, 128));
	btnDatum.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		Date datum = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		tfDatum.setText(format.format(datum));
		tfDatum.setVisible(true);
	    }
	});
	btnDatum.setBounds(29, 72, 231, 23);
	contentPane.add(btnDatum);

	tfDatum = new JTextField();
	tfDatum.setEditable(false);
	tfDatum.setBounds(379, 73, 131, 20);
	contentPane.add(tfDatum);
	tfDatum.setColumns(10);
	tfDatum.setVisible(false);

	btnSchließen = new JButton("Schlie\u00DFen");
	btnSchließen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	btnSchließen.setBounds(749, 475, 121, 23);
	contentPane.add(btnSchließen);

	btnWeiter = new JButton("Weiter");
	btnWeiter.setForeground(new Color(0, 0, 128));
	btnWeiter.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    btnStop.setVisible(true);
		    btnPause.setVisible(true);
		    btnWeiter.setVisible(false);
		    weiter.add(LocalDateTime.now());
		    lblLaueft.setVisible(true);
		    
		 //   System.out.println(("Weiter" +weiter.get(0)));
		}
	});
	btnWeiter.setBounds(280, 310, 89, 23);
	contentPane.add(btnWeiter);
	btnWeiter.setVisible(false);

	lblStop = new JLabel("Gearbeitete Zeit:");
	lblStop.setBounds(32, 256, 138, 14);
	contentPane.add(lblStop);
	lblStop.setVisible(false);

	tfStop = new JTextField();
	tfStop.setEditable(false);
	tfStop.setBounds(171, 253, 111, 20);
	contentPane.add(tfStop);
	tfStop.setColumns(10);

	btnStart = new JButton("Der Start Ihrer Arbeitszeit");
	btnStart.setForeground(new Color(0, 0, 128));
	btnStart.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		 start = LocalDateTime.now();
		 uhrzeit.zeitEintragen(tfStartZeit);
		 btnStart.setVisible(false);
		 btnStop.setVisible(true);
		 btnPause.setVisible(true);
		 btnWeiter.setVisible(false);
		 lblPausen.setVisible(true);
		 tfAnzahlPausen.setVisible(true);
		 lblLaueft.setVisible(true);
	    }
	});
	btnStart.setBounds(37, 201, 604, 23);
	contentPane.add(btnStart);

	lblDatum = new JLabel("Datum:");
	lblDatum.setBounds(336, 76, 46, 14);
	contentPane.add(lblDatum);

	tfStartZeit = new JTextField();
	tfStartZeit.setEditable(false);
	tfStartZeit.setBounds(196, 394, 86, 20);
	contentPane.add(tfStartZeit);
	tfStartZeit.setColumns(10);

	lblStartZeit = new JLabel("Beginn der Arbeitszeit:");
	lblStartZeit.setBounds(32, 397, 131, 14);
	contentPane.add(lblStartZeit);

	JLabel lblEndZeit = new JLabel("Ende der Arbeitszeit:");
	lblEndZeit.setBounds(32, 436, 131, 14);
	contentPane.add(lblEndZeit);

	tfEndeZeit = new JTextField();
	tfEndeZeit.setEditable(false);
	tfEndeZeit.setBounds(196, 433, 86, 20);
	contentPane.add(tfEndeZeit);
	tfEndeZeit.setColumns(10);
	tfStop.setVisible(false);

	uhrzeit = new de.lelonek.unternehmensverwaltung.Uhrzeit(tfZeit, tfStartZeit);
	
	lblLaueft = new JLabel("Ihre Zeit laueft!!");
	lblLaueft.setBounds(673, 205, 166, 14);
	lblLaueft.setVisible(false);
	contentPane.add(lblLaueft);
	lblLaueft.setVisible(false);
	
	lblPausen = new JLabel("Anzahl der Pausen:");
	lblPausen.setBounds(421, 256, 111, 14);
	contentPane.add(lblPausen);
	lblPausen.setVisible(false);

	tfAnzahlPausen = new JTextField();
	tfAnzahlPausen.setEnabled(false);
	tfAnzahlPausen.setBounds(542, 253, 86, 20);
	contentPane.add(tfAnzahlPausen);
	tfAnzahlPausen.setColumns(10);
	tfAnzahlPausen.setVisible(false);


	// Test Stefan

		lblAuftragsNr = new JLabel("AuftragsNr");
		lblAuftragsNr.setForeground(new Color(0, 0, 128));
		lblAuftragsNr.setBounds(159, 11, 105, 14);
		contentPane.add(lblAuftragsNr);

		tfAuftragsNr = new JTextField();

		tfAuftragsNr.setBounds(155, 27, 131, 20);
		contentPane.add(tfAuftragsNr);
		tfAuftragsNr.setColumns(10);


	//Ende Test
	Thread t = new Thread(uhrzeit);
	t.start();


	
	


	}




	// Hashmap speichern


	private static void saveHashMapToFile(Map<String, Integer> hashMap) {
		try (FileWriter writer = new FileWriter("hashmap.txt")) {
			for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
				String line = entry.getKey() + "=" + entry.getValue();
				writer.write(line);
				writer.write(System.lineSeparator());
			}
			System.out.println("HashMap wurde erfolgreich in die Datei gespeichert.");
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern der HashMap in die Datei: " + e.getMessage());
		}
	}

	private static Map<String, Integer> loadHashMapFromFile(String filename) {
		Map<String, Integer> hashMap = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("=");
				if (parts.length == 2) {
					String key = parts[0];
					int value = Integer.parseInt(parts[1]);
					hashMap.put(key, value);
				}
			}
			System.out.println("HashMap erfolgreich aus der Datei geladen.");
		} catch (IOException e) {
			System.out.println("Fehler beim Laden der HashMap aus der Datei: " + e.getMessage());
		}
		return hashMap;
	}

}
