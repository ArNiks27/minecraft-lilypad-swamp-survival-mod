package net.arniks.modneebu.block.renderer;

import software.bernie.geckolib.renderer.GeoItemRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.arniks.modneebu.block.model.Lillypad4DisplayModel;
import net.arniks.modneebu.block.display.Lillypad4DisplayItem;

public class Lillypad4DisplayItemRenderer extends GeoItemRenderer<Lillypad4DisplayItem> {
	public Lillypad4DisplayItemRenderer() {
		super(new Lillypad4DisplayModel());
	}

	@Override
	public RenderType getRenderType(Lillypad4DisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
