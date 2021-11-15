package xyz.hackos.r4tech.r4mod.others;

public class Config {
    private String botToken;
    private String commandsAccessRoleID;
    private String chatChannelID;
    private String consoleChannelID;
    private String serverStartingPrompt;
    private String serverStartedPrompt;
    private String serverStoppingPrompt;
    private String serverStoppedPrompt;

    public Config(String botToken, String chatChannelID, String consoleChannelID, String commandsAccessRoleID, String serverStartingPrompt, String serverStartedPrompt, String serverStoppingPrompt, String serverStoppedPrompt) {
        this.botToken = botToken;
        this.commandsAccessRoleID = commandsAccessRoleID;
        this.chatChannelID = chatChannelID;
        this.consoleChannelID = consoleChannelID;
        this.serverStartingPrompt = serverStartingPrompt;
        this.serverStartedPrompt = serverStartedPrompt;
        this.serverStoppingPrompt = serverStoppingPrompt;
        this.serverStoppedPrompt = serverStoppedPrompt;
    }

    public String getBotToken() {
        return botToken;
    }

    public String getChatChannelID() {
        return chatChannelID;
    }

    public String getConsoleChannelID() {
        return consoleChannelID;
    }

    public String getCommandsAccessRoleID() {
        return commandsAccessRoleID;
    }

    public String getServerStartingPrompt() {
        return serverStartingPrompt;
    }

    public String getServerStartedPrompt() {
        return serverStartedPrompt;
    }

    public String getServerStoppingPrompt() {
        return serverStoppingPrompt;
    }

    public String getServerStoppedPrompt() {
        return serverStoppedPrompt;
    }

}
