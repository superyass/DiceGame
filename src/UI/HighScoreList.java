package UI;

import Core.DiceGame;
import Core.Entry;
import Core.HighScore;
import java.awt.Color;
import persist.PersistKit;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import persist.JDBCKit;
import persist.MongoKit;
import persist.Srkit;
import persist.Xmlkit;

public class HighScoreList {

    private HighScore highScore;
    JPanel content = new JPanel();
    JLabel jLabel1 = new JLabel();
    JPanel h = new JPanel();
    JTable table;

    public HighScoreList() {

        updateContent();
    }

    public void updateContent() {

        JPanel highScorePanel = new JPanel();
        highScorePanel.setLayout(new GridLayout(0, 3));

        Vector<Vector<String>> out = new Vector<Vector<String>>();

        Vector<String> columnNames = new Vector<String>();
        columnNames.add(0, "Name");
        columnNames.add(1, "Score");
        columnNames.add(2, "Kit");

        

        //load database
        PersistKit pk = new JDBCKit();
        HighScore highscore;
        highscore = pk.makeKit();
        highscore.load();
        Vector<Entry> e = highscore.entries;

        int t = 0;
        for (Entry entry : e) {
            if (t == 20) {
                break;
            }
            Vector<String> vectorEntry = new Vector<String>();
            vectorEntry.add(entry.getName());
            vectorEntry.add("" + entry.getScore());
            vectorEntry.add("DATABASE");
            out.add(vectorEntry);
            t++;
        }
        
        //load xml
        pk = new Xmlkit();
        highscore = pk.makeKit();
        highscore.load();
        e = highscore.entries;

        t = 0;
        for (Entry entry : e) {
            if (t == 20) {
                break;
            }
            Vector<String> vectorEntry = new Vector<String>();
            vectorEntry.add(entry.getName());
            vectorEntry.add("" + entry.getScore());
            vectorEntry.add("XML");
            out.add(vectorEntry);
            t++;
        }

        //load file
        pk = new Srkit();
        highscore = pk.makeKit();
        highscore.load();
        e = highscore.entries;

        t = 0;
        for (Entry entry : e) {
            if (t == 20) {
                break;
            }
            Vector<String> vectorEntry = new Vector<String>();
            vectorEntry.add(entry.getName());
            vectorEntry.add("" + entry.getScore());
            vectorEntry.add("FILE");
            out.add(vectorEntry);
            t++;
        }
        
        //load mongo
        pk = new MongoKit();
        highscore = pk.makeKit();
        highscore.load();
        e = highscore.entries;

        t = 0;
        for (Entry entry : e) {
            if (t == 20) {
                break;
            }
            Vector<String> vectorEntry = new Vector<String>();
            vectorEntry.add(entry.getName());
            vectorEntry.add("" + entry.getScore());
            vectorEntry.add("MongoDB");
            out.add(vectorEntry);
            t++;
        }

        table = new JTable(out, columnNames);
        table.setEnabled(false);

    }

    public JComponent Display() {

        h.add(table);
        content.add(h);
        h.setBounds(12, 120, 260, 180);
        content.setBackground(Color.white);
        return content;

    }

    private void cancel() {
        System.out.println("cancel");
        System.exit(0);
    }

}
