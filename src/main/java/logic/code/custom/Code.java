package logic.code.custom;

import logic.code.AllCodes;
import logic.code.key.IKeyConstructor;
import logic.code.helpers.CodingHelper;
import logic.language.Language;

public abstract class Code {
    protected Language mainLanguage;
    protected boolean encode;
    protected CodingHelper codingHelper;

    Code(boolean encode) {
        this.encode = encode;
    }

    public abstract void setInfo(IKeyConstructor key, String inputText, Language mainLanguage);

    public abstract IKeyConstructor getKeyConstructor(AllCodes codeID);

    public abstract String getResult();

    public static Code getNewCode(AllCodes codeId, boolean encode) {
        switch (codeId) {
            case CAESAR_CODE:
                return new CodeCaesar(encode);
            case SIMPLE_CODE:
                return new CodeSimple(encode);
            case EXAMPLE_CODE:
                return new CodeExample(encode);
            default:
                return null;
        }
    }
}
