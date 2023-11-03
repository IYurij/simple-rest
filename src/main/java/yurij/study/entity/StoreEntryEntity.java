package yurij.study.entity;

/**
 * StoreEntry entity class.
 */
public class StoreEntryEntity {
    private String key;
    private String value;
    private long expiryTimestamp;

    public StoreEntryEntity() {

    }

    public StoreEntryEntity(String key, String value, long expiryTimestamp) {
        this.key = key;
        this.value = value;
        this.expiryTimestamp = expiryTimestamp;
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

    public long getExpiryTimestamp() {
        return expiryTimestamp;
    }

    public void setExpiryTimestamp(long expiryTimestamp) {
        this.expiryTimestamp = expiryTimestamp;
    }
}
