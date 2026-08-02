package net.arniks.modneebu.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.arniks.modneebu.block.display.Lillypad5DisplayItem;

public class Lillypad5DisplayModel extends GeoModel<Lillypad5DisplayItem> {
	@Override
	public ResourceLocation getAnimationResource(Lillypad5DisplayItem animatable) {
		return new ResourceLocation("mod_ne_ebu", "animations/lilly_pad_5.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(Lillypad5DisplayItem animatable) {
		return new ResourceLocation("mod_ne_ebu", "geo/lilly_pad_5.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(Lillypad5DisplayItem entity) {
		return new ResourceLocation("mod_ne_ebu", "textures/block/lilly_pad_5.png");
	}
}
