package test.language.custome.nest.def;

public interface Number {
    Number plus(Number value);

    Number minus(Number value);

    Number multiply(Number value);

    Number power(Number value);

    Number mod(Number value);
    
    Number avgBy(long count);
    
    int compareTo(Number value);
}
