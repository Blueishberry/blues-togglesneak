package me.blueishberry.togglesneak;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import org.lwjgl.glfw.GLFW;

public class BluesToggleSneakClient implements ClientModInitializer {

	public static KeyBinding toggleSneakKey;

	@Override
	public void onInitializeClient() {
		// Register the toggle sneak keybinding
		toggleSneakKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.bluestogglesneak.togglesneak",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UNKNOWN,
				"category.bluestogglesneak"
		));
	}
}
