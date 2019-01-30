package logic.code.helpers;

import java.util.ArrayList;
import java.util.List;

public class CodingHelper {

    private final static String ERROR_INDEX = "Error: InfoHelper: index doesn't match the list";

    //private String inputString;                                     // original input String
    private List<SymbolH> lstIn;                                    // input list of symbols
    private List<Character> lstOut;                                 // list with result symbols
    private List<SymbolH> currentString;                            // list with symbols of current string

    public CodingHelper(String string) {
        //this.inputString = string;
        initialize(string.toCharArray());
    }

    private void initialize (char[] masLetters) {
        this.lstIn = new ArrayList<>();
        this.lstOut = new ArrayList<>();
        this.currentString = new ArrayList<>();
        for (int i = 0; i < masLetters.length; i++) {
            SymbolH s = new SymbolH(masLetters[i], i);
            lstIn.add(i, s);
        }
    }

    public List<Character> getCurrentSymbols() {
        List<Character> outList = new ArrayList<>();
        currentString.forEach(symbolH -> outList.add(symbolH.getSymbol()));
        return outList;
    }

    public void setSymbol(Character character) {
        if(character != null) {
            lstOut.add(character);
        }
    }

    public void setString(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        for (char symbol : str.toCharArray()) {
            lstOut.add(symbol);
        }
    }

//    public boolean nextLetters(int lettersNumber) {
//        if (isLast(currentSymbol, lstIn)) {
//            return false;
//        }
//        currentSymbol = lstIn.get(currentSymbol.getPosition() + 1);
//        return true;
//    }

    public Character getNextLetter() {
        List<Character> nextLettersList = getNextLetters(1);
        if (nextLettersList == null) {
            return null;
        }
        return nextLettersList.get(0);
    }

    public List<Character> getNextLetters(int lettersNumber) {
        if (lettersNumber < 1 || isLast(lettersNumber - 1)) {
            return null;
        }
        int currentIndex = 0;
        if(isCurrentStringValid()) {
            SymbolH currentLastSymbol = currentString.get(currentString.size() - 1);
            currentIndex = currentLastSymbol.getPosition() + 1;
        }
        currentString.clear();
        for(int i = currentIndex; i <= currentIndex + lettersNumber; i++) {
            currentString.add(lstIn.get(i));
        }
        return getCurrentSymbols();
    }

//    public boolean previousLetter() {
//        if (isFirst(currentSymbol, lstIn)) {
//            return false;
//        }
//        currentSymbol = lstIn.get(currentSymbol.getPosition() - 1);
//        return true;
//    }

    public Character getPreviousLetter() {
        List<Character> previousLettersList = getPreviousLetters(1);
        if (previousLettersList == null) {
            return null;
        }
        return previousLettersList.get(0);
    }

    public List<Character> getPreviousLetters(int lettersNumber) {
        if (lettersNumber < 1 || isFirst(lettersNumber - 1)) {
            return null;
        }
        int currentIndex = 0;
        if (isCurrentStringValid()) {
            SymbolH currentFirstSymbol = currentString.get(0);
            currentIndex = currentFirstSymbol.getPosition() - 1;
        }
        currentString.clear();
        for(int i = currentIndex; i > currentIndex - lettersNumber; i--) {
            currentString.add(lstIn.get(i));
        }
        return getCurrentSymbols();
    }

    public int getInputStringLength() {
        return lstIn.size();
    }

    public int getResultStringLength() {
        return lstOut.size();
    }

    public int countLeftSymbols(boolean fromStartToEnd) {
        int currentIndex = 0;
        if(isCurrentStringValid()) {
            currentIndex = (fromStartToEnd) ? currentString.get(currentString.size() - 1).getPosition() : currentString.get(0).getPosition();
        }
        return lstIn.size() - currentIndex;
    }

    private boolean isCurrentStringValid() {
        return currentString != null && !currentString.isEmpty();
    }

    private boolean isFirst(int offset) {
        if (!isCurrentStringValid()) {
            return false;
        }
        SymbolH currentFirstSymbol = currentString.get(0);
        if (!isSymbolValid(currentFirstSymbol, lstIn)) {
            return false;
        }
        return currentFirstSymbol.getPosition() - offset <= 0;
    }

    private boolean isLast(int offset) {
        if (!isCurrentStringValid()) {
            return false;
        }
        SymbolH currentLastSymbol = currentString.get(currentString.size() - 1);
        if (!isSymbolValid(currentLastSymbol, lstIn)) {
            return false;
        }
        return currentLastSymbol.getPosition() + offset >= lstIn.size() - 1;
    }

    private boolean isSymbolValid(SymbolH symbolH, List<SymbolH> lst) {
        if (lst.indexOf(symbolH) != symbolH.getPosition()) {
            System.out.println(ERROR_INDEX);
            // error
            return false;
        }
        return true;
    }

    public String getResult() {
        StringBuilder stringBuilder = new StringBuilder();
        lstOut.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
