package net.arniks.modneebu.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.arniks.modneebu.block.entity.Lillypad1TileEntity;

public class Lillypad1BlockModel extends GeoModel<Lillypad1TileEntity> {
	@Override
	public ResourceLocation getAnimationResource(Lillypad1TileEntity animatable) {
		return new ResourceLocation("mod_ne_ebu", "animations/lilly_pad_1.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(Lillypad1TileEntity animatable) {
		return new ResourceLocation("mod_ne_ebu", "geo/lilly_pad_1.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Lillypad1TileEntity animatable) {
		return new ResourceLocation("mod_ne_ebu", "textures/block/lilly_pad_1.png");
	}
}
