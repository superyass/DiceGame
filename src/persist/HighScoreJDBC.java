package persist;

import Core.Entry;
import Core.HighScore;
import java.sql.ResultSet;
import java.util.Enumeration;
import javax.swing.event.ListDataListener;

public class HighScoreJDBC extends HighScore {

    private final String SERVEUR = "localhost";
    private final String USER = "root";
    private final String PASSWORD = "tooro";
    private final String BDD = "dicegame";

    private SQLHelper sql = null;

    public HighScoreJDBC() {
        sql = new SQLHelper(SERVEUR, USER, PASSWORD, BDD);
    }

    public void load() {
        sql.connexion();

        try {
            String SELECT = "SELECT * FROM highscore";
            sql.executeRequest(SELECT);
            ResultSet rs;
            while ((rs = sql.fetchArray()) != null) {
                this.add(new Entry(rs.getString(1), rs.getInt(2)));
            }
            sortEntries();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        System.out.println("save it");
        sql.connexion();
        String DELETE = "TRUNCATE TABLE highscore";
        boolean b = sql.executeRequest(DELETE);
        System.out.println("truncate "+b);

        for (Enumeration e = entries.elements(); e.hasMoreElements();) {
            Entry entry = (Entry) e.nextElement();
            
            String INSERT = "INSERT INTO highscore (name, Score) VALUES ('" + entry.getName() + "', " + entry.getScore() + ");";
            sql.executeRequest(INSERT);
        }
        sql.close();
    }

    
}
