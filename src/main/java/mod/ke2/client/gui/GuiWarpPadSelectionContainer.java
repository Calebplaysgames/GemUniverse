package mod.ke2.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class GuiWarpPadSelectionContainer extends Container {
	public GuiWarpPadSelectionContainer() {
		
	}
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}
