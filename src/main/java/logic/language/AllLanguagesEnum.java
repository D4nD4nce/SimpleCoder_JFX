package logic.language;

import java.util.ArrayList;
import java.util.List;

public enum AllLanguagesEnum {
    RUSSIAN,
    ENGLISH;

    public static List<String> getLanguages() {
        List<String> languages = new ArrayList<>();
        for (AllLanguagesEnum lang : AllLanguagesEnum.values()) {
            languages.add(lang.name());
        }
        return languages;
    }


//    public static AllLanguagesEnum choose()
//    {
//        System.out.println("1. Russian");
//        System.out.println("2. English");
//        System.out.println("Choose mainLanguage (print number):");
//        Scanner input = new Scanner(System.in);
//        int chosenNumber;
//        //
//        if (input.hasNextInt() && isValid(chosenNumber = input.nextInt()))
//        {
//            return AllLanguagesEnum.values()[chosenNumber - 1];
//        }
//        else {
//            return choose();
//        }
//    }

    // проверка, находится ли введенное значение в пределах количества доступных вариантов
//    private static boolean isValid(int number)
//    {
//        return (1 <= number && number <= AllLanguagesEnum.values().length);
//    }
}
