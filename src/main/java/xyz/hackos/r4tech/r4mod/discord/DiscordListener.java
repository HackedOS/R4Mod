package xyz.hackos.r4tech.r4mod.discord;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import xyz.hackos.r4tech.r4mod.R4Mod;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

import static xyz.hackos.r4tech.r4mod.R4Mod.config;

public class DiscordListener extends ListenerAdapter {
    static String content;
    public DiscordListener(){
    }
    //Prompts
    public static void serverStartingMethod() {
        DiscordChatBridge.sendMessage(config.serverStartingPrompt());
    }

    public static void serverStartedMethod() {
        DiscordChatBridge.sendMessage(config.serverStartedPrompt());
    }

    public static void serverStoppingMethod() {
        DiscordChatBridge.sendMessage(config.serverStoppingPrompt());
    }

    public static void serverStoppedMethod() {
        DiscordChatBridge.sendMessage(config.serverStoppedPrompt());
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

        if (event.getMessage().getContentDisplay().equals("")) return;

        if (event.getMessage().getContentRaw().equals("")) return;

        if (!event.getChannel().getId().equals(config.chatChannelID())) return;

        Message message = event.getMessage();
        content = message.getContentRaw();

        if(content.startsWith("!")){
            DiscordChatBridge.discordCommand(content,event);
            return;
        }

        if (event.getChannel().getId().equals(config.chatChannelID())){
            List<Member> mentionedMembers = event.getMessage().getMentionedMembers();
            for (Member m : mentionedMembers) {
                content = content.replaceAll(String.format("<@!?%s>", m.getId()), m.getNickname() != null ? String.format("@%s", m.getNickname()) :  String.format("@%s", m.getUser().getName()));
            }
            R4Mod.server.getPlayerManager().broadcast(Text.of("[" + Objects.requireNonNull(event.getMember()).getUser().getName() + "] " + content),false);
        }
    }
}
