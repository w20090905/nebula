package test.guice;

import javax.inject.Inject;



public class TestS {
    final TO<String> to;
    
    @Inject
    public TestS(TO<String> to){
        this.to=to;
    }
}
