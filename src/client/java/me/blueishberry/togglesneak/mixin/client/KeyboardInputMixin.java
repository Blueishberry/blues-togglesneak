package me.blueishberry.togglesneak.mixin.client;

import me.blueishberry.togglesneak.BluesToggleSneakClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public abstract class KeyboardInputMixin {

    @Unique
    private boolean isToggledSneaking = false; // Tracks the toggle state
    @Unique
    private boolean wasToggleKeyPressed = false; // Tracks the previous state of the toggle key

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(boolean slowDown, float slowDownFactor, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        // Check the state of the toggle sneak key
        boolean isToggleKeyPressed = BluesToggleSneakClient.toggleSneakKey.isPressed();

        // If the toggle key is pressed and wasn't pressed before, toggle the sneaking state
        if (isToggleKeyPressed && !wasToggleKeyPressed) {
            isToggledSneaking = !isToggledSneaking;

            // Synchronize with the server
            if (isToggledSneaking) {
                client.player.setSneaking(true);
            } else {
                client.player.setSneaking(false);
            }
        }

        // Update sneaking state if toggle is active
        if (isToggledSneaking) {
            client.player.input.sneaking = true;
        } else {
            client.player.input.sneaking = client.options.sneakKey.isPressed();
        }

        // Update the previous toggle key state
        wasToggleKeyPressed = isToggleKeyPressed;
    }
}
