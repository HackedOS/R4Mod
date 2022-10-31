package xyz.hackos.r4tech.r4mod.others;

public class Config {
    private String botToken;
    private String chatChannelID;
    private String serverStartingPrompt;
    private String serverStartedPrompt;
    private String serverStoppingPrompt;
    private String serverStoppedPrompt;

    public Config(String botToken, String chatChannelID, String serverStartingPrompt, String serverStartedPrompt,
                  String serverStoppingPrompt, String serverStoppedPrompt) {
        this.botToken = botToken;
        this.chatChannelID = chatChannelID;
        this.serverStartingPrompt = serverStartingPrompt;
        this.serverStartedPrompt = serverStartedPrompt;
        this.serverStoppingPrompt = serverStoppingPrompt;
        this.serverStoppedPrompt = serverStoppedPrompt;
    }

    public String botToken() {
        return botToken;
    }

    public String chatChannelID() {
        return chatChannelID;
    }

    public String serverStartingPrompt() {
        return serverStartingPrompt;
    }

    public String serverStartedPrompt() {
        return serverStartedPrompt;
    }

    public String serverStoppingPrompt() {
        return serverStoppingPrompt;
    }

    public String serverStoppedPrompt() {
        return serverStoppedPrompt;
    }
}
