package mod.ke2.block;

import mod.ke2.init.Ke2CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;

public class BlockWarpPadFrame extends Block {
	private final int color;
	
	public BlockWarpPadFrame(int color) {
		super(Material.ROCK);
		this.setCreativeTab(Ke2CreativeTabs.GEM_TECH);
		this.color = color;
		this.setUnlocalizedName(EnumDyeColor.byMetadata(color).toString().toLowerCase() + "_warp_pad_frame");
		this.setResistance(30);
		this.setHardness(2);
	}
	
	public int getColor() {
		return this.color;
	}
}