package logic.code.key;

import logic.code.AllCodes;
import logic.language.Language;

import java.util.List;

public class KeyCaesar extends KeyConstructor {

    private static final String KEY_DESCRIPTION = "input key - number to move alphabet to the write side for all letters";
    private static final String ERROR_NO_KEY = "key hasn't been put";
    private static final String ERROR_KEY_EQUALS_ALPHABET_LENGTH = "key is the same as alphabet length, nothing will change after encoding";
    private static final String ERROR_NEGATIVE_VALUE = "set value above 0";

    private Integer caesarKey;
    //private String mistakeMassage;

    public KeyCaesar(Language currentLang, AllCodes codeID) {
        super(currentLang, codeID);
    }

    @Override
    public List<KeyCode> getInputForm() {
        KeyCode key = new CaesarKeyCode(KEY_DESCRIPTION, currentLanguage);
        template.add(key);
        return template;
    }

    @Override
    public void setKey(List<KeyCode> keys) {
        if ((keys == null) || keys.isEmpty()) {
            return;
        }
        KeyCode keyCode = keys.get(0);
        if (keyCode == null) {
            return;
        }
        String key = keyCode.getOneKey();
        if ((key != null) && (!key.isEmpty())) {
            caesarKey = Integer.valueOf(key);
        }
    }

    @Override
    public boolean isKeyValid() {
        return isInputValid(String.valueOf(caesarKey), currentLanguage) == null;
    }

//    @Override
//    public String isKeyValid(boolean getDescription) {
//        if (isKeyValid() || !getDescription) {
//            return null;
//        }
//        return mistakeMassage;
//    }

    public int getCaesarKey() {
        return caesarKey;
    }

    private static String isInputValid(String key, Language currentLanguage) {
        if (key == null || key.isEmpty()) {
            return ERROR_NO_KEY;
        }
        if (Integer.valueOf(key) == currentLanguage.getSize()) {
            return ERROR_KEY_EQUALS_ALPHABET_LENGTH;
        }
        if (Integer.valueOf(key) <= 0) {
            return ERROR_NEGATIVE_VALUE;
        }
        return null;
    }

    /**
     * inner class to check and save user input while setting key
     */
    private class CaesarKeyCode extends KeyCode {

        private CaesarKeyCode(String keyDescription, Language language) {
            super(keyDescription, language);
        }

        @Override
        public String checkInput(String input) {
            return isInputValid(input, language);
        }
    }
}
