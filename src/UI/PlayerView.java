package UI;

import Core.Player;
import java.awt.Color;
import java.awt.Panel;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import sun.awt.VerticalBagLayout;

public class PlayerView extends Panel implements Observer {

    private JLabel name;
    private JLabel score;
    private Player player;

    public PlayerView(Player player) {
//        setLayout(new VerticalBagLayout());
        name = new JLabel(player.getName() + "             ");
        score = new JLabel("score: " + player.getScore() + " ");
        name.setFont(new java.awt.Font("Tahoma", 1, 13)); //
//        name.setForeground(new java.awt.Color(255, 255, 255));
        score.setForeground(new java.awt.Color(0, 102, 51));
//        score.setBoundss(240, 10, 45, 30);
        this.add(name);
        this.add(score);
//        setBackground(Color.gray);
        setLocation(140, 140);
    }

    @Override
    public void update(Observable o, Object o1) {
        score.setText("score: " + ((Player) o).getScore() + " ");
    }

}
