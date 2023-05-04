import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D) g;
        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLACK);
        g.fillRect(Var.lbl1.getX(),Var.lbl1.getY(),Var.lbl1.getWidth(),Var.jf1.getHeight());


}}

