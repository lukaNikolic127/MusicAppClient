package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Band;
import rs.ac.bg.fon.ps.domain.Performer;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmDetailsBand;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

public class DetailsBandController {

    private FrmDetailsBand form;
    private Band band = (Band) MainCordinator.getInstance().getParam(Constants.PARAM_PERFORMER);
    public DetailsBandController(FrmDetailsBand form) {
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
        form.getTxtBandName().setEditable(false);
        form.getTxtCountry().setEditable(false);
        form.getTxtAddedBy().setEditable(false);

        form.getTxtId().setText(String.valueOf(band.getPerformerID()));
        form.getTxtBandName().setText(band.getBandName());
        form.getTxtCountry().setText(band.getCountry().getName());
        form.getTxtAddedBy().setText(band.getUser().getUsername());

        switch (mode) {
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
                MainCordinator.getInstance().addParam(Constants.PARAM_DELETE, band);
                MainCordinator.getInstance().openDeleteConfirmForm();
                form.dispose();
            }
        });
    }
}
