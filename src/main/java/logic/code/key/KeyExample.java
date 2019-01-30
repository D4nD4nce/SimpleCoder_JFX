package logic.code.key;

import logic.code.AllCodes;
import logic.language.Language;

import java.util.List;

public class KeyExample extends KeyConstructor {

    public KeyExample(Language currentLang, AllCodes codeID) {
        super(currentLang, codeID);
    }

    @Override
    public List<KeyCode> getInputForm() {
        return null;
    }

    @Override
    public void setKey(List<KeyCode> keys) {

    }

    @Override
    public boolean isKeyValid() {
        return false;
    }

//    @Override
//    public String isKeyValid(boolean getDescription) {
//        return null;
//    }
}
