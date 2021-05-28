package rs.ac.bg.fon.ps.view.cordinator;

import java.util.HashMap;
import java.util.Map;
import rs.ac.bg.fon.ps.communication.PerformerType;
import rs.ac.bg.fon.ps.view.constants.Constants;
import rs.ac.bg.fon.ps.view.controller.AlbumController;
import rs.ac.bg.fon.ps.view.controller.AlbumSearchController;
import rs.ac.bg.fon.ps.view.controller.SelectPerformerController;
import rs.ac.bg.fon.ps.view.controller.DetailsBandController;
import rs.ac.bg.fon.ps.view.controller.DetailsSingerController;
import rs.ac.bg.fon.ps.view.form.FrmLogin;
import rs.ac.bg.fon.ps.view.form.FrmMain;
import rs.ac.bg.fon.ps.view.controller.LoginController;
import rs.ac.bg.fon.ps.view.controller.MainController;
import rs.ac.bg.fon.ps.view.controller.DeleteConfirmController;
import rs.ac.bg.fon.ps.view.controller.PerformerController;
import rs.ac.bg.fon.ps.view.controller.PerformerSearchController;
import rs.ac.bg.fon.ps.view.controller.SaveConfirmController;
import rs.ac.bg.fon.ps.view.controller.SelectAlbumController;
import rs.ac.bg.fon.ps.view.form.FrmAlbumNew;
import rs.ac.bg.fon.ps.view.form.FrmSaveConfirm;
import rs.ac.bg.fon.ps.view.form.FrmAlbumSearchResult;
import rs.ac.bg.fon.ps.view.form.FrmSelectPerformer;
import rs.ac.bg.fon.ps.view.form.FrmDetailsBand;
import rs.ac.bg.fon.ps.view.form.FrmDetailsSinger;
import rs.ac.bg.fon.ps.view.form.FrmDeleteConfirm;
import rs.ac.bg.fon.ps.view.form.FrmPerformerSearchResult;
import rs.ac.bg.fon.ps.view.form.FrmSelectAlbum;
import rs.ac.bg.fon.ps.view.form.util.FormMode;

public class MainCordinator {

    private static MainCordinator instance;
    private final MainController mainController;

    private final Map<Constants, Object> params;

    public void addParam(Constants name, Object key) {
        params.put(name, key);
    }

    public Object getParam(Constants name) {
        return params.get(name);
    }

    private MainCordinator() {
        this.mainController = new MainController(new FrmMain());
        this.params = new HashMap<>();
    }

    public static MainCordinator getInstance() {
        if (instance == null) {
            instance = new MainCordinator();
        }
        return instance;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void openMainForm() {
        mainController.openForm();
    }
    
    public void closeMainForm() {
        mainController.closeForm();
    }

    public void openLoginForm() {
        LoginController loginController = new LoginController(new FrmLogin());
        loginController.openForm();
    }

    public void openPerformerSingerMenageForm() {
        PerformerController performerController = new PerformerController(PerformerType.TYPE_SINGER, mainController.getFrmMain());
        performerController.openForm();
    }

    public void openPerformerBandMenageForm() {
        PerformerController performerController = new PerformerController(PerformerType.TYPE_BAND, mainController.getFrmMain());
        performerController.openForm();
    }

    public void openAlbumNewForm(FormMode mode) {
        AlbumController albumController = new AlbumController(new FrmAlbumNew(mainController.getFrmMain(), true));
        albumController.openForm(mode);
    }

    public void openDeleteConfirmForm() {
        DeleteConfirmController deleteConfirmController = new DeleteConfirmController(new FrmDeleteConfirm(mainController.getFrmMain(), true));
        deleteConfirmController.openForm();
    }

    public void openSearchPerformerForm(FormMode mode) throws Exception {
        PerformerSearchController performerSearchController = new PerformerSearchController(new FrmPerformerSearchResult(mainController.getFrmMain(), true));
        performerSearchController.openForm(mode);
    }

    public void openSelectPerformerForm(FormMode mode) {
        SelectPerformerController selectPerformerController = new SelectPerformerController(new FrmSelectPerformer(mainController.getFrmMain(), true));
        selectPerformerController.openForm(mode);
    }

    public void openDetailsSingerForm(FormMode mode) {
        DetailsSingerController detailsSingerController = new DetailsSingerController(new FrmDetailsSinger(mainController.getFrmMain(), true));
        detailsSingerController.openForm(mode);
    }

    public void openDetailsBandForm(FormMode mode) {
        DetailsBandController detailsBandController = new DetailsBandController(new FrmDetailsBand(mainController.getFrmMain(), true));
        detailsBandController.openForm(mode);
    }

    public void openSaveConfirmForm(FormMode mode) {
        SaveConfirmController saveConfirmController = new SaveConfirmController(new FrmSaveConfirm(mainController.getFrmMain(), true));
        saveConfirmController.openForm(mode);
    }

    public void openSelectAlbumForm() {
        SelectAlbumController selectAlbumController = new SelectAlbumController(new FrmSelectAlbum(mainController.getFrmMain(), true));
        selectAlbumController.openForm();
    }

    public void openSearchAlbumForm() {
        AlbumSearchController albumSearchController = new AlbumSearchController(new FrmAlbumSearchResult(mainController.getFrmMain(), true));
        albumSearchController.openForm();
    }

    public void refreshFrmDeletePerformer() {
        
    }

}
