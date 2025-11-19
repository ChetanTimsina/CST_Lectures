import javax.swing.*;

public class App {
    public static void main(String[] args) {
        // Connect to the database
        Database db = new Database();
        db.connect_db();
        
        // Synchronize data from file to database
        db.readFileAndInsertData();
        
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new MusicPlayerGUI().setVisible(true);

//                Song song = new Song("src/assets/Wind Riders - Asher Fulero.mp3");
//                System.out.println(song.getSongTitle());
//                System.out.println(song.getSongArtist());
            }
        });
    }
}