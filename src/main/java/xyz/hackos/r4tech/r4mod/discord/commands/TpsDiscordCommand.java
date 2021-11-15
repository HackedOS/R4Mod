package xyz.hackos.r4tech.r4mod.discord.commands;

import carpet.helpers.TickSpeed;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.minecraft.util.math.MathHelper;
import xyz.hackos.r4tech.r4mod.R4Mod;

import java.awt.*;
import java.util.Objects;

public class TpsDiscordCommand {
    public static void tpsCommand(MessageReceivedEvent event){
        double MSPT = MathHelper.average(R4Mod.server.lastTickLengths) * 1.0E-6D;
        double TPS = 1000.0D / Math.max((TickSpeed.time_warp_start_time != 0)?0.0:TickSpeed.mspt, MSPT);
        try {
            EmbedBuilder emb = new EmbedBuilder();
            emb.setColor(MSPT >= 50 ? Color.RED : Color.GREEN);
            emb.setTitle("Performance");
            if (MSPT > 50) {
                emb.setDescription("**SERVER IS LAGGING** \n\n");
            }
            emb.addField("TPS", String.valueOf(Math.round(TPS * 10.0) / 10.0), true);
            emb.addField("MSPT", String.valueOf(Math.round(MSPT * 10.0) / 10.0), true);
            event.getChannel().sendMessageEmbeds(Objects.requireNonNull(emb).build()).queue();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
