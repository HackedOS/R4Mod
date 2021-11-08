package xyz.hackos.r4tech.r4mod.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

import static xyz.hackos.r4tech.r4mod.others.DiscordListener.serverStartingMethod;


public class GetServerStartingEvent implements ServerLifecycleEvents.ServerStarting {
    @Override
    public void onServerStarting(MinecraftServer server) {
        serverStartingMethod();
    }
}