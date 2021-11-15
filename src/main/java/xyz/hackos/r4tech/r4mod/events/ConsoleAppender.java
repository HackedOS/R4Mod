package xyz.hackos.r4tech.r4mod.events;

import net.minecraft.text.Text;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import xyz.hackos.r4tech.r4mod.discord.DiscordChatBridge;

public class ConsoleAppender extends AbstractAppender {

    public ConsoleAppender() {
        super("CustomConsoleAppender", null, null, false, Property.EMPTY_ARRAY);
    }

    @Override
    public void append(LogEvent event) {
        //Checking if it is at the right level
        if (event.getLevel() == Level.ERROR || event.getLevel() == Level.INFO || event.getLevel() == Level.WARN) {
            DiscordChatBridge.sendConsoleMessage("[" + event.getLevel() + "] " + event.getMessage().getFormattedMessage());
        }
    }
}
