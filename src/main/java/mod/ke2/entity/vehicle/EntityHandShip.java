package mod.ke2.entity.vehicle;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityHandShip extends EntityLiving {
	public EntityHandShip(World world) {
		super(world);
		this.setSize(32.0F, 10.0F);
	}
}
