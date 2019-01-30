package ui.controllers.custom;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

public interface ICodeController extends Initializable {

    void setDialogStage(Stage dialogStage);

    boolean isKeySettled();
}
