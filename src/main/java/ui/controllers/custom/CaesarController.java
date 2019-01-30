package ui.controllers.custom;

import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CaesarController implements ICodeController
{
    private Stage dialogStage;
    private boolean isOkay = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public boolean isKeySettled() {
        return isOkay;
    }
}
