package net.mersid.realtimetranslate.mixin;

import net.mersid.realtimetranslate.RealTimeTranslate;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

	// Thanks, Alex!
	// Keycodes map to values under GLFW. See the keybind handler in RealTimeTranslate.
	@Inject(at = @At("HEAD"), method = "onKey(JIIII)V")
	private void onOnKey(long window, int keyCode, int scanCode, int action, int modifiers, CallbackInfo ci)
	{
		RealTimeTranslate.INSTANCE.notEventHandler.onKeyPress(window, keyCode, scanCode, action, modifiers);
	}
}
