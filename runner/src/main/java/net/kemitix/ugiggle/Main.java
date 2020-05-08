package net.kemitix.ugiggle;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class Main implements QuarkusApplication {
    @Override
    public int run(String... args) throws Exception {
        System.out.println("Hello, World!");
        return 0;
    }
}
