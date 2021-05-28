package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.communication.PerformerType;
import rs.ac.bg.fon.ps.domain.Band;
import rs.ac.bg.fon.ps.domain.Country;
import rs.ac.bg.fon.ps.domain.Performer;
import rs.ac.bg.fon.ps.domain.Singer;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;
import rs.ac.bg.fon.ps.view.form.FrmPerformerBand;
import rs.ac.bg.fon.ps.view.form.FrmPerformerSinger;
import rs.ac.bg.fon.ps.view.form.model.PerformerBandTableModel;
import rs.ac.bg.fon.ps.view.form.model.PerformerSingerTableModel;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

public class PerformerController {

    private final PerformerType performerType;
    private JDialog form;

    public PerformerController(PerformerType performerType, JFrame mainForm) {
        this.performerType = performerType;
        switch (performerType) {
            case TYPE_SINGER:
                form = new FrmPerformerSinger(mainForm, true);
                break;
            case TYPE_BAND:
                form = new FrmPerformerBand(mainForm, true);
                break;
        }
    }

    public void openForm() {
        try {
            prepareForm();
            addActionListeners();
            form.setLocationRelativeTo(MainCordinator.getInstance().getMainController().getFrmMain());
            form.setVisible(true);
        } catch (IOException ex) {
            MainCordinator.getInstance().closeMainForm();
            MainCordinator.getInstance().openLoginForm();
            JOptionPane.showMessageDialog(form, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(form, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prepareForm() throws Exception {
        setupTable();
        fillCbCountry();
    }

    private void setupTable() throws Exception {
        switch (performerType) {
            case TYPE_SINGER:
                Singer singer = new Singer();
                List<Performer> singers = Communication.getInstance().getAllPerformers(singer);
                PerformerSingerTableModel modelSinger = new PerformerSingerTableModel(singers);
                ((FrmPerformerSinger) form).getTblPerformerSinger().setModel(modelSinger);
                break;
            case TYPE_BAND:
                Band band = new Band();
                List<Performer> bands = Communication.getInstance().getAllPerformers(band);
                PerformerBandTableModel modelBand = new PerformerBandTableModel(bands);
                ((FrmPerformerBand) form).getTblPerformerBand().setModel(modelBand);
                break;
        }
    }

    private void fillCbCountry() throws Exception {
        List<Country> countries = Communication.getInstance().getAllCountries();
        switch (performerType) {
            case TYPE_SINGER:
                ((FrmPerformerSinger) form).getCbCountry().removeAllItems();
                ((FrmPerformerSinger) form).getCbCountry().setModel(new DefaultComboBoxModel<>(countries.toArray()));
                break;
            case TYPE_BAND:
                ((FrmPerformerBand) form).getCbCountry().removeAllItems();
                ((FrmPerformerBand) form).getCbCountry().setModel(new DefaultComboBoxModel<>(countries.toArray()));
                break;
        }
    }

    private void addActionListeners() {
        switch (performerType) {
            case TYPE_SINGER:
                FrmPerformerSinger singerForm = (FrmPerformerSinger) form;

                singerForm.addBtnAddActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String firstName = singerForm.getTxtFirstName().getText();
                        String lastName = singerForm.getTxtLastName().getText();
                        String artistName = singerForm.getTxtArtistName().getText();

                        if (validate(firstName, lastName, artistName).isEmpty()) {

                            Singer singer = new Singer();
                            singer.setFirstName(firstName);
                            singer.setLastName(lastName);
                            singer.setArtistName(artistName);

                            Country country = (Country) singerForm.getCbCountry().getSelectedItem();
                            singer.setCountry(country);

                            MainCordinator.getInstance().addParam(Constants.PARAM_GENERIC_ENTITY, singer);
                            MainCordinator.getInstance().openSaveConfirmForm(FormMode.FORM_ADD);
                            try {
                                setupTable();
                            } catch (IOException ex) {
                                
                                JOptionPane.showMessageDialog(singerForm, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(singerForm, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(singerForm, validate(firstName, lastName, artistName), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    private String validate(String firstName, String lastName, String artistName) {
                        String errorMessage = "";
                        if (firstName.isEmpty()) {
                            errorMessage += "First name is required!\n";
                            singerForm.getLblFirstNameError().setText("* Required");
                        } else if (!Pattern.matches("[A-ZŠĐŽČĆ][a-zšđžčć]{2,20}", firstName)) {
                            errorMessage += "Incorrect format of first name!\n";
                            singerForm.getLblFirstNameError().setText("* Incorrect format");
                        } else {
                            singerForm.getLblFirstNameError().setText("");
                        }

                        if (lastName.isEmpty()) {
                            errorMessage += "Last name is required!\n";
                            singerForm.getLblLastNameError().setText("* Required");
                        } else if (!Pattern.matches("[A-ZŠĐŽČĆ][a-zšđžčć]{2,20}", lastName)) {
                            errorMessage += "Incorrect format of last name!\n";
                            singerForm.getLblLastNameError().setText("* Incorrect format");
                        } else {
                            singerForm.getLblLastNameError().setText("");
                        }

                        if (artistName.isEmpty()) {
                            errorMessage += "Stage name is required!\n";
                            singerForm.getLblArtistNameError().setText("* Required");
                        } else if (artistName.length() < 2 || artistName.length() > 20) {
                            errorMessage += "Stage name must have between 2 and 20 characters!\n";
                            singerForm.getLblArtistNameError().setText("* Incorrect format");
                        } else if (!Pattern.matches("[A-ZŠĐŽČĆ](\\s?[A-Za-z0-9šđžčć]+)*", artistName)) {
                            errorMessage += "Incorrect format of stage name!\n";
                            singerForm.getLblArtistNameError().setText("* Incorrect format");
                        } else {
                            singerForm.getLblArtistNameError().setText("");
                        }

                        return errorMessage;
                    }

                });

                break;
            case TYPE_BAND:
                FrmPerformerBand bandForm = (FrmPerformerBand) form;
                bandForm.addBtnAddActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = bandForm.getTxtName().getText();

                        if (validate(name).isEmpty()) {

                            Band band = new Band();
                            band.setBandName(name);
                            Country country = (Country) bandForm.getCbCountry().getSelectedItem();
                            band.setCountry(country);

                            MainCordinator.getInstance().addParam(Constants.PARAM_GENERIC_ENTITY, band);
                            MainCordinator.getInstance().openSaveConfirmForm(FormMode.FORM_ADD);
                            try {
                                setupTable();
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(bandForm, ex.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(bandForm, ex.getMessage(), "Loading error", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(bandForm, validate(name), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    private String validate(String name) {
                        String errorMessage = "";
                        if (name.isEmpty()) {
                            errorMessage += "Band's name is required!\n";
                            bandForm.getLblNameError().setText("* Required");
                        } else if (name.length() < 2 || name.length() > 20) {
                            errorMessage += "Band's name must have between 2 and 20 characters!\n";
                            bandForm.getLblNameError().setText("* Incorrect format");
                        } else if (!Pattern.matches("[A-ZŠĐŽČĆ](\\s?[A-Za-z0-9šđžčć]+)*", name)) {
                            errorMessage += "Incorrect format of band's name!\n";
                            bandForm.getLblNameError().setText("* Incorrect format");
                        } else {
                            bandForm.getLblNameError().setText("");
                        }

                        return errorMessage;
                    }

                });
                break;
        }
    }
}
