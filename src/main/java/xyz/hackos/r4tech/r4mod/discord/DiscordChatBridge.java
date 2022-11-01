package xyz.hackos.r4tech.r4mod.discord;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import xyz.hackos.r4tech.r4mod.R4Mod;
import java.util.Objects;
import static xyz.hackos.r4tech.r4mod.R4Mod.api;
import static xyz.hackos.r4tech.r4mod.R4Mod.config;
import static xyz.hackos.r4tech.r4mod.discord.commands.BotsDiscordCommand.BotsCommand;
import static xyz.hackos.r4tech.r4mod.discord.commands.TpsDiscordCommand.tpsCommand;
import static xyz.hackos.r4tech.r4mod.discord.commands.OnlineDiscordCommand.OnlineCommand;


public class DiscordChatBridge {
    //For sending messages to Discord and Minecraft
    public static void sendMessage(String message) {
        if (R4Mod.jdaReady) {
            Objects.requireNonNull(api.getTextChannelById(config.chatChannelID())).sendMessage(message).queue();
        }
    }

    public static void discordCommand(String command, SlashCommandEvent event) {
        event.deferReply().queue();
        switch (command) {
            case "online" -> OnlineCommand(event);
            case "bots" -> BotsCommand(event);
            case "tps" -> tpsCommand(event);
        }

    }
}

