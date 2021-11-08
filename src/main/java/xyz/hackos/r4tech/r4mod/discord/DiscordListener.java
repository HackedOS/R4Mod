package xyz.hackos.r4tech.r4mod.discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import xyz.hackos.r4tech.r4mod.R4Mod;

import javax.annotation.Nonnull;

import java.util.Objects;

import static xyz.hackos.r4tech.r4mod.R4Mod.api;
import static xyz.hackos.r4tech.r4mod.R4Mod.config;

public class DiscordListener extends ListenerAdapter {
    static String content;
    public DiscordListener(){
    }
    //Prompts
    public static void serverStartingMethod() {
        DiscordChatBridge.sendMessage(Text.of(R4Mod.config.getServerStartingPrompt()));
    }

    public static void serverStartedMethod() {
        DiscordChatBridge.sendMessage(Text.of(R4Mod.config.getServerStartedPrompt()));
    }

    public static void serverStoppingMethod() {
        DiscordChatBridge.sendMessage(Text.of(R4Mod.config.getServerStoppingPrompt()));
    }

    public static void serverStoppedMethod() {
        DiscordChatBridge.sendMessage(Text.of(config.getServerStoppedPrompt()));
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        R4Mod.jdaReady = true;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);

        //Makes sure the Author is not a bot
        if (event.getAuthor().isBot()) return;
        if (event.getChannel().getId().equals(config.getChatChannelID())){
        Message message = event.getMessage();
        content = message.getContentRaw();
        R4Mod.getServerVariable().getPlayerManager().broadcastChatMessage(Text.of("[" + event.getMember().getUser().getName() + "] " + content), MessageType.CHAT, R4Mod.senderUUID);
        }
    }
}
