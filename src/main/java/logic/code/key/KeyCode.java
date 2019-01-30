package logic.code.key;

import logic.language.Language;

public abstract class KeyCode {

    private String oneKey;
    private String description;
    protected Language language;

    KeyCode(String keyDescription, Language language) {
        this.description = keyDescription;
        this.language = language;
    }

    public String getOneKey() {
        return oneKey;
    }

    public void setOneKey(String oneKey) {
        this.oneKey = oneKey;
    }

    public String getDescription() {
        return description;
    }

    /**
     * check if user write valid key
     * @param input - string from user
     * @return error message if input invalid or null if it's okay
     */
    public abstract String checkInput(String input);
}
