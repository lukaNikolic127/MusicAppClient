package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.communication.PerformerType;
import rs.ac.bg.fon.ps.domain.Album;
import rs.ac.bg.fon.ps.domain.Band;
import rs.ac.bg.fon.ps.domain.Genre;
import rs.ac.bg.fon.ps.domain.Performer;
import rs.ac.bg.fon.ps.domain.PerformerSong;
import rs.ac.bg.fon.ps.domain.Singer;
import rs.ac.bg.fon.ps.domain.Song;
import rs.ac.bg.fon.ps.view.constants.AlbumSongConstants;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmAlbumNew;
import rs.ac.bg.fon.ps.view.form.model.BandTableModel;
import rs.ac.bg.fon.ps.view.form.model.SingerTableModel;
import rs.ac.bg.fon.ps.view.form.model.SongTableModel;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

public class AlbumController {

    private final FrmAlbumNew form;
    private FormMode formMode;
    private Song songEdit;
    private Album album;

    public AlbumController(FrmAlbumNew form) {
        this.form = form;
    }

    public void openForm(FormMode mode) {
        try {
            formMode = mode;
            prepareForm(formMode);
            addActionListeners();
            form.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
            form.setVisible(true);
        } catch (IOException ex) {
            
            JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(form, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prepareForm(FormMode mode) throws Exception {
        form.getTxtNumberOfSongs().setEditable(false);
        form.getTxtAlbumDuration().setEditable(false);
        form.getTxtAlbumName().setEditable(true);
        fillCbGenre();
        prepareNewSongForm();

        switch (mode) {
            case FORM_ADD:
                form.setTitle("New album");
                setupTblSongs();
                form.getTxtAlbumDuration().setText(String.valueOf(0.0));
                form.getTxtNumberOfSongs().setText(String.valueOf(0));
                form.getBtnSaveSong().setEnabled(false);
                form.getBtnCancel().setEnabled(false);
                form.getBtnDeleteAlbum().setEnabled(false);
                break;
            case FORM_EDIT:
                form.setTitle("Edit album");

                this.album = (Album) MainCordinator.getInstance().getParam(Constants.PARAM_ALBUM);
                form.getTxtAlbumName().setText(album.getName());
                setupTblSongs(album.getSongs());
                form.getTxtAlbumDuration().setText(String.valueOf(album.getDuration()));
                form.getTxtNumberOfSongs().setText(String.valueOf(album.getNumberOfSongs()));
                form.getBtnSaveSong().setEnabled(false);
                form.getBtnCancel().setEnabled(false);
                form.getBtnDeleteAlbum().setEnabled(true);

                break;
        }
    }

    private void setupTblSongs() throws Exception {
        SongTableModel model = new SongTableModel();
        form.getTblSongs().setModel(model);
    }

    private void setupTblSongs(List<Song> songs) throws Exception {
        SongTableModel model = new SongTableModel(songs);
        form.getTblSongs().setModel(model);
    }

    private void setupTblBands() throws Exception {
        Band band = new Band();
        List<Performer> bands = Communication.getInstance().getAllPerformers(band);
        BandTableModel model = new BandTableModel(bands, null);
        form.getTblBands().setModel(model);
    }

    private void setupTblSingers() throws Exception {
        Singer singer = new Singer();
        List<Performer> singers = Communication.getInstance().getAllPerformers(singer);
        SingerTableModel model = new SingerTableModel(singers, null);
        form.getTblSingers().setModel(model);
    }

    private void fillCbGenre() throws Exception {
        List<Genre> genres = Communication.getInstance().getAllGenres();
        form.getCbGenre().removeAllItems();
        form.getCbGenre().setModel(new DefaultComboBoxModel<>(genres.toArray()));
    }

    private void prepareNewSongForm() {
        try {
            setupTblSingers();
            setupTblBands();
            fillCbGenre();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(form, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
        }
        form.getTxtSongName().setText("");
        form.getTxtSongDuration().setText("");
    }

    private void addActionListeners() {
        form.addBtnAddSingerActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = form.getTblSingers().getSelectedRow();
                SingerTableModel model = (SingerTableModel) form.getTblSingers().getModel();
                if (selectedRow >= 0) {
                    try {
                        model.selectPerformer(selectedRow);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(form, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(form, "No singer selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        form.addBtnRemoveSelectedSingerActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = form.getTblSingers().getSelectedRow();
                SingerTableModel model = (SingerTableModel) form.getTblSingers().getModel();
                if (selectedRow >= 0) {
                    try {
                        model.deselectPerformer(selectedRow);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(form, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(form, "No singer selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        form.addBtnAddBandActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = form.getTblBands().getSelectedRow();
                BandTableModel model = (BandTableModel) form.getTblBands().getModel();
                if (selectedRow >= 0) {
                    try {
                        model.selectBand(selectedRow);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(form, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(form, "No band selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        form.addBtnRemoveSelectedBandActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = form.getTblBands().getSelectedRow();
                BandTableModel model = (BandTableModel) form.getTblBands().getModel();
                if (selectedRow >= 0) {
                    try {
                        model.deselectBand(selectedRow);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(form, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(form, "No band selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        form.addBtnAddNewSongActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = form.getTxtSongName().getText();
                String durationStr = form.getTxtSongDuration().getText();
                if (validate(name, durationStr).isEmpty()) {
                    double duration = Double.parseDouble(durationStr);
                    duration = Math.round(duration * 100) / 100.0;
                    Genre genre = (Genre) form.getCbGenre().getSelectedItem();

                    Song song = new Song();
                    song.setName(name);
                    song.setDuration(duration);
                    song.setGenre(genre);
                    try {
                        song.setUser(Communication.getInstance().getUser());
                    } catch (IOException ex) {
                        form.dispose();
                        JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                    }

                    List<PerformerSong> performerSongs = new ArrayList<>();
                    SingerTableModel modelSingers = (SingerTableModel) form.getTblSingers().getModel();
                    BandTableModel modelBands = (BandTableModel) form.getTblBands().getModel();

                    for (Performer performer : modelSingers.getSelected()) {
                        PerformerSong ps = new PerformerSong(performer, song);
                        performerSongs.add(ps);
                    }

                    for (Performer performer : modelBands.getSelected()) {
                        PerformerSong ps = new PerformerSong(performer, song);
                        performerSongs.add(ps);
                    }

                    if (performerSongs.isEmpty()) {
                        JOptionPane.showMessageDialog(form, "Song must have at least one performer!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        song.setPerformerSongs(performerSongs);
                        SongTableModel model = (SongTableModel) form.getTblSongs().getModel();
                        try {
                            model.addSong(song);
                            form.getTxtAlbumDuration().setText(String.valueOf(model.getAlbumDuration()));
                            form.getTxtNumberOfSongs().setText(String.valueOf(model.getNumberOfSongs()));
                            prepareNewSongForm();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(form, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(form, validate(name, durationStr), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        form.addBtnDeleteSelectedSongActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = form.getTblSongs().getSelectedRow();
                SongTableModel model = (SongTableModel) form.getTblSongs().getModel();
                if (selectedRow >= 0) {
                    model.removeSong(selectedRow);
                    form.getTxtAlbumDuration().setText(String.valueOf(model.getAlbumDuration()));
                    form.getTxtNumberOfSongs().setText(String.valueOf(model.getNumberOfSongs()));
                } else {
                    JOptionPane.showMessageDialog(form, "No song selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        form.addBtnSaveAlbumActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String albumName = form.getTxtAlbumName().getText();
                if (validate(albumName).isEmpty()) {

                    SongTableModel model = (SongTableModel) form.getTblSongs().getModel();
                    if (model.getNumberOfSongs() == 0) {
                        JOptionPane.showMessageDialog(form, "Album must have at least one song!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {

                        Album album = new Album();
                        switch (formMode) {
                            case FORM_ADD:
                                break;
                            case FORM_EDIT:
                                Album albumEdit = (Album) MainCordinator.getInstance().getParam(Constants.PARAM_ALBUM);
                                album.setAlbumID(albumEdit.getAlbumID());
                                break;
                        }
                        album.setName(form.getTxtAlbumName().getText());
                        try {
                            album.setUser(Communication.getInstance().getUser());
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                        }
                        album.setNumberOfSongs(model.getNumberOfSongs());
                        album.setDuration(model.getAlbumDuration());
                        album.setSongs(model.getSongs());

                        MainCordinator.getInstance().addParam(Constants.PARAM_GENERIC_ENTITY, album);
                        MainCordinator.getInstance().openSaveConfirmForm(formMode);
                    }
                } else {
                    JOptionPane.showMessageDialog(form, validate(albumName), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            private String validate(String albumName) {
                String errorMessage = "";
                if (albumName.isEmpty()) {
                    errorMessage += "Album's name is required!\n";
                    form.getLblAlbumNameError().setText("* Required!");
                } else if (albumName.length() < 2 || albumName.length() > 30) {
                    errorMessage += "Album's name must have between 2 and 20 characters!\n";
                    form.getLblAlbumNameError().setText("* Incorrect name");
                } else if (!Pattern.matches("[A-ZŠĐŽČĆ](\\s?[A-Za-z0-9šđžčć]+)*", albumName)) {
                    errorMessage += "Incorrect name format!\n";
                    form.getLblAlbumNameError().setText("* Incorrect name");
                } else {
                    form.getLblAlbumNameError().setText("");
                }
                return errorMessage;
            }
        });

        form.addBtnEditSongActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SongTableModel model = (SongTableModel) form.getTblSongs().getModel();
                int selectedRow = form.getTblSongs().getSelectedRow();
                if (selectedRow >= 0) {
                    Song song = model.getSong(selectedRow);
                    songEdit = song;
                    form.getBtnEditSong().setEnabled(false);
                    form.getBtnSaveAlbum().setEnabled(false);
                    form.getBtnSaveSong().setEnabled(true);
                    form.getBtnCancel().setEnabled(true);
                    form.getBtnAddNewSong().setEnabled(false);
                    form.getBtnDeleteSelectedSong().setEnabled(false);

                    form.getTxtSongName().setText(song.getName());
                    form.getTxtSongDuration().setText(String.valueOf(song.getDuration()));
                    Genre genre = song.getGenre();
                    form.getCbGenre().setSelectedItem(genre);

                    List<Performer> singers = new ArrayList<>();
                    List<Performer> bands = new ArrayList<>();

                    for (PerformerSong performerSong : songEdit.getPerformerSongs()) {
                        if (performerSong.getPerformer().getType().equals(PerformerType.TYPE_SINGER) && !singers.contains(performerSong.getPerformer())) {
                            singers.add(performerSong.getPerformer());
                        } else if (!bands.contains(performerSong.getPerformer())) {
                            bands.add(performerSong.getPerformer());
                        }
                    }

                    SingerTableModel modelSingers = (SingerTableModel) form.getTblSingers().getModel();
                    BandTableModel modelBands = (BandTableModel) form.getTblBands().getModel();

                    modelSingers.setSelected(singers);
                    modelBands.setSelected(bands);
                } else {
                    JOptionPane.showMessageDialog(form, "No song selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        form.addBtnCancelActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                form.getBtnEditSong().setEnabled(true);
                form.getBtnSaveAlbum().setEnabled(true);
                form.getBtnSaveSong().setEnabled(false);
                form.getBtnAddNewSong().setEnabled(true);
                form.getBtnDeleteSelectedSong().setEnabled(true);
                form.getBtnCancel().setEnabled(false);

                prepareNewSongForm();
            }
        });

        form.addBtnSaveSongActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = form.getTxtSongName().getText();
                String durationStr = form.getTxtSongDuration().getText();

                if (validate(name, durationStr).isEmpty()) {
                    songEdit.setName(name);
                    songEdit.setDuration(Double.parseDouble(durationStr));
                    songEdit.setGenre((Genre) form.getCbGenre().getSelectedItem());
                    try {
                        songEdit.setUser(Communication.getInstance().getUser());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                    }

                    List<PerformerSong> performerSongs = new ArrayList<>();
                    SingerTableModel modelSingers = (SingerTableModel) form.getTblSingers().getModel();
                    BandTableModel modelBands = (BandTableModel) form.getTblBands().getModel();

                    for (Performer performer : modelSingers.getSelected()) {
                        PerformerSong ps = new PerformerSong(performer, songEdit);
                        performerSongs.add(ps);
                    }

                    for (Performer performer : modelBands.getSelected()) {
                        PerformerSong ps = new PerformerSong(performer, songEdit);
                        performerSongs.add(ps);
                    }

                    if (performerSongs.isEmpty()) {
                        JOptionPane.showMessageDialog(form, "Song must have at least one performer!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        songEdit.setPerformerSongs(performerSongs);
                        SongTableModel model = (SongTableModel) form.getTblSongs().getModel();
                        try {
                            model.saveSong(songEdit);
                            form.getTxtAlbumDuration().setText(String.valueOf(model.getAlbumDuration()));
                            form.getTxtNumberOfSongs().setText(String.valueOf(model.getNumberOfSongs()));
                            form.getBtnEditSong().setEnabled(true);
                            form.getBtnSaveAlbum().setEnabled(true);
                            form.getBtnSaveSong().setEnabled(false);
                            form.getBtnAddNewSong().setEnabled(true);
                            form.getBtnDeleteSelectedSong().setEnabled(true);
                            form.getBtnCancel().setEnabled(false);

                            prepareNewSongForm();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(form, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            form.getLblSongNameError().setText("* Song already exists!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(form, validate(name, durationStr), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        form.addBtnDeleteAlbumActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().addParam(Constants.PARAM_DELETE, album);
                MainCordinator.getInstance().openDeleteConfirmForm();
            }
        });
    }

    private String validate(String name, String durationStr) {
        String errorMessage = "";
        if (name.isEmpty()) {
            errorMessage += "Song's name is required!\n";
            form.getLblSongNameError().setText("* Required!");
        } else if (name.length() < 2 || name.length() > 20) {
            errorMessage += "Song's name must have between 2 and 20 characters!\n";
            form.getLblSongNameError().setText("* Neispravan naziv");
        } else if (!Pattern.matches("[A-ZŠĐŽČĆ](\\s?[A-Za-z0-9šđžčć]+)*", name)) {
            errorMessage += "Incorrect name format!\n";
            form.getLblSongNameError().setText("* Incorrect name");
        } else {
            form.getLblSongNameError().setText("");
        }

        if (durationStr.isEmpty()) {
            errorMessage += "Song's duration is required!\n";
            form.getLblSongDurationError().setText("* Required!");
        } else {
            try {
                double duration = Double.parseDouble(durationStr);
                if (duration < AlbumSongConstants.MIN_SONG_DURATION || duration > AlbumSongConstants.MAX_SONG_DURATION) {
                    String durationError = "Song's duration must be between " + AlbumSongConstants.MIN_SONG_DURATION + " and "
                            + AlbumSongConstants.MAX_SONG_DURATION + " min!";
                    errorMessage += durationError;
                    form.getLblSongDurationError().setText("* Duration is out of range!");
                }
                form.getLblSongDurationError().setText("");
            } catch (Exception e) {
                errorMessage += "Incorrect format of song's duration!";
                form.getLblSongDurationError().setText("* Incorrect format!");
            }
        }
        return errorMessage;
    }
}
