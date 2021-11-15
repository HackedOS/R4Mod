package xyz.hackos.r4tech.r4mod.discord;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.minecraft.server.command.CommandManager;
import xyz.hackos.r4tech.r4mod.R4Mod;
import java.util.Objects;
import static xyz.hackos.r4tech.r4mod.R4Mod.api;
import static xyz.hackos.r4tech.r4mod.R4Mod.config;
import static xyz.hackos.r4tech.r4mod.discord.commands.BotsDiscordCommand.BotsCommand;
import static xyz.hackos.r4tech.r4mod.discord.commands.OnlineDiscordCommand.OnlineCommand;


public class DiscordChatBridge {
    //For sending messages to Discord and Minecraft
    public static void sendMessage(String message) {
        if (R4Mod.jdaReady) {
            Objects.requireNonNull(api.getTextChannelById(config.getChatChannelID())).sendMessage(message).queue();
        }
    }
    public static void sendConsoleMessage(String message) {
        if (R4Mod.jdaReady) {
            Objects.requireNonNull(api.getTextChannelById(config.getConsoleChannelID())).sendMessage(message).queue();
        }
    }

    public static void discordCommand(String commandW, MessageReceivedEvent event) {
        if (!event.getChannel().getId().equals(config.getChatChannelID())){
            event.getChannel().sendMessage("Sorry <@" + event.getMember().getId() + "> this is not the chat channel").queue();
            return;
        }
        String command = commandW.split("!")[1];
        if (command.equals("online")) OnlineCommand(event);
        else if (command.equals("bots")) BotsCommand(event);

    }

    public static void consoleCommand(String content,MessageReceivedEvent event){

        if (!Objects.requireNonNull(event.getMember()).getRoles().contains(event.getGuild().getRoleById(config.getCommandsAccessRoleID()))) {
            event.getChannel().sendMessage("Sorry <@" + event.getMember().getId() + "> you don't have access to the console.").queue();
            return;
        }

        if (!event.getChannel().equals(event.getGuild().getTextChannelById(config.getConsoleChannelID()))) {
            event.getChannel().sendMessage("Sorry <@" + event.getMember().getId() + "> this is not the console channel").queue();
            return;
        }
        CommandManager command = new CommandManager(CommandManager.RegistrationEnvironment.DEDICATED);
        try {
            //Attempt to send command
            command.execute(R4Mod.server.getCommandSource(), content);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

