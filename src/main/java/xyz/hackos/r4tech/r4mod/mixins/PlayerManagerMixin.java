package xyz.hackos.r4tech.r4mod.mixins;

import com.google.common.collect.Lists;
import net.minecraft.network.MessageType;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.hackos.r4tech.r4mod.R4Mod;

import java.util.List;
import java.util.UUID;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    private final List<ServerPlayerEntity> players = Lists.newArrayList();

    @Inject(method = "broadcastChatMessage", at = @At(value = "HEAD"))
    public void getMessage(Text message, MessageType type, UUID sender, CallbackInfo ci) {
        R4Mod.setPlayerVariable(players);
        R4Mod.sendMessage(message, true, false, false);
    }
}
