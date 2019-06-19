package mod.ke2.block;

import mod.ke2.init.Ke2CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockOre extends Block {

    public BlockOre(String ore) {
        super(Material.GROUND);
        this.setResistance(3);
        this.setHardness(2);
        this.setUnlocalizedName(ore + "_ore");
        this.setCreativeTab(Ke2CreativeTabs.GEM_BLOCKS);
    }
}