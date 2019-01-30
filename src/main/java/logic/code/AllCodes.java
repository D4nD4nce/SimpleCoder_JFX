package logic.code;

import java.util.ArrayList;
import java.util.List;

public enum AllCodes {
    CAESAR_CODE     (AllCodesInfo.CAESAR_NAME, AllCodesInfo.CAESAR_DESCRIPTION, AllCodesInfo.CAESAR_KEY_DESCRIPTION, true),
    SIMPLE_CODE     (AllCodesInfo.SIMPLE_NAME, AllCodesInfo.SIMPLE_DESCRIPTION, AllCodesInfo.SIMPLE_KEY_DESCRIPTION, true),
    EXAMPLE_CODE    (AllCodesInfo.EXAMPLE_NAME, AllCodesInfo.EXAMPLE_DESCRIPTION, AllCodesInfo.EXAMPLE_KEY_DESCRIPTION, false);

    private String codeName;
    private String codeDescription;
    private String keyDescription;
    private boolean useSingleLanguageOny;

    AllCodes(String codeName, String codeDescription, String keyDescription, boolean useSingleLanguageOny) {
        this.codeName = codeName;
        this.codeDescription = codeDescription;
        this.keyDescription = keyDescription;
        this.useSingleLanguageOny = useSingleLanguageOny;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getCodeDescription() {
        return codeDescription;
    }

    public String getKeyDescription() {
        return keyDescription;
    }

    public boolean isSingleLanguageOny() {
        return useSingleLanguageOny;
    }

    public static List<String> getCodesNamesList() {
        List<String> codesList = new ArrayList<>();
        for (int i = 0; i < AllCodes.values().length; ++i) {
            codesList.add(AllCodes.values()[i].name());
        }
        return codesList;
    }

    public static class AllCodesInfo {
        /*
         * names
         */
        private static final String CAESAR_NAME = "Шифр Цезаря";
        private static final String SIMPLE_NAME = "Шифр простой подстановки";
        private static final String EXAMPLE_NAME = "тест";

        /*
         * code descriptions
         */
        private static final String CAESAR_DESCRIPTION =
                "Код, который якобы был придуман и использовался еще самим Юлием Цезарем: " +
                        "при переводе текста символы сдвигаются в соответствии с выбранным алфавитом";
        private static final String SIMPLE_DESCRIPTION =
                "Простейший шифр: каждый символ введенного текста заменяется на цифру в соответствии с ее местов в алфавите." +
                        "Далее цифры 0-9 шифруются любыми буквами, и подставляются вместо введенного текста.";
        private static final String EXAMPLE_DESCRIPTION =
                "тестовый текст";

        /*
         * key descriptions
         */
        private static final String CAESAR_KEY_DESCRIPTION =
                "Ключ определяет смещение по алфавиту - каждая буква будет заменена соответственно смещению. " +
                        "Цифра должна быть больше нуля и не равна количеству букв в выбранном алфавите.";
        private static final String SIMPLE_KEY_DESCRIPTION =
                "В ключе все десять чисел шифруются буквами. Затем в веденном тексте все буквы заменяются на их порядковый номер" +
                        "в алфавите, но вместо цифр выставляются буквы из ключа. " +
                        "Для каждого числа введите одну букву из текущего алфавита.";
        private static final String EXAMPLE_KEY_DESCRIPTION =
                "тестовое описание ключа.";
    }
}
