package logic.code.key;

import logic.code.AllCodes;
import logic.language.Language;

import java.util.ArrayList;
import java.util.List;

public abstract class KeyConstructor implements IKeyConstructor {

    protected List<KeyCode> template = new ArrayList<>();
    protected Language currentLanguage;
    private AllCodes codeID;

    public KeyConstructor(Language currentLang, AllCodes codeID) {
        this.currentLanguage = currentLang;
        this.codeID = codeID;
    }

    @Override
    public String getDescription() {
        return codeID.getKeyDescription();
    }

    @Override
    public abstract List<KeyCode> getInputForm();

    @Override
    public abstract void setKey(List<KeyCode> keys);

    @Override
    public abstract boolean isKeyValid();

//    @Override
//    public abstract String isKeyValid(boolean getDescription);
}
