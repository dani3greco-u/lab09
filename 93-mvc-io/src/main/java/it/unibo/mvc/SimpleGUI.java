package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {
    private static final String TITLE = "SimpleGUI";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);

    /**
     * Creates a new SimpleGUI.
     */
    public SimpleGUI() {
        final Controller controller = new SimpleController();
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        final JTextField field = new JTextField();
        mainPanel.add(field, BorderLayout.NORTH);
        /* 
        * Add a JTextArea in a JScrollPane
        */
        final JTextArea text = new JTextArea();
        final JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        /* 
        * panel for the button
        */
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        final JButton print = new JButton("Print");
        buttonPanel.add(print); 
        final JButton history = new JButton("Show history");
        buttonPanel.add(history); 
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Handlers
         */
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setNextString(field.getText());
                controller.printCurrentString();
                field.setText("");
            }
        });

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                text.setText(String.join("\n", controller.getHistory()));
            }
        });
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
       new SimpleGUI().display();
    }

}

