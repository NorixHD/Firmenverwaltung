

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.util.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class StempelView extends JFrame {

    private JPanel contentPane;

    private JButton btnDatum;
    private JButton btnPause;
    private JButton btnSchlieen;
    private JButton btnStop;
    private JButton btnWeiter;

    private JLabel lblDatum;
    private JLabel lblStartZeit;
    private JLabel lblStop;
    private JLabel lblAktuelleZeit;

    private JTextField tfDatum;
    private JTextField tfStop;
    private JTextField tfZeit;

    
    private JButton btnStart;
    private JTextField tfStartZeit;
    private JTextField tfEndeZeit;

    private LocalDateTime end;
    private LocalDateTime start;
    private Uhrzeit uhrzeit;
	public  String Datum;
    
    
    public long hours, minutes, seconds;
    public double gearbeiteteZeit;
    
   
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    StempelView frame = new StempelView();
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
    public StempelView() {
	setTitle("Stempel Karte f\u00FCr die Mitarbeiter");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 907, 548);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);

	btnPause = new JButton("Pause");
	btnPause.setBounds(152, 310, 89, 23);
	contentPane.add(btnPause);

	btnStop = new JButton("Stop");
	btnStop.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		lblStop.setVisible(true);
		tfStop.setVisible(true);
		
		 end = LocalDateTime.now();
		 Duration d = Duration.between(start, end); 
		hours = d.toHours(); 
		 minutes = d.toMinutes() % 60; 
		seconds = d.getSeconds() % 60; 
		gearbeiteteZeit=hours/3600 +minutes/60+seconds;		//Umwandlung der Zeit in Sekunden
		
		 String result = String.format("%02d:%02d:%02d", hours, minutes, seconds);

			//Stefan

			Database.einfuegen_Zeit(2, Datum,gearbeiteteZeit);

			//Stefan Ende



			uhrzeit.zeitEintragen(tfEndeZeit);
		 tfStop.setText(result);
		 
		 btnStop.setVisible(false);
		 btnPause.setVisible(false);
		 btnWeiter.setVisible(false);
		 
	    }
	});
	btnStop.setBounds(32, 310, 89, 23);
	contentPane.add(btnStop);

	tfZeit = new JTextField();
	tfZeit.setEditable(false);
	tfZeit.setBounds(171, 148, 111, 20);
	contentPane.add(tfZeit);
	tfZeit.setColumns(10);

	lblAktuelleZeit = new JLabel("Aktuelle Uhrzeit:");
	lblAktuelleZeit.setBounds(32, 151, 109, 14);
	contentPane.add(lblAktuelleZeit);

	btnDatum = new JButton("Aktuelles Datum eintragen lassen");
	btnDatum.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		Date datum = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		tfDatum.setText(format.format(datum));
		tfDatum.setVisible(true);
			Datum = format.format(datum);
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

	btnSchlieen = new JButton("Schlie\u00DFen");
	btnSchlieen.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	btnSchlieen.setBounds(749, 475, 121, 23);
	contentPane.add(btnSchlieen);

	btnWeiter = new JButton("Weiter");
	btnWeiter.setBounds(280, 310, 89, 23);
	contentPane.add(btnWeiter);

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
	btnStart.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		 start = LocalDateTime.now();
		 uhrzeit.zeitEintragen(tfStartZeit);
		 btnStart.setVisible(false);
	    }
	});
	btnStart.setBounds(170, 201, 212, 23);
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

	uhrzeit = new Uhrzeit(tfZeit, tfStartZeit);
	Thread t = new Thread(uhrzeit);
	t.start();
	
	

    }
    public double getGearbeiteZeitInSekunden() {
	    return gearbeiteteZeit;
	}
}
