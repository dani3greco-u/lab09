package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private static final String TITLE = "Simple GUI With FileChooser";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);

    public SimpleGUIWithFileChooser() {
        Controller controller = new Controller();
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        final JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new BorderLayout());
        mainPanel.add(secondPanel, BorderLayout.NORTH);
        /*
        * Text Area 
        */
        JTextArea text = new JTextArea();
        /*
        * Path field
        */
        JTextField field = new JTextField();
        field.setText(controller.getCurrentFilePath());
        field.setEnabled(false);
        /*
        * browse button
        */
        JButton browse = new JButton("Browse");
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ignored) {
                final JFileChooser fc = new JFileChooser(controller.getCurrentFile().getParent());
                fc.setSelectedFile(controller.getCurrentFile());
                switch (fc.showSaveDialog(frame)) {
                case JFileChooser.APPROVE_OPTION:
                    final File newFile = fc.getSelectedFile();
                    controller.setCurrentFile(newFile);
                    field.setText(controller.getCurrentFilePath());
                    break;
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        /*
        * Save button 
        */
        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ignored) {
                try {
                    controller.writeOnCurrentFile(text.getText());
                } catch (final IOException e) {
                    JOptionPane.showMessageDialog(frame, e, "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); // NOPMD
                }
            }
        });
        mainPanel.add(text, BorderLayout.CENTER);
        mainPanel.add(save, BorderLayout.SOUTH);
        secondPanel.add(field, BorderLayout.CENTER);
        secondPanel.add(browse, BorderLayout.LINE_END);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     *
     * @param args ignored
     */
    public static void main(final String... args) {
       new SimpleGUIWithFileChooser().display();
    }
}


