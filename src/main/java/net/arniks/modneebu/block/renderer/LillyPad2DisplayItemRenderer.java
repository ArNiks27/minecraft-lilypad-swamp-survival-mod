package net.arniks.modneebu.block.renderer;

import software.bernie.geckolib.renderer.GeoItemRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.arniks.modneebu.block.model.LillyPad2DisplayModel;
import net.arniks.modneebu.block.display.LillyPad2DisplayItem;

public class LillyPad2DisplayItemRenderer extends GeoItemRenderer<LillyPad2DisplayItem> {
	public LillyPad2DisplayItemRenderer() {
		super(new LillyPad2DisplayModel());
	}

	@Override
	public RenderType getRenderType(LillyPad2DisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
