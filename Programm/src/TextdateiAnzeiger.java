

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextdateiAnzeiger {
    private JFrame frame;
    private JTextArea textArea;

    public static void main() {
        TextdateiAnzeiger anzeiger = new TextdateiAnzeiger();
        anzeiger.start();
    }

    public  void start() {


        frame = new JFrame("Textdatei Anzeiger");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Textbereich für den Inhalt
        textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        // Button zum Laden der Datei
        JButton ladenButton = new JButton("Datei laden");
        ladenButton.addActionListener(new LadenButtonListener());

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(ladenButton, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    private void ladenDatei() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:\\Users\\Stefan\\OneDrive - Johannes-Heidenhain-Gymnasium Traunreut\\Dokumente\\11. Klasse\\Info\\Projekt\\Programm\\Auftrag0.txt"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Textdateien", "txt"));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                StringBuilder content = new StringBuilder();
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
                textArea.setText(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    private class LadenButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            ladenDatei();
        }
    }
}
