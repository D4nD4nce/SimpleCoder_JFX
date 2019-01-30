package logic.code.custom;

import logic.code.AllCodes;
import logic.code.key.KeySimple;
import logic.code.helpers.CodingHelper;
import logic.language.Language;
import logic.code.key.IKeyConstructor;

import java.util.HashMap;
import java.util.Map;

public class CodeSimple extends Code {

    private Map<Integer,Character> simpleKeyEncode;     // only for encode
    private Map<Character,Integer> simpleKeyDecode;     // only for decode

    CodeSimple(boolean encode) {
        super(encode);
    }

    @Override
    public void setInfo(IKeyConstructor key, String inputText, Language mainLanguage) {
        this.mainLanguage = mainLanguage;
        this.codingHelper = new CodingHelper(inputText);
        if(encode) {
            this.simpleKeyEncode = ((KeySimple)key).getSimpleKey();
        } else {
            this.simpleKeyDecode = new HashMap<>();
            Map<Integer,Character> gotKey = ((KeySimple)key).getSimpleKey();
            gotKey.forEach((k,v) -> simpleKeyDecode.put(v,k));
        }
    }

    @Override
    public IKeyConstructor getKeyConstructor(AllCodes codeID) {
        return new KeySimple(mainLanguage, codeID);
    }

    @Override
    public String getResult() {
        makeCode();
        return codingHelper.getResult();
    }

    private void makeCode() {
        if (encode) {
            Character currentChar;
            while ((currentChar = codingHelper.getNextLetter()) != null) {
                codingHelper.setString((mainLanguage.isValidLetter(currentChar)) ? encodeMoving(currentChar) : String.valueOf(currentChar));
            }
        } else {
            while (codingHelper.countLeftSymbols(true) > 0) {
                Character firstCharacter = findFirstLetter();
                Character secondCharacter = findSecondLetter();
                if (firstCharacter != null) {
                    String foundValue = String.valueOf((secondCharacter != null) ? firstCharacter + secondCharacter : firstCharacter);
                    codingHelper.setString(decodeMoving(foundValue));
                }
            }
        }
    }


    /**
     * ---------- encode functions ----------
     */

    private String encodeMoving(char h) {
        StringBuilder str = new StringBuilder();
        int currentIndex = mainLanguage.getMap().get(h);
        int decade = currentIndex / 10;
        int number = currentIndex % 10;
        if (decade > 0) {
            str.append(getEncodedLetter(decade));
        }
        str.append(getEncodedLetter(number));
        str.append(" ");
        return str.toString();
    }

    private Character getEncodedLetter(int number) {
        if(!simpleKeyEncode.containsKey(number)) {
            // error
        }
        return simpleKeyEncode.get(number);
    }


    /**
     * ---------- decode functions ----------
     */

    private Character findFirstLetter() {
        Character character;
        while (!isCodeSymbol(character = codingHelper.getNextLetter())) {
            codingHelper.setSymbol(character);
        }
        return character;
    }

    private Character findSecondLetter() {
        Character character;
        if (!isCodeSymbol(character = codingHelper.getNextLetter())) {
            codingHelper.setSymbol(character);
            return null;
        }
        return character;
    }

    private String decodeMoving(String str) {
        int decade = 0;
        int number = 0;
        if (str.length() > 1) {
            decade = getDecodedNumber(str.charAt(0)) * 10;                  // получить десятки, обозначающие местоположение нужной буквы согласно коду
            number = getDecodedNumber(str.charAt(1));                       // получить единицы, -//--//--//-
        } else {
            number = getDecodedNumber(str.charAt(0));                       // получить единицы при отсутсвии десятков (первый символ слева-направо)
        }
        char cResult = mainLanguage.getLstLetters().get(decade + number);   // складываем полученные числа кода (десятки + единицы) и получаем число, которое указывает порядковый номер зашифрованной буквы.
        return String.valueOf(cResult);
    }

    private int getDecodedNumber(Character character) {
        return simpleKeyDecode.get(character);
    }

    private boolean isCodeSymbol(Character character) {
        return simpleKeyDecode.containsKey(character);
    }
}
