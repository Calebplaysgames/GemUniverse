package mod.ke2.client.model.gem;

import mod.ke2.client.model.ModelGem;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBismuth extends ModelGem {
	public ModelBismuth() {
		super(0.0F, 0.0F, 64, 64);
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.addBox(-4.0F, -12.0F, -4.0F, 8, 8, 8, 0.0F);
		this.bipedHead.offsetY = -0.25F;
		this.bipedHeadwear = new ModelRenderer(this, 32, 0);
		this.bipedHeadwear.addBox(-4.0F, -12.0F, -4.0F, 8, 8, 8, 0.5F);
		this.bipedHead.addChild(this.bipedHeadwear);
		this.bipedBody = new ModelRenderer(this, 0, 16);
		this.bipedBody.addBox(-6.0F, -8.0F, -3.0F, 12, 16, 6, 0.0F);
		this.bipedSkirt = new ModelRenderer(this, 32, 38);
		this.bipedSkirt.addBox(-6.0F, 4.0F, -3.0F, 12, 12, 6, 0.5F);
		this.bipedBody.addChild(this.bipedSkirt);
		this.bipedRightArm = new ModelRenderer(this, 16, 38);
		this.bipedRightArm.addBox(-6.0F, -8.0F, -2.5F, 5, 16, 5, 0.0F);
		this.bipedLeftArm = new ModelRenderer(this, 0, 38);
		this.bipedLeftArm.addBox(1.0F, -8.0F, -2.5F, 5, 16, 5, 0.0F);
		this.bipedRightLeg = new ModelRenderer(this, 48, 38);
		this.bipedRightLeg.addBox(-6.0F, -4.0F, -2.0F, 4, 16, 4);
		this.bipedLeftLeg = new ModelRenderer(this, 32, 38);
		this.bipedLeftLeg.addBox(2.0F, -4.0F, -2.0F, 4, 16, 4);
	}
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
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
		this.bipedHeadwear.rotateAngleX = 0.0F;
		this.bipedHeadwear.rotateAngleY = 0.0F;
	}
}
