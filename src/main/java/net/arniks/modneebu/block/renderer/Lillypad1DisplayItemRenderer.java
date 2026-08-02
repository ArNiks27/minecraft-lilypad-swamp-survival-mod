package net.arniks.modneebu.block.renderer;

import software.bernie.geckolib.renderer.GeoItemRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.arniks.modneebu.block.model.Lillypad1DisplayModel;
import net.arniks.modneebu.block.display.Lillypad1DisplayItem;

public class Lillypad1DisplayItemRenderer extends GeoItemRenderer<Lillypad1DisplayItem> {
	public Lillypad1DisplayItemRenderer() {
		super(new Lillypad1DisplayModel());
	}

	@Override
	public RenderType getRenderType(Lillypad1DisplayItem animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
