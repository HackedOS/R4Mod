package xyz.hackos.r4tech.r4mod.discord;

import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import xyz.hackos.r4tech.r4mod.R4Mod;

import java.util.Objects;

import static xyz.hackos.r4tech.r4mod.R4Mod.api;
import static xyz.hackos.r4tech.r4mod.R4Mod.config;


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
}

