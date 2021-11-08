package xyz.hackos.r4tech.r4mod.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

import static xyz.hackos.r4tech.r4mod.discord.DiscordListener.serverStoppingMethod;

public class GetServerStoppingEvent implements ServerLifecycleEvents.ServerStopping {
    @Override
    public void onServerStopping(MinecraftServer server) {
        serverStoppingMethod();
    }
}
