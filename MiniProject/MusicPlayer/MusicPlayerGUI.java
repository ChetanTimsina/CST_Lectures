import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Hashtable;

public class MusicPlayerGUI extends JFrame {
    public static final Color FRAME_COLOR = null;
    private Color frameColor = Color.BLACK;
    private Color textColor = Color.WHITE;

    private MusicPlayer musicPlayer;

    private JFileChooser jFileChooser;

    private JLabel songTitle, songArtist;
    private JPanel playbackBtns;
    private JSlider playbackSlider;

    private ArrayList<Song> favoriteSongs;

    public MusicPlayerGUI() {
        super("Music Player");
    
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
    
        musicPlayer = new MusicPlayer(this);
        jFileChooser = new JFileChooser();
    
        jFileChooser.setCurrentDirectory(new File("assets"));
        jFileChooser.setFileFilter(new FileNameExtensionFilter("MP3", "mp3"));
    
        favoriteSongs = new ArrayList<>();
    
        addGuiComponents();
    }
    

    private void addGuiComponents() {
        addToolbar();
    
        // Load default song image
        JLabel songImage = new JLabel(new ImageIcon("assets/record.gif"));
        songImage.setBounds(0, 60, getWidth() - 20, 240);
        add(songImage);
    
        JButton changeImageButton = new JButton("Change Image");
        changeImageButton.setBounds(120, 310, 150, 30);
        changeImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser imageChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
                imageChooser.setFileFilter(filter);
                int returnVal = imageChooser.showOpenDialog(MusicPlayerGUI.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = imageChooser.getSelectedFile();
                    if (selectedFile != null) {
                        // Load user-selected image
                        ImageIcon newImageIcon = new ImageIcon(selectedFile.getPath());
                        songImage.setIcon(newImageIcon);
                    }
                }
            }
        });
        add(changeImageButton);
    
        songTitle = new JLabel("Song Title");
        songTitle.setBounds(0, 350, getWidth() - 10, 30);
        songTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        songTitle.setForeground(textColor);
        songTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(songTitle);
    
        songArtist = new JLabel("Artist");
        songArtist.setBounds(0, 390, getWidth() - 10, 30);
        songArtist.setFont(new Font("Dialog", Font.PLAIN, 24));
        songArtist.setForeground(textColor);
        songArtist.setHorizontalAlignment(SwingConstants.CENTER);
        add(songArtist);
    
        playbackSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        playbackSlider.setBounds(getWidth() / 2 - 300 / 2, 430, 300, 40);
        playbackSlider.setBackground(null);
        playbackSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                musicPlayer.pauseSong();
            }
    
            @Override
            public void mouseReleased(MouseEvent e) {
                JSlider source = (JSlider) e.getSource();
                int frame = source.getValue();
                musicPlayer.setCurrentFrame(frame);
                musicPlayer.setCurrentTimeInMilli((int) (frame / (2.08 * musicPlayer.getCurrentSong().getFrameRatePerMilliseconds())));
                musicPlayer.playCurrentSong();
                enablePauseButtonDisablePlayButton();
            }
        });
        add(playbackSlider);
    
        addPlaybackBtns();
    
        ImageIcon icon1 = new ImageIcon("assets/favorite.png");
        ImageIcon icon2 = new ImageIcon("assets/fav.png");
    
        JButton addToFavoritesButton = new JButton(icon1);
        addToFavoritesButton.setBounds(270, 500, 150, 50);
        addToFavoritesButton.setBorderPainted(false);
        addToFavoritesButton.setBackground(null);
    
        addToFavoritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToFavoritesButton.setIcon(icon2);
    
                Song currentSong = musicPlayer.getCurrentSong();
                if (currentSong != null && !favoriteSongs.contains(currentSong)) {
                    favoriteSongs.add(currentSong);
                    JOptionPane.showMessageDialog(MusicPlayerGUI.this, "Added to Favorites!");
                    try {
                        File sourceFile = new File(currentSong.getFilePath());
                        File targetFile = new File("favorites/" + sourceFile.getName());
                        targetFile.getParentFile().mkdirs();
                        Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(MusicPlayerGUI.this, "Song is already in Favorites or no song playing!");
                }
                addToFavoritesButton.setIcon(icon1);
            }
        });
        add(addToFavoritesButton);
    }
    

    private void addToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setBounds(0, 0, getWidth(), 20);
        toolBar.setFloatable(false);

        JMenuBar menuBar = new JMenuBar();
        toolBar.add(menuBar);

        JMenu songMenu = new JMenu("All Songs");
        menuBar.add(songMenu);

        JMenuItem loadSong = new JMenuItem("Load Song");
        loadSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = jFileChooser.showOpenDialog(MusicPlayerGUI.this);
                File selectedFile = jFileChooser.getSelectedFile();

                if (result == JFileChooser.APPROVE_OPTION && selectedFile != null) {
                    Song song = new Song(selectedFile.getPath());
                    musicPlayer.loadSong(song);
                    updateSongTitleAndArtist(song);
                    updatePlaybackSlider(song);
                    enablePauseButtonDisablePlayButton();
                }
            }
        });
        songMenu.add(loadSong);

        JMenu playlistMenu = new JMenu("Playlist");
        menuBar.add(playlistMenu);

        JMenuItem createPlaylist = new JMenuItem("Create Playlist");
        createPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MusicPlaylistDialog(MusicPlayerGUI.this).setVisible(true);
            }
        });
        playlistMenu.add(createPlaylist);

        JMenuItem loadPlaylist = new JMenuItem("Load Playlist");
        loadPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileFilter(new FileNameExtensionFilter("Playlist", "txt"));
                jFileChooser.setCurrentDirectory(new File("assets"));

                int result = jFileChooser.showOpenDialog(MusicPlayerGUI.this);
                File selectedFile = jFileChooser.getSelectedFile();

                if (result == JFileChooser.APPROVE_OPTION && selectedFile != null) {
                    musicPlayer.stopSong();
                    musicPlayer.loadPlaylist(selectedFile);
                }
            }
        });
        playlistMenu.add(loadPlaylist);

        JMenu favoritesMenu = new JMenu("Favorites");
        menuBar.add(favoritesMenu);

        JMenuItem viewFavorites = new JMenuItem("View Favorite Songs");
        viewFavorites.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!favoriteSongs.isEmpty()) {
                    JFrame favoriteSongsFrame = new JFrame("Favorite Songs");
                    favoriteSongsFrame.setSize(400, 200);
                    favoriteSongsFrame.setLayout(new BorderLayout());
                    favoriteSongsFrame.getContentPane().setBackground(frameColor);

                    JTextArea favoriteSongsTextArea = new JTextArea();
                    favoriteSongsTextArea.setEditable(false);
                    favoriteSongsTextArea.setFont(new Font("Dialog", Font.PLAIN, 14));
                    favoriteSongsTextArea.setForeground(textColor);
                    favoriteSongsTextArea.setBackground(frameColor);

                    StringBuilder favoriteSongsList = new StringBuilder("Favorite Songs:\n");
                    for (Song song : favoriteSongs) {
                        favoriteSongsList.append(song.getSongTitle()).append(" - ").append(song.getSongArtist()).append("\n");
                    }
                    favoriteSongsTextArea.setText(favoriteSongsList.toString());

                    favoriteSongsFrame.add(favoriteSongsTextArea, BorderLayout.CENTER);
                    favoriteSongsFrame.setLocationRelativeTo(null);
                    favoriteSongsFrame.setVisible(true);
                }
            }
        });
        favoritesMenu.add(viewFavorites);

        add(toolBar);

        JMenu settingsMenu = new JMenu("Settings");
        menuBar.add(settingsMenu);

        JMenuItem changeFrameColor = new JMenuItem("Change Frame Color");
        changeFrameColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(MusicPlayerGUI.this, "Choose Frame Color", frameColor);
                if (newColor != null) {
                    frameColor = newColor;
                    getContentPane().setBackground(frameColor);
                }
            }
        });
        settingsMenu.add(changeFrameColor);

        JMenuItem changeTextColor = new JMenuItem("Change Text Color");
        changeTextColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(MusicPlayerGUI.this, "Choose Text Color", textColor);
                if (newColor != null) {
                    textColor = newColor;
                    songTitle.setForeground(textColor);
                    songArtist.setForeground(textColor);
                }
            }
        });
        settingsMenu.add(changeTextColor);

        JMenuItem changeBackgroundImage = new JMenuItem("Change Background Image");
        changeBackgroundImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = jFileChooser.showOpenDialog(MusicPlayerGUI.this);
                File selectedFile = jFileChooser.getSelectedFile();

                if (result == JFileChooser.APPROVE_OPTION && selectedFile != null) {
                    try {
                        BufferedImage backgroundImage = ImageIO.read(selectedFile);
                        setContentPane(new JLabel(new ImageIcon(backgroundImage)));
                        getContentPane().setLayout(null);
                        repaint();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        settingsMenu.add(changeBackgroundImage);
    }

    private void addPlaybackBtns() {
        playbackBtns = new JPanel();
        playbackBtns.setBounds(50, 450, getWidth() - 120, 80);
        playbackBtns.setBackground(null);

        JButton prevButton = new JButton(loadImage("assets/previous.png"));
        prevButton.setBorderPainted(false);
        prevButton.setBackground(null);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musicPlayer.prevSong();
            }
        });
        playbackBtns.add(prevButton);

        JButton playButton = new JButton(loadImage("assets/play.png"));
        playButton.setBorderPainted(false);
        playButton.setBackground(null);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enablePauseButtonDisablePlayButton();
                musicPlayer.playCurrentSong();
            }
        });
        playbackBtns.add(playButton);

        JButton pauseButton = new JButton(loadImage("assets/pause.png"));
        pauseButton.setBorderPainted(false);
        pauseButton.setBackground(null);
        pauseButton.setVisible(false);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enablePlayButtonDisablePauseButton();
                musicPlayer.pauseSong();
            }
        });
        playbackBtns.add(pauseButton);

        JButton nextButton = new JButton(loadImage("assets/next.png"));
        nextButton.setBorderPainted(false);
        nextButton.setBackground(null);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                musicPlayer.nextSong();
            }
        });
        playbackBtns.add(nextButton);

        add(playbackBtns);
    }

    public void setPlaybackSliderValue(int frame) {
        playbackSlider.setValue(frame);
    }

    public void updateSongTitleAndArtist(Song song) {
        songTitle.setText(song.getSongTitle());
        songArtist.setText(song.getSongArtist());
    }

    public void updatePlaybackSlider(Song song) {
        playbackSlider.setMaximum(song.getMp3File().getFrameCount());

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        JLabel labelBeginning = new JLabel("00:00");
        labelBeginning.setFont(new Font("Dialog", Font.BOLD, 18));
        labelBeginning.setForeground(textColor);

        JLabel labelEnd = new JLabel(song.getSongLength());
        labelEnd.setFont(new Font("Dialog", Font.BOLD, 18));
        labelEnd.setForeground(textColor);

        labelTable.put(0, labelBeginning);
        labelTable.put(song.getMp3File().getFrameCount(), labelEnd);

        playbackSlider.setLabelTable(labelTable);
        playbackSlider.setPaintLabels(true);
    }

    public void enablePauseButtonDisablePlayButton() {
        JButton playButton = (JButton) playbackBtns.getComponent(1);
        JButton pauseButton = (JButton) playbackBtns.getComponent(2);

        playButton.setVisible(false);
        playButton.setEnabled(false);

        pauseButton.setVisible(true);
        pauseButton.setEnabled(true);
    }

    public void enablePlayButtonDisablePauseButton() {
        JButton playButton = (JButton) playbackBtns.getComponent(1);
        JButton pauseButton = (JButton) playbackBtns.getComponent(2);

        playButton.setVisible(true);
        playButton.setEnabled(true);

        pauseButton.setVisible(false);
        pauseButton.setEnabled(false);
    }

    private ImageIcon loadImage(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            return new ImageIcon(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
