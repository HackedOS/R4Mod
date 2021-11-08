package xyz.hackos.r4tech.r4mod.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import xyz.hackos.r4tech.r4mod.R4Mod;

import static xyz.hackos.r4tech.r4mod.discord.DiscordListener.serverStoppedMethod;

public class GetServerStoppedEvent implements ServerLifecycleEvents.ServerStopped {
    @Override
    public void onServerStopped(MinecraftServer server) {
            serverStoppedMethod();
            R4Mod.stopDiscordBot();
    }
}
