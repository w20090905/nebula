package it.trace.mvc.inject;

import java.io.Serializable;

public interface Container extends Serializable {

    <T> T getInstance(Class<T> type);

}