package net.arniks.modneebu.block.renderer;

import software.bernie.geckolib.renderer.GeoItemRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.arniks.modneebu.block.model.Lillypad3DisplayModel;
import net.arniks.modneebu.block.display.Lillypad3DisplayItem;

public class Lillypad3DisplayItemRenderer extends GeoItemRenderer<Lillypad3DisplayItem> {
	public Lillypad3DisplayItemRenderer() {
		super(new Lillypad3DisplayModel());
	}

	@Override
	public RenderType getRenderType(Lillypad3DisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
