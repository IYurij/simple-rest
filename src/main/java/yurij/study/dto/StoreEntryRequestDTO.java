package yurij.study.dto;

/**
 * StoreEntry DTO class.
 */
public class StoreEntryRequestDTO {
    private String key;
    private String value;

    public StoreEntryRequestDTO() {

    }

    public StoreEntryRequestDTO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
