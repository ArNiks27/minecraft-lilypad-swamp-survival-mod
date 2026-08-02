package net.arniks.modneebu.init;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.arniks.modneebu.ModNeEbuMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHighlightEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BlockOutLineClient {
    static final Set<ResourceLocation> ENABLED = new HashSet<>();
    static {
        ENABLED.add(BuiltInRegistries.BLOCK.getKey(ModNeEbuModBlocks.CATTAIL.get()));
    }
    public static boolean isOutlined(Block block) {
        ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
        return ENABLED.contains(key);
    }
    public static void add(ResourceLocation blockId) {
        ENABLED.add(blockId);
    }
    public static void remove(ResourceLocation blockId) {
        ENABLED.remove(blockId);
    }
    public static Set<ResourceLocation> all() {
        return Collections.unmodifiableSet(ENABLED);
    }
}

@Mod.EventBusSubscriber(modid = ModNeEbuMod.MODID, value = Dist.CLIENT)
class BlockOutlineRender {
    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_LEVEL) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;
        Level level = mc.level;
        var camera = event.getCamera();
        double camX = camera.getPosition().x;
        double camY = camera.getPosition().y;
        double camZ = camera.getPosition().z;
        BlockPos camPos = BlockPos.containing(camX, camY, camZ);
        BlockPos targeted = null;
        if (mc.hitResult instanceof BlockHitResult bhr) targeted = bhr.getBlockPos();
        int radius = 16;
        PoseStack poseStack = event.getPoseStack();
        var bufferSource = mc.renderBuffers().bufferSource();
        VertexConsumer buffer = bufferSource.getBuffer(RenderType.lines());
        RenderSystem.disableDepthTest();
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos pos = camPos.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(pos);
                    if (state.isAir()) continue;
                    if (!BlockOutLineClient.isOutlined(state.getBlock())) continue;
                    if (targeted != null && targeted.equals(pos)) continue;
                    VoxelShape shape = state.getShape(level, pos);
                    if (shape.isEmpty()) shape = Shapes.block();
                    AABB box = shape.bounds().move(pos);
                    LevelRenderer.renderLineBox(poseStack, buffer, box, 0f, 0f, 0f, 1f);
                }
            }
        }
        RenderSystem.enableDepthTest();
        bufferSource.endBatch();
    }
}

@Mod.EventBusSubscriber(modid = ModNeEbuMod.MODID, value = Dist.CLIENT)
class BlockOutlineHighlightSuppressor {
    @SubscribeEvent
    public static void onBlockHighlight(RenderHighlightEvent.Block event) {
        var target = event.getTarget();
        var level = Minecraft.getInstance().level;
        if (level == null || target == null) return;
        BlockState state = level.getBlockState(target.getBlockPos());
        if (BlockOutLineClient.isOutlined(state.getBlock())) {
            event.setCanceled(true);
        }
    }
}

