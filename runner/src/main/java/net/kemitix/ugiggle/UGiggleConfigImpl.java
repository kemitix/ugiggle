package net.kemitix.ugiggle;

import io.quarkus.arc.config.ConfigProperties;
import net.kemitix.ugiggle.service.UGiggleConfig;
import net.kemitix.ugiggle.trello.TrelloConfig;

@ConfigProperties(prefix = "ugiggle")
public class UGiggleConfigImpl implements UGiggleConfig, TrelloConfig {
    private String slushBoard;
    private String slushList;
    private String recipient;
    private String targetName;
    private String trelloKey;
    private String trelloAccessToken;
    private String amazonSesRegion;
    private String sender;

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
        this.recipient = System.getenv("UGIGGLE_RECIPIENT");
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
        this.trelloKey = System.getenv("TRELLO_KEY");
    }

    @Override
    public String getTrelloAccessToken() {
        return trelloAccessToken;
    }

    public void setTrelloAccessToken(String trelloAccessToken) {
        this.trelloAccessToken = System.getenv("TRELLO_SECRET");
    }

    @Override
    public String getAmazonSesRegion() {
        return amazonSesRegion;
    }

    public void setAmazonSesRegion(String amazonSesRegion) {
        this.amazonSesRegion = System.getenv("AMAZON_SES_REGION");
    }

    @Override
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = System.getenv("UGIGGLE_SENDER");
    }
}
