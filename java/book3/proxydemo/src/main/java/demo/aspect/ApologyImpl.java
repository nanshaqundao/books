package demo.aspect;

import demo.springaop.Apology;

public class ApologyImpl implements Apology {
    @Override
    public void saySorry(String name) {
        System.out.println("Sorry - ApologyImpl, " + name);
    }
}
