package xyz.hackos.r4tech.r4mod.mixins;

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.hackos.r4tech.r4mod.discord.DiscordChatBridge;

@Mixin(ServerPlayNetworkHandler.class)
public class ChatEventMixin {
    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onGameMessage", at = @At("RETURN"))
    private void onChatMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        if (!packet.getChatMessage().startsWith("/")) DiscordChatBridge.sendMessage("`<" + player.getName().getString() + ">` " + packet.getChatMessage());
    }
}
