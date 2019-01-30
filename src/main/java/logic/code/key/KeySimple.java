package logic.code.key;

import logic.code.AllCodes;
import logic.language.Language;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeySimple extends KeyConstructor {

    private static final String KEY_INPUT_DESCRIPTION = "set code key for value: ";
    private static final String ERROR_NO_KEY = "key hasn't been put. try again";
    private static final String ERROR_KEY_TOO_LONG = "key too long. type only one letter";
    private static final String ERROR_WRONG_LANGUAGE = "wrong language, use chosen one";
    private static final int COUNT_NUMBERS = 10;  // 0 - 9

    private Map<Integer,Character> simpleKey = new HashMap<>();
    //String mistakeMessage;

    public KeySimple(Language currentLang, AllCodes codeID) {
        super(currentLang, codeID);
    }

    @Override
    public List<KeyCode> getInputForm() {
        KeyCode keyCode;
        for (int i = 0; i < COUNT_NUMBERS; i++) {
            String keyDescription = KEY_INPUT_DESCRIPTION + i;
            keyCode = new SimpleKeyCode(keyDescription, currentLanguage);
            template.add(keyCode);
        }
        return template;
    }

    @Override
    public void setKey(List<KeyCode> keys) {
        if (keys == null || keys.isEmpty()) {
            return;
        }
        for (int i = 0; i < keys.size(); i++) {
//            if (!simpleKey.containsKey(i)) {
//                return;
//            }
            KeyCode keyCode = keys.get(i);
            String thisKey = keyCode.getOneKey();
            if (thisKey != null && !thisKey.isEmpty()) {
                simpleKey.put(i, thisKey.charAt(0));
            }
        }
    }

    @Override
    public boolean isKeyValid() {
        return (simpleKey != null) && (!simpleKey.isEmpty()) && (simpleKey.size() == COUNT_NUMBERS);
    }

//    @Override
//    public String isKeyValid(boolean getDescription) {
//        return null;
//    }

    public Map<Integer,Character> getSimpleKey() {
        return simpleKey;
    }

    private static String isInputValid(String userText, Language language) {
        if (userText == null || userText.isEmpty()) {
            return ERROR_NO_KEY;
        }
        if (userText.length() > 1) {
            return ERROR_KEY_TOO_LONG;
        }
        char key = userText.charAt(0);
        if (!language.isValidLetter(key)) {
            return ERROR_WRONG_LANGUAGE;
        }
        return null;
    }

    /**
     * inner class to check and save user input while setting key
     */
    private class SimpleKeyCode extends KeyCode {

        private SimpleKeyCode(String keyDescription, Language language) {
            super(keyDescription, language);
        }

        @Override
        public String checkInput(String input) {
            return isInputValid(input, language);
        }
    }
}
