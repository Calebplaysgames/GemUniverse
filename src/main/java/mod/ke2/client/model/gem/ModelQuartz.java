package mod.ke2.client.model.gem;

import mod.ke2.client.model.ModelGem;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelQuartz extends ModelGem {
	private ModelRenderer bipedTopBun;
	private ModelRenderer bipedSideBuns;
	private ModelRenderer bipedBackBun;
	public ModelQuartz() {
		super(0.0F, 0.0F, 64, 64, 4);
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
		this.bipedHead.offsetY = -1.0F;
		this.bipedHeadwear = new ModelRenderer(this, 32, 0);
		this.bipedHeadwear.addBox(-9.0F, -12.0F, -4.0F, 18, 28, 12, 1.1F);
		this.bipedHead.addChild(this.bipedHeadwear);
		this.bipedSideBuns = new ModelRenderer(this, 0, 56);
		this.bipedSideBuns.addBox(-9.0F, -6.0F, -2.0F, 18, 4, 4);
		this.bipedHead.addChild(this.bipedSideBuns);
		this.bipedTopBun = new ModelRenderer(this, 48, 54);
		this.bipedTopBun.addBox(-2.0F, -12.0F, -2.0F, 4, 4, 4);
		this.bipedHead.addChild(this.bipedTopBun);
		this.bipedBackBun = new ModelRenderer(this, 16, 38);
		this.bipedBackBun.addBox(-2.0F, -6.0F, 4.0F, 4, 4, 4);
		this.bipedHead.addChild(this.bipedBackBun);
		this.bipedBody = new ModelRenderer(this, 16, 16);
		this.bipedBody.addBox(-6.0F, -16.0F, -3.0F, 12, 20, 6);
		this.bipedRightArm = new ModelRenderer(this, 48, 16);
		this.bipedRightArm.addBox(-7.0F, 0.0F, -3.0F, 6, 20, 6);
		this.bipedRightArm.offsetY = -1.0F;
		this.bipedLeftArm = new ModelRenderer(this, 48, 34);
		this.bipedLeftArm.addBox(1.0F, 0.0F, -3.0F, 6, 20, 6);
		this.bipedLeftArm.offsetY = -1.0F;
		this.bipedRightLeg = new ModelRenderer(this, 0, 16);
		this.bipedRightLeg.addBox(-6.0F, 0.0F, -3.0F, 6, 20, 6);
		this.bipedRightLeg.offsetY = -0.5F;
		this.bipedLeftLeg = new ModelRenderer(this, 0, 32);
		this.bipedLeftLeg.addBox(0.0F, 0.0F, -3.0F, 6, 20, 6);
		this.bipedLeftLeg.offsetY = -0.5F;
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
	}
}
