package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.communication.PerformerType;
import rs.ac.bg.fon.ps.domain.Album;
import rs.ac.bg.fon.ps.domain.Band;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.Performer;
import rs.ac.bg.fon.ps.domain.Singer;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmDeleteConfirm;

public class DeleteConfirmController {

    private final FrmDeleteConfirm form;
    private GenericEntity entity;

    public DeleteConfirmController(FrmDeleteConfirm form) {
        this.form = form;
    }

    public void openForm() {
        prepareQuestion();
        addActionListeners();
        form.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        form.setVisible(true);
    }

    private void addActionListeners() {

        form.addBtnYesActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (entity instanceof Performer) {
                        Communication.getInstance().removePerformer((Performer) entity);
                        JOptionPane.showMessageDialog(form, "Performer has been deleted!", "Successfully deleted", JOptionPane.INFORMATION_MESSAGE);
                    } else if (entity instanceof Album) {
                        Communication.getInstance().removeAlbum((Album) entity);
                        JOptionPane.showMessageDialog(form, "Album has been deleted!", "Successfully deleted", JOptionPane.INFORMATION_MESSAGE);
                    }
                    form.dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                    form.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
                    form.dispose();
                }
            }
        });

        form.addBtnNoActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.dispose();
            }
        });
    }

    private void prepareQuestion() {
        String question = "Are you sure you want to delete ";
        if (MainCordinator.getInstance().getParam(Constants.PARAM_DELETE) instanceof Performer) {
            Performer performer = (Performer) MainCordinator.getInstance().getParam(Constants.PARAM_DELETE);
            this.entity = performer;
            if (performer instanceof Band) {
                Band band = (Band) performer;
                question += "band '" + band.getBandName() + "'";
            } else if (performer instanceof Singer) {
                Singer singer = (Singer) performer;
                question += "singer '" + singer.getArtistName() + "'";
            }
        } else if (MainCordinator.getInstance().getParam(Constants.PARAM_DELETE) instanceof Album) {
            Album album = (Album) MainCordinator.getInstance().getParam(Constants.PARAM_DELETE);
            this.entity = album;
            question += "album '" + album.getName() + "'";
        }
        question += " from database ?";

        form.getLblQuestion().setText(question);
    }

}
