package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.WindowConstants;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

public class MainController {

    private final FrmMain form;

    public MainController(FrmMain frmMain) {
        this.form = frmMain;
        addActionListeners();
    }

    public FrmMain getFrmMain() {
        return form;
    }

    public void openForm() {
        form.setVisible(true);
        try {

            form.getLblUser().setText("Welcome " + Communication.getInstance().getUser().getUsername() + "!");
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeForm() {
        form.dispose();
    }

    private void addActionListeners() {

        form.addMiSingerActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openPerformerSingerMenageForm();
            }
        });

        form.addMiBandActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openPerformerBandMenageForm();
            }
        });

        form.addMiDeleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openSelectPerformerForm(FormMode.FORM_DELETE);
            }
        });

        form.addMiAlbumNewActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openAlbumNewForm(FormMode.FORM_ADD);
            }
        });

        form.addMiAlbumEditActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openSelectAlbumForm();
            }
        });

        form.addMiSearchPerformerActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().openSelectPerformerForm(FormMode.FORM_VIEW);
            }
        });

    }

}
