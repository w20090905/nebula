package it.trace.nebula.rest.binder;

import java.lang.reflect.Method;

public interface DataBinder<T> {

    Object[] bind(Context context, Method method);

}
