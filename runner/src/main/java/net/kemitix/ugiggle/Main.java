package net.kemitix.ugiggle;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import net.kemitix.ugiggle.service.SendReadingListService;

import javax.inject.Inject;
import java.util.logging.Logger;

@QuarkusMain
public class Main implements QuarkusApplication {

    private final static Logger LOG =
            Logger.getLogger(
                    Main.class.getName());

    private SendReadingListService service;

    public Main() {
    }

    @Inject
    public Main(SendReadingListService service) {
        this.service = service;
    }

    @Override
    public int run(String... args) {
        try {
            service.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
