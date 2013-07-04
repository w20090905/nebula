package nebula.expr;

@Deprecated
public interface V_String extends V<String> {
	LogicExp inF(String name);
	LogicExp in(V<String> v);
	LogicExp in(String v);
}
