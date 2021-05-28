package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Performer;
import rs.ac.bg.fon.ps.domain.Singer;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmDetailsSinger;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

public class DetailsSingerController {

    private FrmDetailsSinger form;
    private Singer singer = (Singer) MainCordinator.getInstance().getParam(Constants.PARAM_PERFORMER);
    public DetailsSingerController(FrmDetailsSinger form) {
        this.form = form;
    }

    public void openForm(FormMode mode) {
        prepareForm(mode);
        addActionListeners();
        form.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
        form.setVisible(true);
    }

    private void prepareForm(FormMode mode) {
        form.getTxtId().setEditable(false);
        form.getTxtFirstName().setEditable(false);
        form.getTxtLastName().setEditable(false);
        form.getTxtArtistName().setEditable(false);
        form.getTxtCountry().setEditable(false);
        form.getTxtAddedBy().setEditable(false);

        form.getTxtId().setText(String.valueOf(singer.getPerformerID()));
        form.getTxtFirstName().setText(singer.getFirstName());
        form.getTxtLastName().setText(singer.getLastName());
        form.getTxtArtistName().setText(singer.getArtistName());
        form.getTxtCountry().setText(singer.getCountry().getName());
        form.getTxtAddedBy().setText(singer.getUser().getUsername());
        
        switch(mode){
            case FORM_DELETE:
                form.getBtnDelete().setEnabled(true);
                break;
            case FORM_VIEW:
                form.getBtnDelete().setEnabled(false);
                break;
        }
    }

    private void addActionListeners() {
        form.addBtnDeleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCordinator.getInstance().addParam(Constants.PARAM_DELETE, singer);
                MainCordinator.getInstance().openDeleteConfirmForm();
                form.dispose();
            }
        });
    }
}
