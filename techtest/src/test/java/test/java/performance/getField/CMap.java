package test.java.performance.getField;

public interface CMap {
    void put(String key, String value);
    String get(String key);
    CMap addKey(String key);    
    CMap shadow();
    
    int code(String key);
    String[] getKeys();
    String[] getValues();
    
    CMap upgrade(CMap map);
}
