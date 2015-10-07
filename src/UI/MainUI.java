package UI;

import Core.DiceGame;
import Core.Die;
import Core.HighScore;
import Core.Player;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import persist.JDBCKit;
import persist.PersistKit;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import persist.MongoKit;
import persist.Srkit;
import persist.Xmlkit;

public class MainUI extends JFrame implements ActionListener {

    private JButton database = new JButton("DATABASE");
    private JButton file = new JButton("FILE");
    private JButton xml = new JButton("XML");
    private JButton mongo = new JButton("MongoDB"); //******
    private JButton highscore = new JButton("HighScores");
    private JLabel name;

    public MainUI() {

        setBackground(Color.white);
        setResizable(false);

        database.addActionListener(this);
        file.addActionListener(this);
        xml.addActionListener(this);
        mongo.addActionListener(this);
    }

    public JComponent Display() {
        JPanel content = new JPanel();
//        JPanel content2 = new JPanel();
        content.setBackground(Color.white);
//        content2.setBackground(Color.white);

        content.setLayout(null);
//        content2.setLayout(null);

        JLabel jLabel1 = new JLabel("Choose a persistence mode");
        jLabel1.setBounds(10, 65, 200, 40);
        content.add(jLabel1);

        JLabel jLabel2 = new JLabel("Global HighScores");
        jLabel2.setBounds(10, 280, 200, 40);
        content.add(jLabel2);

        database.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        content.add(database);
        database.setBounds(10, 100, 200, 40);

        xml.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        content.add(xml);
        xml.setBounds(10, 145, 200, 40);

        file.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        content.add(file);
        file.setBounds(10, 190, 200, 40);

        mongo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        content.add(mongo);
        mongo.setBounds(10, 235, 200, 40);

        highscore.setBounds(10, 310, 200, 40);
        highscore.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        content.add(highscore);
        highscore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                MainUI.this.showHighScoreList();
            }
        });

        content.setBorder(BorderFactory.createTitledBorder("GameDice!"));
//        content2.add(content);
//        content2.setBounds(0, 0, 200, 200);
        return content;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        Player p = null;

        //init Dialogue options
        String[] options = {"OK"};
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Enter Your name: ");
        JTextField txt = new JTextField(10);
        txt.setText("Player");
        panel.add(lbl);
        panel.add(txt);
        //init options

        //choose name for the player
        int selectedOption = JOptionPane.showOptionDialog(null, panel, "Dice Game", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        //retreive the name
        String text = txt.getText();
        if (selectedOption == 0) {
            if ((text != null) && (text.length() > 0)) {
                p = new Player(text);
            } else {
                p = new Player("Player");
            }
        } else {
            return;
        }

        if (ae.getSource() == database) {
            basedonnees(p);
        } else if (ae.getSource() == file) {
            fichier(p);
        } else if (ae.getSource() == xml) {
            xml(p);
        }else if (ae.getSource() == mongo) {
            mongo(p);
        }
    }

    private void xml(Player p) {

        PersistKit pk = new Xmlkit();
        HighScore highscore;
        highscore = pk.makeKit();
        highscore.load();
        Die d1 = new Die();
        Die d2 = new Die();

        DiceGame dg = DiceGame.getInstance(p, d1, d2, highscore);

        JFrame f1 = new JFrame();
        f1.setLocation(520, 180);
        f1.setTitle("Dice Game");
        f1.add(dg.Display());
        f1.pack();
        f1.setSize(300, 450);
        f1.setVisible(true);
    }

    private void fichier(Player p) {
        PersistKit pk = new Srkit();
        HighScore highscore;
        highscore = pk.makeKit();
        highscore.load();
        Die d1 = new Die();
        Die d2 = new Die();

        DiceGame dg = DiceGame.getInstance(p, d1, d2, highscore);
        JFrame f1 = new JFrame();

        f1.setLocation(520, 180);
        f1.setTitle("Dice Game");
        f1.add(dg.Display());
        f1.pack();
        f1.setSize(300, 450);
        f1.setVisible(true);
    }

    private void basedonnees(Player p) {

        PersistKit pk = new JDBCKit();
        HighScore highscore;
        highscore = pk.makeKit();
        highscore.load();
        Die d1 = new Die();
        Die d2 = new Die();

        DiceGame dg = DiceGame.getInstance(p, d1, d2, highscore);

        JFrame f1 = new JFrame();
        f1.setLocation(520, 180);
        f1.setTitle("Dice Game");
        f1.add(dg.Display());
        f1.pack();
        f1.setSize(300, 450);
        f1.setVisible(true);

    }

    private void mongo(Player p) {

        PersistKit pk = new MongoKit();
        HighScore highscore;
        highscore = pk.makeKit();
        highscore.load();
        Die d1 = new Die();
        Die d2 = new Die();

        DiceGame dg = DiceGame.getInstance(p, d1, d2, highscore);

        JFrame f1 = new JFrame();
        f1.setLocation(520, 180);
        f1.setTitle("Dice Game");
        f1.add(dg.Display());
        f1.pack();
        f1.setSize(300, 450);
        f1.setVisible(true);

    }

    private void showHighScoreList() {
        JFrame f = new JFrame();
        HighScoreList po = new HighScoreList();
        f.setLocation(520, 180);
        f.setTitle("Dice Game");
        f.add(po.Display());
        f.pack();
        f.setSize(250, 300);
        f.setVisible(true);
        f.setResizable(false);
    }

}
