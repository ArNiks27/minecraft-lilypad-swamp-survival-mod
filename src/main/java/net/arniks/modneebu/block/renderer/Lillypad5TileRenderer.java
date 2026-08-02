package net.arniks.modneebu.block.renderer;

import software.bernie.geckolib.renderer.GeoBlockRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.arniks.modneebu.block.model.Lillypad5BlockModel;
import net.arniks.modneebu.block.entity.Lillypad5TileEntity;

public class Lillypad5TileRenderer extends GeoBlockRenderer<Lillypad5TileEntity> {
	public Lillypad5TileRenderer() {
		super(new Lillypad5BlockModel());
	}

	@Override
	public RenderType getRenderType(Lillypad5TileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public boolean shouldRenderOffScreen(Lillypad5TileEntity blockEntity) {
		return true;
	}

	@Override
	public int getViewDistance() {
		return 512;
	}
}
