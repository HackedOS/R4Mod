package xyz.hackos.r4tech.r4mod.mixins;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.hackos.r4tech.r4mod.discord.DiscordChatBridge;

import java.util.Objects;

public class PlayerEventsMixins {
    @Mixin(PlayerManager.class)
    public static class PlayerJoined {
        @Inject(method = "onPlayerConnect", at = @At("RETURN"))
        private void onPlayerJoined(ClientConnection connection, ServerPlayerEntity player, CallbackInfo ci) {
            DiscordChatBridge.sendMessage(":arrow_right: **" + player.getName().getString().replace("_", "\\_") + " joined the game!**");
        }

        @Redirect(method = "onPlayerConnect", at = @At(value = "INVOKE",target = "Ljava/lang/Object;toString()Ljava/lang/String;"))
        private String onPlayerJoinedRemoveIP(Object ip) {
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

    @Mixin({PlayerEntity.class, ServerPlayerEntity.class})
    public static class PlayerDied {
        @Inject(method = "onDeath", at = @At("HEAD"))
        private void onPlayerDied(DamageSource source, CallbackInfo ci) {
            DiscordChatBridge.sendMessage(":skull_crossbones: **" + ((ServerPlayerEntity) (Object) this).getDamageTracker().getDeathMessage().getString().replace("_", "\\_") + "**");

        }
    }

    @Mixin(PlayerAdvancementTracker.class)
    public static class PlayerAdvancement {
        @Shadow
        private ServerPlayerEntity owner;

        @Inject(method = "grantCriterion", at = @At(value = "RETURN"))
        private void onAdvancement(Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
            Text text = Text.translatable("chat.type.advancement." + advancement.getDisplay().getFrame().getId(), this.owner.getDisplayName(), advancement.toHoverableText());

            DiscordChatBridge.sendMessage(":confetti_ball: **" + text.getString().replace("_", "\\_") + "**");
        }
    }
}
