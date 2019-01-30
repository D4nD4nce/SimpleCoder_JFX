package ui;

import logic.code.AllCodes;

import java.util.HashMap;
import java.util.Map;

public class ResourcesInfo {

    /*
     * paths to resources
     */
    //private static final String MAIN_WIN_PATH = "../src/main/java/ui/resources/main_win.fxml";
    private static final String CAESAR_KEY_PATH = "../resources/set_caesar_key.fxml";
    private static final String SIMPLE_KEY_PATH = "../resources/set_caesar_key.fxml";
    private static final String EXAMPLE_KEY_PATH = "../resources/set_caesar_key.fxml";

    private final static Map<AllCodes, ControllerInfo> CODES_RESOURCES = new HashMap<>();
    static {
        CODES_RESOURCES.put(AllCodes.CAESAR_CODE, new ControllerInfo(CAESAR_KEY_PATH, "set key"));
        CODES_RESOURCES.put(AllCodes.SIMPLE_CODE, new ControllerInfo(SIMPLE_KEY_PATH, "set key"));
        CODES_RESOURCES.put(AllCodes.EXAMPLE_CODE, new ControllerInfo(EXAMPLE_KEY_PATH, "set key"));
    }

//    public static String getMainWinPath() {
//        return MAIN_WIN_PATH;
//    }

    public static String getCodePath(AllCodes codeID) {
        return CODES_RESOURCES.get(codeID).getResPath();
    }

    public static String getKeyWinTitle(AllCodes codeID) {
        return CODES_RESOURCES.get(codeID).getKeyWinTitle();
    }

    private static class ControllerInfo {
        private String resPath;
        private String keyWinTitle;

        ControllerInfo(String resPath, String keyWinTitle) {
            this.resPath = resPath;
            this.keyWinTitle = keyWinTitle;
        }

        public String getResPath() {
            return resPath;
        }

        public String getKeyWinTitle() {
            return keyWinTitle;
        }
    }
}
