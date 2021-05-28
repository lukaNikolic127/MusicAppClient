package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Album;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.constants.AlbumSongConstants;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmSelectAlbum;
import rs.ac.bg.fon.ps.view.form.model.AlbumTableModel;

public class SelectAlbumController {

    private final FrmSelectAlbum form;
    private Album selectedAlbum = null;

    public SelectAlbumController(FrmSelectAlbum form) {
        this.form = form;
    }

    public void openForm() {
        try {
            prepareForm();
            addActionListeners();
            form.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
            form.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(form, ex.getMessage(), "Loading erroru", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prepareForm() throws Exception {
        prepareTable();
        fillCbUsers();
        form.getTxtAlbumName().setText("");
        form.getSlDuration().setMinimum(0);
        form.getSlDuration().setMaximum((int) (AlbumSongConstants.MAX_NUM_OF_SONGS * AlbumSongConstants.MAX_SONG_DURATION));
        form.getSlDuration().setValue(0);
        form.getSlNumOfSongs().setValue(0);
        form.getSlNumOfSongs().setMaximum(AlbumSongConstants.MAX_NUM_OF_SONGS);
    }

    private void fillCbUsers() throws Exception {
        List<User> users = Communication.getInstance().getAllUsers();
        form.getCbUsers().removeAllItems();
        form.getCbUsers().setModel(new DefaultComboBoxModel<>(users.toArray()));
        form.getCbUsers().addItem("All users");
    }

    private void prepareTable() throws Exception {
        List<Album> albums = Communication.getInstance().getAllAlbums();
        AlbumTableModel model = new AlbumTableModel(albums);
        form.getTblAlbums().setModel(model);
    }

    private void addActionListeners() {
        form.addBtnSelectAlbumActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = form.getTblAlbums().getSelectedRows();
                if (selectedRows.length > 0) {
                    if (selectedRows.length > 1) {
                        JOptionPane.showMessageDialog(form, "Cannot select more than one album.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int selectedRow = selectedRows[0];
                        AlbumTableModel model = (AlbumTableModel) form.getTblAlbums().getModel();
                        Album album = model.getSelectedAlbum(selectedRow);
                        selectedAlbum = album;
                        form.getTxtAlbumName().setText(album.getName());
                        form.getCbUsers().setSelectedItem(album.getUser());
                    }
                } else {
                    JOptionPane.showMessageDialog(form, "No album selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        form.addBtnSearchActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Album pattern = new Album();

                String albumName = form.getTxtAlbumName().getText();
                User user = null;
                try {
                    user = (User) form.getCbUsers().getSelectedItem();
                } catch (ClassCastException ex) {

                }

                double duration = form.getSlDuration().getValue();
                int numberOfSongs = form.getSlNumOfSongs().getValue();

                if (selectedAlbum != null) {
                    pattern.setAlbumID(selectedAlbum.getAlbumID());
                } else {
                    pattern.setName(albumName);
                    pattern.setUser(user);
                    pattern.setDuration(duration);
                    pattern.setNumberOfSongs(numberOfSongs);
                }
                MainCordinator.getInstance().addParam(Constants.PARAM_ALBUM_PATTERN, pattern);
                MainCordinator.getInstance().openSearchAlbumForm();
                try {
                    prepareForm();
                    selectedAlbum = null;
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        form.addBtnResetActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    prepareForm();
                } catch (IOException ex) {
                    form.dispose();
                    MainCordinator.getInstance().closeMainForm();
                    MainCordinator.getInstance().openLoginForm();
                    JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
