package nebula.lang;

public interface Vo {
    Object get(String name);
    void put(String name,Object value);
    Object get(int i);
    int size();    
}


//
//public interface Vo extends V {
//    void put(String name, Object value);
//
//    Object get(String name);
//
//    Object get(int i);
//
//    int size();
//
//    String getIndentify();
//
//    // void add(V v);
//}
