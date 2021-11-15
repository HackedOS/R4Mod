package xyz.hackos.r4tech.r4mod.mixins;

import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.hackos.r4tech.r4mod.discord.DiscordChatBridge;

public class PlayerEventsMixins {
    @Mixin(PlayerManager.class)
    public static class PlayerJoined {
        @Inject(method = "onPlayerConnect", at = @At("RETURN"))
        private void onPlayerJoined(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
            DiscordChatBridge.sendMessage(":arrow_right: **" + player.getName().getString().replace("_", "\\_") + " joined the game!**");
        }

        @ModifyArg(method = "onPlayerConnect", at = @At(value = "INVOKE",target = "Lorg/apache/logging/log4j/Logger;info(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V"), index = 2)
        private Object onPlayerJoinedRemoveIP(Object ip) {
            return "IP MASKED";
        }
    }


    @Mixin(PlayerManager.class)
    public static class PlayerLeft {
        @Inject(method = "remove", at = @At("RETURN"))
        private void onPlayerLeft(ServerPlayerEntity player, CallbackInfo ci) {
            DiscordChatBridge.sendMessage(":arrow_left: **" + player.getName().getString().replace("_", "\\_") + " left the game!**");
        }
    }
}
