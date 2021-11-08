package xyz.hackos.r4tech.r4mod.others;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import xyz.hackos.r4tech.r4mod.R4Mod;

import javax.annotation.Nonnull;

import static xyz.hackos.r4tech.r4mod.R4Mod.config;

public class DiscordListener extends ListenerAdapter {
    static String content;
    public DiscordListener(){

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
        R4Mod.sendMessage(Text.of("[" + event.getMember().getUser().getName() + "] " + content), false, false, true);
        }
    }
}
