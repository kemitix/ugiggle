package net.kemitix.ugiggle;

import io.quarkus.arc.config.ConfigProperties;
import net.kemitix.ugiggle.service.UGiggleConfig;

@ConfigProperties(prefix = "ugiggle")
public class UGiggleConfigImpl implements UGiggleConfig {
    private String slushBoard;
    private String slushList;
    private String recipient;
    private String targetName;

    public String getSlushBoard() {
        return slushBoard;
    }

    public void setSlushBoard(String slushBoard) {
        this.slushBoard = slushBoard;
    }

    public String getSlushList() {
        return slushList;
    }

    public void setSlushList(String slushList) {
        this.slushList = slushList;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
}
