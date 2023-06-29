import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

class AuswahlGUI extends JFrame implements ActionListener {
    private JButton button1, button2, button3, button4;

    public AuswahlGUI() {
        setTitle("AuswahlGUI");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Hier wird die Methode aufgerufen, wenn das JFrame geschlossen wird


                try {
                    Main.main(new String[0]);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });








        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.setBackground(Color.LIGHT_GRAY);

        button1 = new JButton("Stempeln");
        button1.addActionListener(this);
        button1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button1.setBackground(Color.RED);
            }
            public void mouseExited(MouseEvent evt) {
                button1.setBackground(null);
            }
        });
        panel.add(button1);

        button2 = new JButton("Aufträge erstellen");
        button2.addActionListener(this);
        button2.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button2.setBackground(Color.RED);
            }
            public void mouseExited(MouseEvent evt) {
                button2.setBackground(null);
            }
        });
        panel.add(button2);

        button3 = new JButton("Aufträge Anzeigen    ");
        button3.addActionListener(this);
        button3.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button3.setBackground(Color.RED);
            }
            public void mouseExited(MouseEvent evt) {
                button3.setBackground(null);
            }
        });
        panel.add(button3);

        button4 = new JButton("Mitarbeiterverwaltung");
        button4.addActionListener(this);
        button4.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button4.setBackground(Color.RED);
            }
            public void mouseExited(MouseEvent evt) {
                button4.setBackground(null);
            }
        });
        panel.add(button4);

        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        setBounds(width/4, height/4, width/2, height/2);

        setContentPane(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            method1();
        } else if (e.getSource() == button2) {
            method2();
        } else if (e.getSource() == button3) {
            method3();
        } else if (e.getSource() == button4) {
            method4();
        }
    }

    public void method1() {
        Stempel.main();
    }

    public void method2() {
       AuftragsverwaltungGUI.main();
    }

    public void method3() {
        TextdateiAnzeiger.main();
    }

    public void method4() {
        if(Var.Admin=true){
            Personenverwaltung.main();
        }
        else {
            JOptionPane.showMessageDialog(null, "Keine Rechte für dieses Panel");
        }

    }

    public static void main() {
        AuswahlGUI gui = new AuswahlGUI();
    }
}
