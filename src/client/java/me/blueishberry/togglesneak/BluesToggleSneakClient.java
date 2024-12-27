package me.blueishberry.togglesneak;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class BluesToggleSneakClient implements ClientModInitializer {

	private static KeyBinding toggleSneakKey;

	@Override
	public void onInitializeClient() {
		toggleSneakKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.bluestogglesneak.togglesneak",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_LEFT_CONTROL,
				"category.bluestogglesneak"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if(client.player == null) return;

			if(toggleSneakKey.wasPressed()) {
				client.player.setSneaking(!client.player.isSneaking());
			}
		});
	}
}