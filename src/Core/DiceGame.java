package Core;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DiceGame extends Observable {

    private static DiceGame instance = null;
    private Player p;
    private Die d1;
    private Die d2;
    private HighScore highScore;
    public static int turn = 0;

    private DiceGame() {

    }

    public static DiceGame getInstance(Player p, Die d1, Die d2, HighScore highScore) {
        if (instance == null) {
            instance = new DiceGame();
            instance.d1 = d1;
            instance.d2 = d2;
        }
        //reset the player (score) each tour
        instance.p = p;
        turn=0;
        instance.highScore = highScore;
        return instance;
    }

    public void play() {
        if (turn < 10) {
            if (instance.d1.roll() + instance.d2.roll() == 7) {
                instance.p.setScore(instance.p.getScore() + 10);
            }
            turn++;

            //in the 10th tour add an entry if score!=0, the score is the highest for the player or the player doesnt exist
            if (turn == 10 && instance.p.getScore() != 0
                    && (instance.highScore.getElementByName(instance.p.getName()) == null 
                        || instance.highScore.getElementByName(instance.p.getName()).getScore() < instance.getP().getScore())) {
                instance.highScore.add(new Entry(instance.p.getName(), instance.p.getScore()));
                instance.highScore.save();
                JOptionPane.showMessageDialog(null, "Congrats, you broke your record!");
            }
            
            this.setChanged();
            this.notifyObservers();
            
            if(turn==10)
                JOptionPane.showMessageDialog(null, "Tour Finished");
            
        }
    }

    public boolean isPlayerExist(String name) {
        for (int i = 0; i < instance.highScore.getSize(); i++) {
            Entry en = instance.highScore.getElementAt(i);
            if (en.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    class DiceGameController extends JButton implements Observer {

        public DiceGameController() {
            this.setText("ROLL");
            this.setOpaque(false);
            this.setFont(new java.awt.Font("Tahoma", 1, 16));
            this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            this.setContentAreaFilled(false);
            this.setForeground(new java.awt.Color(0, 102, 51));

            this.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DiceGame.this.play();
                }
            });
        }

        public void update(Observable o, Object arg) {
            this.setText("ROLL: " + turn);
            this.setEnabled(turn < 10);
            
        }

    }

    public JComponent Display() {

        JPanel content = new JPanel();

        content.setLayout(null);
        content.setBackground(Color.white);
        
        JPanel die1 = new JPanel();
        die1.setOpaque(false);
        die1.add(instance.d1.display());
        content.add(die1);
        die1.setBounds(10, 90, 120, 150);

        JPanel die2 = new JPanel();
        die2.setOpaque(false);
        die2.add(instance.d2.display());
        content.add(die2);
        die2.setBounds(150, 90, 120, 150);

        //roll button
        JPanel rollButtonPanel = new JPanel();
        rollButtonPanel.setOpaque(false);
        DiceGameController dgc = new DiceGameController();
        this.addObserver(dgc);
        rollButtonPanel.add(dgc);
        rollButtonPanel.setBounds(105, 372, 80, 40);
        content.add(rollButtonPanel);
        
        //scorePanel classment
        JPanel score = new JPanel();
        score.add(instance.highScore.display());
        score.setBounds(0, 300, 285, 70);
        score.setBackground(Color.lightGray);
        content.add(score);

        //playview : score and name
        JPanel playViewPanel = new JPanel();
        playViewPanel.setOpaque(false);
        playViewPanel.setBackground(Color.white);
        playViewPanel.add(instance.p.display());
        content.add(playViewPanel);
        playViewPanel.setBounds(0, 2, 170, 80);

        return content;
    }



    public Player getP() {
        return p;
    }

    public Die getD1() {
        return d1;
    }

    public Die getD2() {
        return d2;
    }

    public HighScore getHighScore() {
        return highScore;
    }

    public void setHighScore(HighScore highScore) {
        this.highScore = highScore;
    }
}
