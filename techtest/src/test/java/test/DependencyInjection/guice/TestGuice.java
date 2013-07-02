package test.DependencyInjection.guice;


import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.TypeLiteral;

public class TestGuice {

    /**
     * @param args
     */
    public static void main(String[] args) {
        /*
         * Guice.createInjector() takes your Modules, and returns a new Injector
         * instance. Most applications will call this method exactly once, in
         * their main() method.
         */
        Injector injector = Guice.createInjector(new AbstractModule(){
            protected void configure() {
                this.bind(new TypeLiteral<TO<String>>(){}).to(TOImp.class);
            }
        });

        /*
         * Now that we've got the injector, we can build objects.
         */
        //TO<String> billingService = injector.getInstance(TO<String>.class);
        TestS billingService = injector.getInstance(TestS.class);
        System.out.println(billingService.getClass().getName());

    }

}
