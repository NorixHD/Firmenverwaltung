package de.lelonek.unternehmensverwaltung;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageDragAndDrop extends JFrame {

    private JLabel label;
    private JButton btnLoeschen;
    private JButton btnSchliessen;

    public ImageDragAndDrop() {
        setTitle("Bild eines Mitarbeiters einf\u00FCgen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(400, 400));

        btnLoeschen = new JButton("Bild L\u00F6schen");
        btnLoeschen.setForeground(new Color(100, 149, 237));
        btnLoeschen.addActionListener(e -> {
            label.setIcon(null); // remove the image icon from the label
            label.setText("F�gen Sie hier ein Bild eines Mitarbeiters ein"); // set the label text back to the default
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLoeschen);

        getContentPane().add(label, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        btnSchliessen = new JButton("Schlie\u00DFen");
        btnSchliessen.setForeground(new Color(100, 149, 237));
        btnSchliessen.addActionListener(e -> {
            dispose(); // close the frame
        });
        buttonPanel.add(btnSchliessen);

        setDropTarget(new DropTarget(label, new ImageDropTargetAdapter()));
    }

    private class ImageDropTargetAdapter extends DropTargetAdapter {

        @Override
        public void drop(DropTargetDropEvent event) {
            event.acceptDrop(DnDConstants.ACTION_COPY);
            Transferable transferable = event.getTransferable();
            if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                try {
                    Object transferData = transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    if (transferData instanceof java.util.List) {
                        java.util.List fileList = (java.util.List) transferData;
                        for (Object file : fileList) {
                            if (file instanceof File) {
                                File droppedFile = (File) file;
                                if (droppedFile.getName().toLowerCase().endsWith(".jpg") ||
                                    droppedFile.getName().toLowerCase().endsWith(".jpeg") ||
                                    droppedFile.getName().toLowerCase().endsWith(".png")) {
                                    BufferedImage image = ImageIO.read(droppedFile);
                                    if (image != null) {
                                        label.setIcon(new ImageIcon(image));
                                        label.setText(""); // remove the default text
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main() {
        ImageDragAndDrop frame = new ImageDragAndDrop();
        frame.setVisible(true);
    }
}