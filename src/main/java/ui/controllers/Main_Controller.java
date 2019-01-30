/*
 *
 * 1. Set input/output langugages
 * 2. Set kind of code
 * 3. Set input text
 * 4. Set key
 *
 */

package ui.controllers;

import logic.language.AllLanguagesEnum;
import logic.code.AllCodes;
import logic.code.custom.Code;
import ui.ResourcesInfo;
import ui.controllers.custom.ICodeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main_Controller implements Initializable {

    public static final String MAIN_WINDOW_TITLE = "";

    @FXML public Button testBut;
    @FXML public Button setKey_button;
    @FXML public Label label_test_1;
    @FXML public CheckBox checkBox_isOkKey;
    @FXML public CheckBox checkBox_singleLang;

    @FXML public ListView<String> list_codes;
    @FXML public ListView<String> lang_from;
    @FXML public ListView<String> lang_to;

    @FXML public RadioButton radioBut_toCode;
    @FXML public RadioButton radioBut_toDecode;
    @FXML public TextArea code_description;

    @FXML public VBox languages_box;
    @FXML public TextArea inputText_area;
    @FXML public HBox setKey_box;
    @FXML public HBox down_mainButton_box;

    private boolean bKeyAnswer = false;
    private boolean singleLang = false;

    private AllCodes currentCodeID;
    //public ComboBox checkList;

    // main for checkbox for setting kind of code. other panels depend on it
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> items_codeValues = FXCollections.observableArrayList(AllCodes.getCodesNamesList());      // set codes names
        ObservableList<String> items_langValues = FXCollections.observableArrayList(AllLanguagesEnum.getLanguages());   // set all languages
        list_codes.setItems(items_codeValues);
        lang_from.setItems(items_langValues);
        updateKeyCheckbox();                                                                                            // обновление чекбокса, показывающего, задан ключ для шифра или нет.
        updateTextArea();                                                                                               // обновление панели с текстом описания выбранного шифра
        checkBox_singleLang.setSelected(true);                                                                          // чекбокс выбора одного-двух языков объявляется выбранным и неактивным (функция пока не доступна)
        checkBox_singleLang.setDisable(true);
        updateLangs();
        ToggleGroup butGroup = new ToggleGroup();                                                                       // создание группы радиобаттонов, в которой выбрать можно только один элемент
        radioBut_toCode.setToggleGroup(butGroup);
        radioBut_toCode.setSelected(true);
        radioBut_toDecode.setToggleGroup(butGroup);
        disableAllFields(true);
    }

    // update when chosen language in list
    @FXML
    public void code_chosen(MouseEvent actionEvent) {
        // -- debug --
        String str = list_codes.getSelectionModel().getSelectedItem();                                                  // получение строки с именем текущего класса // debug
        label_test_1.setText(str);
        // -- debug --

        int pos = list_codes.getSelectionModel().getSelectedIndex();                                                    // get code position
        //if ((pos < 0) || (pos >= AllCodes.values().length)) return;                                                     // check position number
        currentCodeID = AllCodes.values()[pos];                                                                         // get code ID
        updateTextArea();                                                                                               // show code description
        singleLang = currentCodeID.isSingleLanguageOny();                                                               // show if this code can be used in single - multiple languages mode
        updateLangs();
        if (inputText_area.isDisabled()) {
            disableAllFields(false);
        }
    }

    // set key button - opens new window with current code options
    @FXML
    public void openSetKeyWindow(ActionEvent act) {
        Code code = Code.getNewCode(currentCodeID, radioBut_toCode.isSelected());
        FXMLLoader fxmlLoader = new FXMLLoader();
        Stage stage = new Stage();
        String codeResPath = ResourcesInfo.getCodePath(currentCodeID);
        fxmlLoader.setLocation(getClass().getResource(codeResPath));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(ResourcesInfo.getKeyWinTitle(currentCodeID));
        stage.initModality(Modality.APPLICATION_MODAL);
        ICodeController iCodeController = fxmlLoader.getController();
        iCodeController.setDialogStage(stage);
        stage.showAndWait();
        bKeyAnswer = iCodeController.isKeySettled();
        updateKeyCheckbox();
    }

    // выбор чекбокса single/multiple languages
    @FXML
    public void cBoxSingleLang(ActionEvent act) {
//        singleLang = checkBox_singleLang.isSelected();
//        updateLangs();
    }

    // апдейт чекбокса (ключ задан, не задан)
    private void updateKeyCheckbox() {
        checkBox_isOkKey.setSelected(bKeyAnswer);
    }

    // апдейт текста (поле с описанием выбранного шифра)
    private void updateTextArea() {
        code_description.setText(currentCodeID.getCodeDescription());
    }

    // апдейт чекбокса выбора языков
    private void updateLangs() {
        checkBox_singleLang.setSelected(singleLang);
        lang_to.setDisable(singleLang);
    }

    // изменение активности ряда полей (объявляются неактивными до тех пор, пока не будет выбран один из шифров)
    private void disableAllFields(boolean bFlag) {
        languages_box.setDisable(bFlag);
        inputText_area.setDisable(bFlag);
        setKey_box.setDisable(bFlag);
        down_mainButton_box.setDisable(bFlag);
    }
}



// initialize

//        HashMap<String,Integer> myMap = new HashMap<>();
//        myMap.put("var", 1);
//        myMap.put("this", 2);
//        myMap.put("check", 3);
//          ObservableList<HashMap> items1 = FXCollections.observableArrayList(myMap);
//          checkList.setItems(items);
//          checkList.getItems().setAll(items1);

//        checkList.setCellFactory(new Callback<ListView<Color>, ListCell<Color>>()
//        {
//            @Override public ListCell<Color> call(ListView<Color> p)
//            {
//                return new ListCell<Color>()
//                {
//                    private final Rectangle rectangle;
//                    {
//                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//                        rectangle = new Rectangle(10, 10);
//                    }
//
//                    @Override protected void updateItem(Color item, boolean empty) {
//                        super.updateItem(item, empty);
//
//                        if (item == null || empty) {
//                            setGraphic(null);
//                        } else {
//                            rectangle.setFill(item);
//                            setGraphic(rectangle);
//                        }
//                    }
//                };
//            }
//        });