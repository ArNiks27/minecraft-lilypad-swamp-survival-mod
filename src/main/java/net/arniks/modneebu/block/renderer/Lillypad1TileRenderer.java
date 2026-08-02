package net.arniks.modneebu.block.renderer;

import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.arniks.modneebu.block.model.Lillypad1BlockModel;
import net.arniks.modneebu.block.entity.Lillypad1TileEntity;

import static net.minecraftforge.common.extensions.IForgeBlockEntity.INFINITE_EXTENT_AABB;

public class Lillypad1TileRenderer extends GeoBlockRenderer<Lillypad1TileEntity> {
	public Lillypad1TileRenderer() {
		super(new Lillypad1BlockModel());
	}

	@Override
	public RenderType getRenderType(Lillypad1TileEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
	@Override
	public boolean shouldRenderOffScreen(Lillypad1TileEntity blockEntity) {
		return true;
	}

	@Override
	public int getViewDistance() {
		return 512;
	}
}
