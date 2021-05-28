package rs.ac.bg.fon.ps.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.communication.Communication;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.view.form.FrmLogin;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;

public class LoginController {

    private final FrmLogin form;

    public LoginController(FrmLogin frmLogin) {
        this.form = frmLogin;
        addActionListener();
    }

    public void openForm() {
        form.setVisible(true);
    }

    private void addActionListener() {
        form.loginAddActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String username = form.getTxtUsername().getText().trim();
                    String password = String.copyValueOf(form.getTxtPassword().getPassword());

                    validateForm(username, password);
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);

                    user = Communication.getInstance().login(user);

                    JOptionPane.showMessageDialog(
                            form,
                            "Welcome " + user.getUsername() + "!",
                            "Logged in",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    form.dispose();
                    MainCordinator.getInstance().openMainForm();

                } catch (IOException e) {
                    //e.printStackTrace();
                    JOptionPane.showMessageDialog(form, e.getMessage(), "Connection failed", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(form, e.getMessage(), "Log in failed", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void validateForm(String username, String password) throws Exception {
                String errorMessage = "";
                if (username.isEmpty()) {
                    form.getLblUsernameError().setText("* Required");
                    errorMessage += "Username is required!\n";
                } else {
                    form.getLblUsernameError().setText("");
                }
                if (password.isEmpty()) {
                    form.getLblPasswordError().setText("* Required");
                    errorMessage += "Password is required!\n";
                } else {
                    form.getLblPasswordError().setText("");
                }
                if (!errorMessage.isEmpty()) {
                    throw new Exception(errorMessage);
                }
            }

        });
    }

}
