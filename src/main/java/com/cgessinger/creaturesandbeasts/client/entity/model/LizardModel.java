package com.cgessinger.creaturesandbeasts.client.entity.model;

import com.cgessinger.creaturesandbeasts.CreaturesAndBeasts;
import com.cgessinger.creaturesandbeasts.entities.LizardEntity;
import com.cgessinger.creaturesandbeasts.init.CNBLizardTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class LizardModel extends AnimatedGeoModel<LizardEntity> {
    private static final ResourceLocation LIZARD_MODEL = new ResourceLocation(CreaturesAndBeasts.MOD_ID, "geo/entity/lizard/lizard.geo.json");
    private static final ResourceLocation MUSHROOM_LIZARD_MODEL = new ResourceLocation(CreaturesAndBeasts.MOD_ID, "geo/entity/lizard/mushroom_lizard.geo.json");
    private static final ResourceLocation SAD_LIZARD_MODEL = new ResourceLocation(CreaturesAndBeasts.MOD_ID, "geo/entity/lizard/sad_lizard.geo.json");
    private static final ResourceLocation SAD_MUSHROOM_LIZARD_MODEL = new ResourceLocation(CreaturesAndBeasts.MOD_ID, "geo/entity/lizard/sad_mushroom_lizard.geo.json");

    private static final ResourceLocation LIZARD_ANIMATIONS = new ResourceLocation(CreaturesAndBeasts.MOD_ID, "animations/lizard.json");

    @Override
    public ResourceLocation getModelResource(LizardEntity entity) {
        if (entity.getLizardType().equals(CNBLizardTypes.MUSHROOM)) {
            return entity.getSad() ? SAD_MUSHROOM_LIZARD_MODEL : MUSHROOM_LIZARD_MODEL;
        }
        
        return entity.getSad() ? SAD_LIZARD_MODEL : LIZARD_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(LizardEntity entity) {
        return entity.getSad() ? entity.getLizardType().getSadTextureLocation() : entity.getLizardType().getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(LizardEntity entity) {
        return LIZARD_ANIMATIONS;
    }

    @Override
    public void setLivingAnimations(LizardEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        if (entity.getSad()) {
            head.setRotationX(0.2182F);
            return;
        }

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }
}