package logic.language.alphabets;

import logic.language.Language;

public class English extends Language {

    public English() {
        this.letters = new char[26];
        int b = 0;
        for(char i = 'a'; i <= 'z'; i++) {
            this.letters[b] = i;
            int index = b;
            this.lettersMap.put(i,index);
            this.lstLetters.add(index, i);
            b++;
        }
    }
}
