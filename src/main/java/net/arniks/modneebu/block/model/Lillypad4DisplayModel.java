package net.arniks.modneebu.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.arniks.modneebu.block.display.Lillypad4DisplayItem;

public class Lillypad4DisplayModel extends GeoModel<Lillypad4DisplayItem> {
	@Override
	public ResourceLocation getAnimationResource(Lillypad4DisplayItem animatable) {
		return new ResourceLocation("mod_ne_ebu", "animations/lilly_pad_4.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(Lillypad4DisplayItem animatable) {
		return new ResourceLocation("mod_ne_ebu", "geo/lilly_pad_4.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Lillypad4DisplayItem entity) {
		return new ResourceLocation("mod_ne_ebu", "textures/block/lilly_pad_4.png");
	}
}
