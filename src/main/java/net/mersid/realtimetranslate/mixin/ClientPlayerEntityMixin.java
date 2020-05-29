package net.mersid.realtimetranslate.mixin;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

	// Fired when a chat message is sent from the player
	@Inject(at = @At("HEAD"),
			method = "sendChatMessage(Ljava/lang/String;)V"
	)
	private void onSendChatMessage(String message, CallbackInfo ci)
	{
		RealTimeTranslate.INSTANCE.notEventHandler.onSendChat(message);
	}
}
