package UI;

import java.awt.BorderLayout;
import Backend.Tabelleneintraege;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class UI extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JPanel panel;
    private JButton delete;
    private JButton add;

    public UI(Tabelleneintraege e) {
        
    }
    
    /**
     * Create the frame.
     * @wbp.parser.constructor
     */
    public UI(String [][] input) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        setGrakaTable(input);
        scrollPane.setViewportView(table);

        panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        delete = new JButton("Delete");
        panel.add(delete);

        add = new JButton("Add");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        panel.add(add);

    }

    
    
    public void setGrakaTable(String[][] input) {
        String[] names = { "Name", "VRAM", "Hersteller" };
        String[][] data = Arrays.copyOfRange(input, 1, input.length);
        setTable(input[0], data);
    }

    public void setTable(String[] names, String[][] data) {
        table = new JTable(data, names);
    }

}
