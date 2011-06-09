package it.trace.nebula.rest.binder;

public interface DataBinder<T> {

    Object[] bind(Context context);

}
