package xyz.hackos.r4tech.r4mod.discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import xyz.hackos.r4tech.r4mod.R4Mod;
import java.util.Objects;
import static xyz.hackos.r4tech.r4mod.R4Mod.api;
import static xyz.hackos.r4tech.r4mod.R4Mod.config;
import static xyz.hackos.r4tech.r4mod.discord.commands.OnlineDiscordCommand.OnlineCommand;


public class DiscordChatBridge {
    //For sending messages to Discord and Minecraft
    public static void sendMessage(String message) {
        if (R4Mod.jdaReady) {
            Objects.requireNonNull(api.getTextChannelById(config.getChatChannelID())).sendMessage(message).queue();

//            if (sendToDiscordConsole) {
//                Objects.requireNonNull(api.getTextChannelById(config.getConsoleChannelID())).sendMessage(message.getString()).queue();
//            }
        }
    }

    public static void discordCommand(String commandW, MessageReceivedEvent event) {
        String command = commandW.split("!")[1];
        System.out.println(command);
        if (command.equals("online")) OnlineCommand(event);

    }
}

