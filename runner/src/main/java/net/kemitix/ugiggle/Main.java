package net.kemitix.ugiggle;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.inject.Inject;
import java.util.logging.Logger;

@QuarkusMain
public class Main implements QuarkusApplication {

    private final static Logger LOG =
            Logger.getLogger(
                    Main.class.getName());

    private GreetingService service;

    public Main() {
    }

    @Inject
    public Main(GreetingService service) {
        this.service = service;
    }

    @Override
    public int run(String... args) throws Exception {
        LOG.info(service.hello());
        LOG.info(service.goodbye());
        return 0;
    }
}
