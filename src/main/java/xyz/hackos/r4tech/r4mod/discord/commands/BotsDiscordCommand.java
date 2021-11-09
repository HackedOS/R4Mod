package xyz.hackos.r4tech.r4mod.discord.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.minecraft.server.network.ServerPlayerEntity;
import xyz.hackos.r4tech.r4mod.R4Mod;

import java.awt.*;
import java.util.Objects;

public class BotsDiscordCommand {
    public static void BotsCommand(MessageReceivedEvent event){
        StringBuilder msg = new StringBuilder();
        int n = 0;
        for (ServerPlayerEntity player : R4Mod.server.getPlayerManager().getPlayerList()) {
            if (player.getClass() != ServerPlayerEntity.class) {
                msg.append(player.getName().getString().replace("_", "\\_")).append("\n");
                n = n + 1;
            }
        }
        EmbedBuilder emb = new EmbedBuilder();
        try {
            emb.setColor(n != 0 ? Color.GREEN : Color.RED);
            emb.setTitle("Online Bots");
            if (n > 1) {
                emb.setDescription("**" + n + " bots connected** \n\n" + msg);
            } else {
                emb.setDescription(n == 0 ? "**No bots online :(**" : "**" + n + " bot connected** \n\n" + msg);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        event.getChannel().sendMessageEmbeds(Objects.requireNonNull(emb).build()).queue();
    }
}
