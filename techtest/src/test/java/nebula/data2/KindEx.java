package nebula.data2;

interface KindEx<T>  extends Kind<T>{
	T apply(T newT);
//	T applyAll();
}
