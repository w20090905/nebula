package nebula.expr;

@Deprecated
public interface LogicExp extends Exp {
	LogicExp and(LogicExp v);

	LogicExp or(LogicExp v);

	LogicExp not();

	boolean exec();
}