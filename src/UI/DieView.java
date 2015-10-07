package UI;

import Core.Die;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class DieView extends Panel implements Observer {

    private JLabel d;
    private Die die;

    public DieView(Die die) {
        d = new JLabel(new ImageIcon(getClass().getResource("/images/" + die.getFaceValue() + ".jpg")));

        this.add(d);

        this.setBackground(Color.white);
        // this.setForeground(Color.WHITE);

    }

    @Override
    public void update(Observable o, Object o1) {

        d.setIcon(new ImageIcon(getClass().getResource("/images/" + ((Die) o).getFaceValue() + ".jpg")));

    }

}
