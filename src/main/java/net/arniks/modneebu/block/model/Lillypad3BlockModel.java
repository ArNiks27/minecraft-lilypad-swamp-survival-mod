package net.arniks.modneebu.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.arniks.modneebu.block.entity.Lillypad3TileEntity;

public class Lillypad3BlockModel extends GeoModel<Lillypad3TileEntity> {
	@Override
	public ResourceLocation getAnimationResource(Lillypad3TileEntity animatable) {
		return new ResourceLocation("mod_ne_ebu", "animations/lilly_pad_3.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(Lillypad3TileEntity animatable) {
		return new ResourceLocation("mod_ne_ebu", "geo/lilly_pad_3.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Lillypad3TileEntity animatable) {
		return new ResourceLocation("mod_ne_ebu", "textures/block/lilly_pad_3.png");
	}
}
