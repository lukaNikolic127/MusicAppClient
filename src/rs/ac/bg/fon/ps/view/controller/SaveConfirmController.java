package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Album;
import rs.ac.bg.fon.ps.domain.Band;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.Performer;
import rs.ac.bg.fon.ps.domain.Singer;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmSaveConfirm;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

public class SaveConfirmController {

    private final FrmSaveConfirm form;
    private final GenericEntity entity;
    private FormMode mode;

    public SaveConfirmController(FrmSaveConfirm form) {
        this.form = form;
        GenericEntity object = (GenericEntity) MainCordinator.getInstance().getParam(Constants.PARAM_GENERIC_ENTITY);
        if (object instanceof Album) {
            this.entity = (Album) object;
        } else if (object instanceof Performer) {
            this.entity = (Performer) object;
        } else {
            this.entity = null;
        }

    }

    public void openForm(FormMode mode) {
        this.mode = mode;
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
                    if (entity instanceof Album) {
                        Album album = (Album) entity;
                        switch (mode) {
                            case FORM_ADD:
                                Communication.getInstance().saveAlbum(album);
                                JOptionPane.showMessageDialog(form, "Album has been saved!", "Successfully saved", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case FORM_EDIT:
                                Communication.getInstance().updateAlbum(album);
                                JOptionPane.showMessageDialog(form, "Album has been updated!", "Successfully updated", JOptionPane.INFORMATION_MESSAGE);
                                break;
                        }
                    } else if (entity instanceof Performer) {
                        Performer performer = (Performer) entity;
                        Communication.getInstance().savePerformer(performer);
                        JOptionPane.showMessageDialog(form, "Performer has been saved!", "Successfully saved", JOptionPane.INFORMATION_MESSAGE);
                    }
                    form.dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                    form.dispose();
                } catch (Exception ex) {
                    //ex.printStackTrace();
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
        String question = "Are you sure you want to save ";
        if (entity instanceof Album) {
            Album album = (Album) entity;
            question += "album '" + album.getName() + "'";
        } else if (entity instanceof Performer) {
            Performer performer = (Performer) entity;
            if (performer instanceof Singer) {
                question += "singer '" + ((Singer) performer).getArtistName() + "'";
            } else if (performer instanceof Band) {
                question += "band '" + ((Band) performer).getBandName() + "'";
            }
        }
        question += " to database?";
        form.getLblQuestion().setText(question);
    }

}
