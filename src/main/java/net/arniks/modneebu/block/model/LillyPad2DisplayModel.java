package net.arniks.modneebu.block.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.arniks.modneebu.block.display.LillyPad2DisplayItem;

public class LillyPad2DisplayModel extends GeoModel<LillyPad2DisplayItem> {
	@Override
	public ResourceLocation getAnimationResource(LillyPad2DisplayItem animatable) {
		return new ResourceLocation("mod_ne_ebu", "animations/lilly_pad_2.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(LillyPad2DisplayItem animatable) {
		return new ResourceLocation("mod_ne_ebu", "geo/lilly_pad_2.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(LillyPad2DisplayItem entity) {
		return new ResourceLocation("mod_ne_ebu", "textures/block/lilly_pad_2.png");
	}
}
