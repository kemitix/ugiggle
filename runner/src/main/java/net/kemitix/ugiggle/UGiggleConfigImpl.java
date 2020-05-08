package net.kemitix.ugiggle;

import io.quarkus.arc.config.ConfigProperties;
import net.kemitix.ugiggle.service.UGiggleConfig;

@ConfigProperties(prefix = "ugiggle")
public class UGiggleConfigImpl implements UGiggleConfig {
    private String slushBoard;
    private String slushList;
    private String recipient;
    private String targetName;
    private String trelloKey;
    private String trelloAccessToken;

    @Override
    public String getSlushBoard() {
        return slushBoard;
    }

    public void setSlushBoard(String slushBoard) {
        this.slushBoard = slushBoard;
    }

    @Override
    public String getSlushList() {
        return slushList;
    }

    public void setSlushList(String slushList) {
        this.slushList = slushList;
    }

    @Override
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    @Override
    public String getTrelloKey() {
        return trelloKey;
    }

    public void setTrelloKey(String trelloKey) {
        this.trelloKey = trelloKey;
    }

    @Override
    public String getTrelloAccessToken() {
        return trelloAccessToken;
    }

    public void setTrelloAccessToken(String trelloAccessToken) {
        this.trelloAccessToken = trelloAccessToken;
    }
}
