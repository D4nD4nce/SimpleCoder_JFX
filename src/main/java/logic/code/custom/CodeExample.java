package logic.code.custom;

import logic.code.AllCodes;
import logic.code.key.KeyExample;
import logic.language.Language;
import logic.code.key.IKeyConstructor;

public class CodeExample extends Code {

    public CodeExample(boolean encode) {
        super(encode);
    }

    @Override
    public void setInfo(IKeyConstructor key, String inputText, Language mainLanguage) {

    }

    @Override
    public IKeyConstructor getKeyConstructor(AllCodes codeID) {
        return new KeyExample(mainLanguage, codeID);
    }

    @Override
    public String getResult() {
        return null;
    }
}
