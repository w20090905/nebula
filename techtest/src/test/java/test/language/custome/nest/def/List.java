package test.language.custome.nest.def;

public interface List<T> {
    T get(int index);

    List<T> get(int from, int to);

    List<T> pick(int... indexes);
    List<T> pick(Method<Boolean,T> expr);
    List<T> pick(String expr);
    
    List<T> plus(List<T> to);

    void foreach(Method<Result,T> expr);
    Number sum(Method<Number,T> expr);
    Number avg(Method<Number,T> expr);
    Number max(Method<Number,T> expr);
    Number min(Method<Number,T> expr);
    void foreach(String expr);
    Number sum(String expr);
    Number avg(String expr);
    Number max(String expr);
    Number min(String expr);
}
