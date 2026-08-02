package net.arniks.modneebu.block.renderer;

import software.bernie.geckolib.renderer.GeoItemRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.arniks.modneebu.block.model.Lillypad5DisplayModel;
import net.arniks.modneebu.block.display.Lillypad5DisplayItem;

public class Lillypad5DisplayItemRenderer extends GeoItemRenderer<Lillypad5DisplayItem> {
	public Lillypad5DisplayItemRenderer() {
		super(new Lillypad5DisplayModel());
	}

	@Override
	public RenderType getRenderType(Lillypad5DisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
