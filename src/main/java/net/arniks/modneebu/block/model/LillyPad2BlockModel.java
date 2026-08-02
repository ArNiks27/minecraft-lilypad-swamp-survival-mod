package net.arniks.modneebu.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.arniks.modneebu.block.entity.LillyPad2TileEntity;

public class LillyPad2BlockModel extends GeoModel<LillyPad2TileEntity> {
	@Override
	public ResourceLocation getAnimationResource(LillyPad2TileEntity animatable) {
		return new ResourceLocation("mod_ne_ebu", "animations/lilly_pad_2.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(LillyPad2TileEntity animatable) {
		return new ResourceLocation("mod_ne_ebu", "geo/lilly_pad_2.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(LillyPad2TileEntity animatable) {
		return new ResourceLocation("mod_ne_ebu", "textures/block/lilly_pad_2.png");
	}
}
