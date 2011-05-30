package it.trace.mvc.binder;

public interface DataBinder<T> {

    void bind(Context context, T instance);

}
