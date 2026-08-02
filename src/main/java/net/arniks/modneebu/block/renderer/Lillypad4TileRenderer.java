package net.arniks.modneebu.block.renderer;

import software.bernie.geckolib.renderer.GeoBlockRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.arniks.modneebu.block.model.Lillypad4BlockModel;
import net.arniks.modneebu.block.entity.Lillypad4TileEntity;

public class Lillypad4TileRenderer extends GeoBlockRenderer<Lillypad4TileEntity> {
	public Lillypad4TileRenderer() {
		super(new Lillypad4BlockModel());
	}

	@Override
	public RenderType getRenderType(Lillypad4TileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public boolean shouldRenderOffScreen(Lillypad4TileEntity blockEntity) {
		return true;
	}

	@Override
	public int getViewDistance() {
		return 512;
	}
}
