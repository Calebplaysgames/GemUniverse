package mod.ke2.client.model.gem;

import mod.ke2.client.model.ModelGem;
import mod.ke2.entity.gem.EntityPeridot;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPeridot extends ModelGem {
	public ModelPeridot() {
		super(0.0F, 0.0F, 64, 64, 4);
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
		this.bipedHead.offsetY = 0.25F;
		this.bipedHeadwear = new ModelRenderer(this, 0, 40);
		this.bipedHeadwear.addBox(-11.0F, -12.0F, -4.0F, 14, 14, 10);
		this.bipedHead.addChild(this.bipedHeadwear);
		this.bipedBody = new ModelRenderer(this, 8, 16);
	    this.bipedBody.addBox(-3.0F, 4.0F, -2.0F, 6, 10, 4);
	    this.bipedRightArm = new ModelRenderer(this, 28, 16);
	    this.bipedRightArm.addBox(0.0F, 0.0F, -1.0F, 2, 10, 2);
	    this.bipedRightArm.offsetY = 0.25F;
	    this.bipedLeftArm = new ModelRenderer(this, 28, 28);
	    this.bipedLeftArm.addBox(-2.0F, 0.0F, -1.0F, 2, 10, 2);
	    this.bipedLeftArm.offsetY = 0.25F;
	    this.bipedRightLeg = new ModelRenderer(this, 0, 16);
	    this.bipedRightLeg.addBox(-3.0F, 2.0F, -1.0F, 2, 10, 2);
	    this.bipedLeftLeg = new ModelRenderer(this, 0, 28);
	    this.bipedLeftLeg.addBox(1.0F, 2.0F, -1.0F, 2, 10, 2);
	}
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		this.bipedHead.render(scale);
		this.bipedBody.render(scale);
		this.bipedRightArm.render(scale);
		this.bipedLeftArm.render(scale);
		this.bipedRightLeg.render(scale);
		this.bipedLeftLeg.render(scale);
	}
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
		if (entity instanceof EntityPeridot) {
			EntityPeridot gem = (EntityPeridot)(entity);
			this.bipedHead.rotateAngleY = 0.5F;
			if (gem.hasSquareHair()) {
				this.bipedHeadwear.rotateAngleZ += 0.0000000F;
				this.bipedHeadwear.offsetX =  0.25F;
				this.bipedHeadwear.offsetY = -0.12F;
			}
			else {
				this.bipedHeadwear.rotateAngleZ += 0.7853982F;
				this.bipedHeadwear.rotateAngleZ += 0;
				this.bipedHeadwear.offsetX = -0.039F;
				this.bipedHeadwear.offsetY = -0.06F;
			}
		}
		this.bipedHeadwear.offsetZ = 0.001F;
	}
}