package logic.language.alphabets;

import logic.language.Language;

public class Russian extends Language {

    public Russian() {
        this.letters = new char[32];
        int b = 0;
        for(char i = 'а'; i <= 'я'; i++) {
            this.letters[b] = i;
            int index = b;
            this.lettersMap.put(i,index);
            this.lstLetters.add(index, i);
            b++;
        }
    }
}
