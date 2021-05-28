package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.communication.PerformerType;
import rs.ac.bg.fon.ps.domain.Band;
import rs.ac.bg.fon.ps.domain.Country;
import rs.ac.bg.fon.ps.domain.Performer;
import rs.ac.bg.fon.ps.domain.Singer;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmSelectPerformer;
import rs.ac.bg.fon.ps.view.form.model.PerformerBandTableModel;
import rs.ac.bg.fon.ps.view.form.model.PerformerSingerTableModel;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

public class SelectPerformerController {

    private final FrmSelectPerformer form;
    private FormMode formMode;

    public SelectPerformerController(FrmSelectPerformer form) {
        this.form = form;
    }

    public void openForm(FormMode mode) {
        try {
            formMode = mode;
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
        setTitle(formMode);
        form.getTxtBandName().setEditable(false);
        form.getTxtFirstName().setEditable(true);
        form.getTxtLastName().setEditable(true);
        form.getTxtArtistName().setEditable(true);
        setupTables();
        fillCbCountry();
        fillCbType();
        fillCbUsers();
    }

    private void setTitle(FormMode mode) {
        switch (mode) {
            case FORM_VIEW:
                form.setTitle("Search performer");
                break;
            case FORM_DELETE:
                form.setTitle("Delete performer");
                break;
        }
    }

    private void setupTables() throws Exception {
        Band band = new Band();
        List<Performer> bands = Communication.getInstance().getAllPerformers(band);
        PerformerBandTableModel modelBand = new PerformerBandTableModel(bands);
        form.getTblBands().setModel(modelBand);

        Singer singer = new Singer();
        List<Performer> singers = Communication.getInstance().getAllPerformers(singer);
        PerformerSingerTableModel modelSinger = new PerformerSingerTableModel(singers);
        form.getTblSingers().setModel(modelSinger);
    }

    private void fillCbCountry() throws Exception {
        List<Country> countries = Communication.getInstance().getAllCountries();
        form.getCbCountry().removeAllItems();
        form.getCbCountry().setModel(new DefaultComboBoxModel<>(countries.toArray()));
        form.getCbCountry().addItem("All countries");
        form.getCbCountry().setSelectedItem("All countries");
    }

    private void fillCbType() throws Exception {
        form.getCbType().removeAllItems();
        for (PerformerType value : PerformerType.values()) {

            if (value.equals(PerformerType.TYPE_SINGER)) {
                form.getCbType().addItem("Singer");
            } else if (value.equals(PerformerType.TYPE_BAND)) {
                form.getCbType().addItem("Band");
            }

        }
    }

    private void fillCbUsers() throws Exception {
        List<User> users = Communication.getInstance().getAllUsers();
        form.getCbUsers().removeAllItems();
        form.getCbUsers().setModel(new DefaultComboBoxModel<>(users.toArray()));
        form.getCbUsers().addItem("All users");
        form.getCbUsers().setSelectedItem("All users");
    }

    private void addActionListeners() {

        form.addCbTypeActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = form.getCbType().getSelectedItem().toString();
                if (selectedType.equals("Singer")) {
                    form.getTxtBandName().setEditable(false);
                    form.getTxtFirstName().setEditable(true);
                    form.getTxtLastName().setEditable(true);
                    form.getTxtArtistName().setEditable(true);
                    form.getTxtBandName().setText("");
                } else {
                    form.getTxtBandName().setEditable(true);
                    form.getTxtFirstName().setEditable(false);
                    form.getTxtLastName().setEditable(false);
                    form.getTxtArtistName().setEditable(false);
                    form.getTxtFirstName().setText("");
                    form.getTxtLastName().setText("");
                    form.getTxtArtistName().setText("");
                }
            }
        });

        form.addBtnSearchActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Country country = null;
                try {
                    country = (Country) form.getCbCountry().getSelectedItem();
                } catch (ClassCastException ex) {
                }

                User user = null;
                try {
                    user = (User) form.getCbUsers().getSelectedItem();
                } catch (ClassCastException ex) {
                }

                String firstName = form.getTxtFirstName().getText();
                String lastName = form.getTxtLastName().getText();
                String artistName = form.getTxtArtistName().getText();
                String bandName = form.getTxtBandName().getText();

                String selectedType = form.getCbType().getSelectedItem().toString();
                if (selectedType.equals("Singer")) {
                    Singer pattern = new Singer();
                    pattern.setFirstName(firstName);
                    pattern.setLastName(lastName);
                    pattern.setArtistName(artistName);

                    pattern.setCountry(country);
                    pattern.setUser(user);
                    try {
                        MainCordinator.getInstance().addParam(Constants.PARAM_PERFORMER, pattern);
                        MainCordinator.getInstance().openSearchPerformerForm(formMode);
                        setupTables();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(form, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    Band pattern = new Band();
                    pattern.setBandName(bandName);
                    pattern.setCountry(country);
                    pattern.setUser(user);
                    try {
                        MainCordinator.getInstance().addParam(Constants.PARAM_PERFORMER, pattern);
                        MainCordinator.getInstance().openSearchPerformerForm(formMode);
                        setupTables();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(form, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        form.addBtnSelectActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedBands = form.getTblBands().getSelectedRows();
                int[] selectedSingers = form.getTblSingers().getSelectedRows();
                if (selectedBands.length == 0 && selectedSingers.length == 0) {
                    JOptionPane.showMessageDialog(form, "No performer selected!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    if ((selectedBands.length > 1 || selectedSingers.length > 1)) {
                        JOptionPane.showMessageDialog(form, "Cannot select more than one performer!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int selectedBand = -1, selectedSinger = -1;

                        if (selectedBands.length == 1) {
                            selectedBand = selectedBands[0];
                        }
                        if (selectedSingers.length == 1) {
                            selectedSinger = selectedSingers[0];
                        }
                        if (selectedBand >= 0 && selectedSinger >= 0) {
                            JOptionPane.showMessageDialog(form, "Cannot select more than one performer!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (selectedBand >= 0) {
                                PerformerBandTableModel model = (PerformerBandTableModel) form.getTblBands().getModel();
                                Band band = (Band) model.getPerformer(selectedBand);
                                form.getCbType().setSelectedItem("Band");
                                form.getTxtFirstName().setText("");
                                form.getTxtLastName().setText("");
                                form.getTxtArtistName().setText("");
                                form.getTxtBandName().setText(band.getBandName());
                                form.getCbCountry().setSelectedItem(band.getCountry());
                                form.getCbUsers().setSelectedItem(band.getUser());
                            } else {
                                PerformerSingerTableModel model = (PerformerSingerTableModel) form.getTblSingers().getModel();
                                Singer singer = (Singer) model.getPerformer(selectedSinger);
                                form.getCbType().setSelectedItem("Singer");
                                form.getTxtFirstName().setText(singer.getFirstName());
                                form.getTxtLastName().setText(singer.getLastName());
                                form.getTxtArtistName().setText(singer.getArtistName());
                                form.getTxtBandName().setText("");
                                form.getCbCountry().setSelectedItem(singer.getCountry());
                                form.getCbUsers().setSelectedItem(singer.getUser());
                            }
                            try {
                                setupTables();
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(form, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        });

        form.addBtnResetActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.getCbType().setSelectedIndex(0);
                form.getTxtFirstName().setText("");
                form.getTxtLastName().setText("");
                form.getTxtArtistName().setText("");
                form.getTxtBandName().setText("");
                form.getCbCountry().setSelectedItem("All countries");
                form.getCbUsers().setSelectedItem("All users");
            }
        });
    }

}
