package it.trace.mvc.executor;

import it.trace.mvc.HttpMethod;
import it.trace.mvc.config.Configuration;

public abstract class ExecutorFinder {

    protected Configuration config;

    public void init(Configuration config) {
        this.config = config;
    }

    public abstract HttpExecutor find(HttpMethod httpMethod, String path);

}
