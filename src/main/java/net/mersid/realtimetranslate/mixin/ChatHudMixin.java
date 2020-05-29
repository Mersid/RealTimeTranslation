package net.mersid.realtimetranslate.mixin;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class ChatHudMixin {

	// Fired when a chat message is received
	@Inject(at = @At("HEAD"),
			method = "addMessage(Lnet/minecraft/text/Text;I)V"
	)
	private void onAddMessage(Text chatText, int chatLineId, CallbackInfo ci)
	{
		RealTimeTranslate.INSTANCE.notEventHandler.onReceiveChat(chatText, chatLineId);
	}
}
