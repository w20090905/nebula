package it.trace.nebula.rest.dispatch;

import it.trace.nebula.rest.executor.ActionExecutor;


public interface ExecutorFinder {

    ActionExecutor lookup(String httpMethod, String accept, String path);

}
