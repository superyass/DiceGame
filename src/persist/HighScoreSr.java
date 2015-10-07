package persist;

import Core.Entry;
import Core.HighScore;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class HighScoreSr extends HighScore {

    private final String filename = "highscores.sr";

    public HighScoreSr() {
        //create file if not exist
        File yourFile = new File(filename);
        if (!yourFile.exists()) {
            try {
                yourFile.createNewFile();
                System.out.println("file created Sr");
            } catch (IOException ex) {
                System.out.println("cannot create file "+ex.getMessage());
            }
        }
    }

    public boolean clearFile() {
        File f = new File(filename);
        return f.delete();
    }

    @Override
    public void save() {
        try {
            FileOutputStream ostream = new FileOutputStream(filename);
            ObjectOutputStream p = new ObjectOutputStream(ostream);

            p.writeObject(this.entries); 
            p.flush();
            p.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void load() {
        ObjectInputStream q = null;
        try {
            FileInputStream istream = new FileInputStream(filename);
            
            if(istream.available()>0){
                q = new ObjectInputStream(istream);
                this.entries = (Vector<Entry>) q.readObject();
            }
            sortEntries();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (q != null) {
                try {
                    q.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
