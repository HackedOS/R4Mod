package xyz.hackos.r4tech.r4mod.mixins;

import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.hackos.r4tech.r4mod.R4Mod;

import java.util.UUID;

@Mixin(ServerPlayerEntity.class)
class SendMessageInDiscordMixin {

    @Inject(method = "sendMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/MessageType;Ljava/util/UUID;)V", at = @At(value = "HEAD"))
    public void getMessage(Text message, MessageType type, UUID sender, CallbackInfo ci) {
        if(!sender.equals(R4Mod.senderUUID) && !sender.equals(UUID.fromString("00000000-0000-0000-0000-000000000000"))){
        R4Mod.sendMessage(message, true, false, false);}
    }
}
