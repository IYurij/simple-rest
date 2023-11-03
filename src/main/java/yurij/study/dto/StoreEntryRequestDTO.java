package yurij.study.dto;

/**
 * StoreEntryRequest DTO class.
 */
public class StoreEntryRequestDTO {
    private String key;
    private String value;
    private long ttl;

    public StoreEntryRequestDTO() {

    }

    public StoreEntryRequestDTO(String key, String value, long ttl) {
        this.key = key;
        this.value = value;
        this.ttl = ttl;
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

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }
}
