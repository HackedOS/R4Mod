package xyz.hackos.r4tech.r4mod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import xyz.hackos.r4tech.r4mod.events.*;
import xyz.hackos.r4tech.r4mod.others.Config;
import xyz.hackos.r4tech.r4mod.discord.DiscordListener;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Environment(EnvType.SERVER)
public class R4Mod implements DedicatedServerModInitializer {

    public static Config config;
    public static boolean jdaReady = false;
    public static MinecraftDedicatedServer server;
    public static JDA api;
    static Path configPath = Paths.get(FabricLoader.getInstance().getConfigDir() + "/r4mod.json");
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //Sets the server variable to use for commands
    public static void setServerVariable(MinecraftDedicatedServer server) {
        R4Mod.server = server;
    }

    public static void stopDiscordBot() {
        if (api == null) return;
        api.shutdown();
    }

    public void updateConfigs() {
        try {
            String contents = Files.readString(configPath);
            config = gson.fromJson(contents, Config.class);
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
            config = new Config("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456",
                    "123456789012345678",
                    "Server starting",
                    "Server started",
                    "Server stopping",
                    "Server stopped");
        }
    }
    @Override
    public void onInitializeServer() {
        try {
            if (!Files.exists(configPath)) {
                if (!Files.exists(configPath.getParent())) {
                    Files.createDirectory(configPath.getParent());
                }
                String contents =
                        """
                                {
                                    "botToken": "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456",
                                    "chatChannelID": "123456789012345678",
                                    "serverStartingPrompt": "**Server starting**",
                                    "serverStartedPrompt": "**Server started**",
                                    "serverStoppingPrompt": "**Server stopping**",
                                    "serverStoppedPrompt": "**Server stopped**"
                                }
                                        """;
                Files.writeString(configPath, contents);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateConfigs();
        try {
            api = JDABuilder.createDefault(config.botToken()).addEventListeners(new DiscordListener()).build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        //Register prompt events
        ServerLifecycleEvents.SERVER_STARTING.register(new GetServerStartingEvent());
        ServerLifecycleEvents.SERVER_STARTED.register(new GetServerStartedEvent());
        ServerLifecycleEvents.SERVER_STOPPING.register(new GetServerStoppingEvent());
        ServerLifecycleEvents.SERVER_STOPPED.register(new GetServerStoppedEvent());

    }
}
