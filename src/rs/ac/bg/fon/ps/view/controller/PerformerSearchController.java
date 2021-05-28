package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.Band;
import rs.ac.bg.fon.ps.domain.Performer;
import rs.ac.bg.fon.ps.domain.Singer;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmPerformerSearchResult;
import rs.ac.bg.fon.ps.view.form.model.PerformerBandTableModel;
import rs.ac.bg.fon.ps.view.form.model.PerformerSingerTableModel;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

public class PerformerSearchController {

    private final FrmPerformerSearchResult form;
    private FormMode formMode;
    private Performer pattern;

    public PerformerSearchController(FrmPerformerSearchResult form) {
        this.form = form;
    }

    public void openForm(FormMode mode) throws Exception {
        try {
            this.formMode = mode;
            this.pattern = (Performer) MainCordinator.getInstance().getParam(Constants.PARAM_PERFORMER);
            prepareForm();
            addActionListeners();
            form.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
            form.setVisible(true);
        } catch (IOException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private void prepareForm() throws Exception {

        switch (pattern.getType()) {
            case TYPE_SINGER:
                List<Performer> singers = Communication.getInstance().searchPerformers(pattern);
                if (singers.isEmpty()) {
                    throw new Exception("No singer found!");
                } else {
                    PerformerSingerTableModel modelSingers = new PerformerSingerTableModel(singers);
                    form.getTblPerformers().setModel(modelSingers);
                }
                break;
            case TYPE_BAND:
                List<Performer> bands = Communication.getInstance().searchPerformers(pattern);
                if (bands.isEmpty()) {
                    throw new Exception("No band found!");
                } else {
                    PerformerBandTableModel modelBands = new PerformerBandTableModel(bands);
                    form.getTblPerformers().setModel(modelBands);
                }
                break;
        }
    }

    private void addActionListeners() {
        form.addBtnShowDetailsActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = form.getTblPerformers().getSelectedRows();
                if (selectedRows.length > 0) {
                    if (selectedRows.length > 1) {
                        JOptionPane.showMessageDialog(form, "Cannot be selected more than one performer!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (form.getTblPerformers().getModel() instanceof PerformerSingerTableModel) {
                            int selectedRow = selectedRows[0];
                            PerformerSingerTableModel model = (PerformerSingerTableModel) form.getTblPerformers().getModel();
                            Performer performer = model.getPerformer(selectedRow);

                            Singer pattern = new Singer();
                            pattern.setPerformerID(performer.getPerformerID());

                            try {
                                Performer singer = (Performer) Communication.getInstance().getPerformer(pattern);
                                MainCordinator.getInstance().addParam(Constants.PARAM_PERFORMER, singer);
                                MainCordinator.getInstance().openDetailsSingerForm(formMode);
                                refresh();
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(form, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            int selectedRow = selectedRows[0];
                            PerformerBandTableModel model = (PerformerBandTableModel) form.getTblPerformers().getModel();
                            Performer performer = model.getPerformer(selectedRow);

                            Band pattern = new Band();
                            pattern.setPerformerID(performer.getPerformerID());
                            try {
                                Performer band = (Performer) Communication.getInstance().getPerformer(pattern);
                                MainCordinator.getInstance().addParam(Constants.PARAM_PERFORMER, band);
                                MainCordinator.getInstance().openDetailsBandForm(formMode);
                                refresh();
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(form, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(form, "No performer selected!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void refresh() throws Exception {
                switch (pattern.getType()) {
                    case TYPE_SINGER:
                        List<Performer> singers = Communication.getInstance().searchPerformers(pattern);
                        PerformerSingerTableModel modelSingers = new PerformerSingerTableModel(singers);
                        form.getTblPerformers().setModel(modelSingers);
                        break;
                    case TYPE_BAND:
                        List<Performer> bands = Communication.getInstance().searchPerformers(pattern);
                        PerformerBandTableModel modelBands = new PerformerBandTableModel(bands);
                        form.getTblPerformers().setModel(modelBands);
                        break;
                }
            }

        });
    }
}
