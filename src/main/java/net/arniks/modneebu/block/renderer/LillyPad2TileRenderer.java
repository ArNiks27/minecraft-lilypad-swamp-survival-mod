package net.arniks.modneebu.block.renderer;

import software.bernie.geckolib.renderer.GeoBlockRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.arniks.modneebu.block.model.LillyPad2BlockModel;
import net.arniks.modneebu.block.entity.LillyPad2TileEntity;

public class LillyPad2TileRenderer extends GeoBlockRenderer<LillyPad2TileEntity> {
	public LillyPad2TileRenderer() {
		super(new LillyPad2BlockModel());
	}

	@Override
	public RenderType getRenderType(LillyPad2TileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public boolean shouldRenderOffScreen(LillyPad2TileEntity blockEntity) {
		return true;
	}

	@Override
	public int getViewDistance() {
		return 512;
	}
}
