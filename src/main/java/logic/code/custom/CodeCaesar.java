package logic.code.custom;

import logic.code.AllCodes;
import logic.code.key.KeyCaesar;
import logic.code.helpers.CodingHelper;
import logic.language.Language;
import logic.code.key.IKeyConstructor;

import java.util.Map;

public class CodeCaesar extends Code {

    private int key;

    CodeCaesar(boolean encode) {
        super(encode);
    }

    @Override
    public void setInfo(IKeyConstructor key, String inputText, Language mainLanguage) {
        this.key = ((KeyCaesar)key).getCaesarKey();
        this.mainLanguage = mainLanguage;
        codingHelper = new CodingHelper(inputText);
    }

    @Override
    public IKeyConstructor getKeyConstructor(AllCodes codeID) {
        return new KeyCaesar(mainLanguage, codeID);
    }

    @Override
    public String getResult() {
        makeCode();
        return codingHelper.getResult();
    }

    private void makeCode() {
        Character character;
        while ((character = codingHelper.getNextLetter()) != null) {
            codingHelper.setSymbol((mainLanguage.isValidLetter(character)) ? moveLetter(character) : character);
        }
    }

    private char moveLetter(char character) {
        char letters[] = mainLanguage.getAlphabet();
        Map<Character, Integer> map = mainLanguage.getMap();
        int firstIndex = map.get(character);
        int codeIndex = (encode) ? getIndex_encode(firstIndex) : getIndex_decode(firstIndex);
        return letters[codeIndex];
    }

    private int getIndex_encode(int i) {
        int size = mainLanguage.getSize();
        return ((i + key) >= size) ? (i + key - size) : (i + key);
    }

    private int getIndex_decode(int i) {
        int size = mainLanguage.getSize();
        return ((i - key) < 0) ? (i - key + size) : (i - key);
    }


}
