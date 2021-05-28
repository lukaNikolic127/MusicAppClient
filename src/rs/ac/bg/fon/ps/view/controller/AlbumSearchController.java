package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Album;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmAlbumSearchResult;
import rs.ac.bg.fon.ps.view.form.model.AlbumTableModel;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

public class AlbumSearchController {

    private final FrmAlbumSearchResult form;
    private Album pattern;

    public AlbumSearchController(FrmAlbumSearchResult form) {
        this.form = form;
    }

    public void openForm() {
        this.pattern = (Album) MainCordinator.getInstance().getParam(Constants.PARAM_ALBUM_PATTERN);
        try {
            prepareForm();
            addActionListeners();
            form.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
            form.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(form, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void prepareForm() throws Exception {
        List<Album> albums = Communication.getInstance().searchAlbums(pattern);
        if (albums.size() == 1) {
            this.pattern.setAlbumID(albums.get(0).getAlbumID());
            this.pattern.setName("");
        }
        if (albums.isEmpty()) {
            throw new Exception("No album found!");
        }
        AlbumTableModel model = new AlbumTableModel(albums);
        form.getTblAlbums().setModel(model);
    }

    private void addActionListeners() {
        form.addBtnShowAlbumActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = form.getTblAlbums().getSelectedRows();
                if (selectedRows.length > 0) {
                    if (selectedRows.length > 1) {
                        JOptionPane.showMessageDialog(form, "Cannot be selected more than one album.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int selectedRow = selectedRows[0];
                        AlbumTableModel model = (AlbumTableModel) form.getTblAlbums().getModel();
                        Album album = model.getSelectedAlbum(selectedRow);
                        try {
                            Album result = Communication.getInstance().getAlbum(album);
                            MainCordinator.getInstance().addParam(Constants.PARAM_ALBUM, result);
                            MainCordinator.getInstance().openAlbumNewForm(FormMode.FORM_EDIT);
                            refresh();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(form, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(form, "No album selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void refresh() throws Exception {
                List<Album> albums = Communication.getInstance().searchAlbums(pattern);
                AlbumTableModel model = new AlbumTableModel(albums);
                form.getTblAlbums().setModel(model);
            }

        });
    }
}
