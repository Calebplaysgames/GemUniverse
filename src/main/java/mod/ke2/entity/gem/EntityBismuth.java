package mod.ke2.entity.gem;

import mod.ke2.api.EntityGem;
import mod.ke2.api.variants.types.VariantColor;
import mod.ke2.api.variants.types.VariantPath;
import mod.ke2.init.Ke2Variants;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityBismuth extends EntityGem {
	static {
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/bismuth/hair_default"), VariantPath.class), EntityBismuth.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/bismuth/outfit_default"), VariantPath.class), EntityBismuth.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/bismuth/skin_default"), VariantPath.class), EntityBismuth.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/bismuth/color_gemstone"), VariantColor.class), EntityBismuth.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/bismuth/color_hair"), VariantColor.class), EntityBismuth.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/bismuth/color_outfit"), VariantColor.class), EntityBismuth.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/bismuth/color_skin"), VariantColor.class), EntityBismuth.class);
		Ke2Variants.addVariantToGem(Ke2Variants.registerVariant(new ResourceLocation("ke2:variants/bismuth/color_visor"), VariantColor.class), EntityBismuth.class);
	}
	
	public EntityBismuth(World world) {
		super(world);
	}
	
	@Override
	public void onInventoryChanged(IInventory inventory) {
		
	}
	
	@Override
	public int generateGemstoneCut() {
		return 0;
	}
}
