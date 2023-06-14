import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AuswahlGUI extends JFrame implements ActionListener {
    private JButton button1, button2, button3, button4;

    public AuswahlGUI() {
        setTitle("AuswahlGUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.setBackground(Color.LIGHT_GRAY);

        button1 = new JButton("Button 1");
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

        button2 = new JButton("Button 2");
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

        button3 = new JButton("Button 3");
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

        button4 = new JButton("Button 4");
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
        StempelView.main();
    }

    public void method2() {
        // Platzhalter-Methodenaufruf für Button 2
    }

    public void method3() {
        // Platzhalter-Methodenaufruf für Button 3
    }

    public void method4() {
       Gui.main();
    }

    public static void main() {
        MyGUI gui = new MyGUI();
    }
}
