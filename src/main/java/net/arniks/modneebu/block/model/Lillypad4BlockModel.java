package net.arniks.modneebu.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.arniks.modneebu.block.entity.Lillypad4TileEntity;

public class Lillypad4BlockModel extends GeoModel<Lillypad4TileEntity> {
	@Override
	public ResourceLocation getAnimationResource(Lillypad4TileEntity animatable) {
		return new ResourceLocation("mod_ne_ebu", "animations/lilly_pad_4.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(Lillypad4TileEntity animatable) {
		return new ResourceLocation("mod_ne_ebu", "geo/lilly_pad_4.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Lillypad4TileEntity animatable) {
		return new ResourceLocation("mod_ne_ebu", "textures/block/lilly_pad_4.png");
	}
}
