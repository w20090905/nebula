package nebula.data.util;

import com.google.common.base.Function;

public class Functions{
	
	public<F,I,T> Function<F, T> link(Function<F, I> fromF,Function<I, T> fromI){
		final Function<F, I> f = fromF;
		final Function<I, T> i = fromI;
		return new Function<F, T>() {
			@Override
			public T apply(F from) {
				return i.apply(f.apply(from));
			}
		};
	}

	public<F,I,J,T> Function<F, T> link(Function<F, I> fromF,Function<I, J> fromI,Function<J, T> fromJ){
		final Function<F, I> f = fromF;
		final Function<I, J> i = fromI;
		final Function<J, T> j = fromJ;
		return new Function<F, T>() {

			@Override
			public T apply(F from) {
				return j.apply(i.apply(f.apply(from)));
			}
		};
	}
	
}
