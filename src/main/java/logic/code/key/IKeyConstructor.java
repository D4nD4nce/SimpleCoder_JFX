package logic.code.key;

import java.util.List;

public interface IKeyConstructor {

    /**
     * full current key description
     * @return description for key input
     */
    String getDescription();

    /**
     * form for input
     * @return list with key descriptions or null if there are no key needed
     * first value in the map - description of the needed key
     * second value in the map - empty, needed to store user input
     */
    List<KeyCode> getInputForm();

    /**
     * set keys that were input
     * @param keys - list with keys
     * first value in the map - description of the needed key
     * second value in the map - empty, needed to store user input
     */
    void setKey(List<KeyCode> keys);

    /**
     * check is input valid
     * @return true if key is valid
     */
    boolean isKeyValid();

//    /**
//     * if param == true, then return message with description - what's wrong with the key
//     * @param getDescription - set true to get mistake description
//     * @return description of current mistake or null if key is valid
//     */
//    String isKeyValid(boolean getDescription);
}
