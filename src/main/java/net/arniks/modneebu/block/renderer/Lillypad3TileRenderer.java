package net.arniks.modneebu.block.renderer;

import software.bernie.geckolib.renderer.GeoBlockRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.arniks.modneebu.block.model.Lillypad3BlockModel;
import net.arniks.modneebu.block.entity.Lillypad3TileEntity;

public class Lillypad3TileRenderer extends GeoBlockRenderer<Lillypad3TileEntity> {
	public Lillypad3TileRenderer() {
		super(new Lillypad3BlockModel());
	}

	@Override
	public RenderType getRenderType(Lillypad3TileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public boolean shouldRenderOffScreen(Lillypad3TileEntity blockEntity) {
		return true;
	}

	@Override
	public int getViewDistance() {
		return 512;
	}
}
