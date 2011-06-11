package module;

import it.trace.mvc.executor.ExecutorFinder;
import it.trace.mvc.executor.SimpleRESTfulExecutorFinder;

import com.google.inject.Binder;
import com.google.inject.Module;

public class FrameworkModule implements Module {

    public void configure(Binder binder) {

        // ExecutorFinder
        binder.bind(ExecutorFinder.class).to(SimpleRESTfulExecutorFinder.class);

    }

}
