package net.arniks.modneebu.init;

import net.arniks.modneebu.block.FlowerlillyBlock;
import net.arniks.modneebu.block.Lillypad4Block;
import net.arniks.modneebu.block.Lillypad5Block;
import net.arniks.modneebu.block.LillypadChildBlock;
import net.arniks.modneebu.block.entity.LillypadChildBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;


public final class LillyPomogator {
    private static final int tikitak = 1200;

    private LillyPomogator() {}

    public static void handleOnPlace(Level level, BlockPos pos, BlockState state) {
        if (!level.isClientSide() && level.isEmptyBlock(pos.above())) {
            level.scheduleTick(pos, state.getBlock(), tikitak);
        }
    }

    public static void handleUpdateShape(LevelAccessor level, BlockPos pos, BlockState state, Direction direction) {
        if (!level.isClientSide() && direction == Direction.UP && level.isEmptyBlock(pos.above())) {
            level.scheduleTick(pos, state.getBlock(), tikitak);
        }
    }

    public static void handleTick(ServerLevel level, BlockPos pos) {
        BlockPos flowery_pos = pos.above();
        if (level.isEmptyBlock(flowery_pos)) {
            level.setBlock(flowery_pos, ModNeEbuModBlocks.FLOWERLILLY.get().defaultBlockState(), 3);
        }
    }


    public static boolean canBonemeal(LevelReader level, BlockPos pos) {
        BlockState aboveState = level.getBlockState(pos.above());
        BlockState defoltState = level.getBlockState(pos);

        if (aboveState.isAir()) {
            return true;
        }
        if (aboveState.getBlock() instanceof FlowerlillyBlock && defoltState.getBlock() instanceof Lillypad4Block) {
            return aboveState.getValue(FlowerlillyBlock.STAGE) < 3;
        }
        if (aboveState.getBlock() instanceof FlowerlillyBlock && defoltState.getBlock() instanceof Lillypad5Block) {
            return aboveState.getValue(FlowerlillyBlock.STAGE) < 3;
        }
        if (aboveState.getBlock() instanceof FlowerlillyBlock) {
            return aboveState.getValue(FlowerlillyBlock.STAGE) < 2;
        }
        return false;
    }
    
    public static void applyBonemeal(ServerLevel level, BlockPos pos) {
        BlockPos abovePos = pos.above();
        BlockState aboveState = level.getBlockState(abovePos);
        if (aboveState.isAir()) {
            level.setBlock(abovePos, ModNeEbuModBlocks.FLOWERLILLY.get().defaultBlockState(), 3);
        }
        else if (aboveState.getBlock() instanceof FlowerlillyBlock) {
            FlowerlillyBlock.grow(level, abovePos, aboveState);
        }
    }


    public static void placeLilypadChildren(Level level, BlockPos masterPos, int radius) {
        if (level.isClientSide()) return;
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                if (dx == 0 && dz == 0) continue;
                BlockPos childPos = masterPos.offset(dx, 0, dz);
                level.setBlock(childPos, ModNeEbuModBlocks.LILLYPAD_CHILD.get().defaultBlockState(), 3);
                if (level.getBlockEntity(childPos) instanceof LillypadChildBlockEntity childBe) {
                    childBe.setMasterPos(masterPos);
                }
            }
        }
    }

    public static void removeLilypadChildren(Level level, BlockPos masterPos, int radius) {
        if (level.isClientSide()) return;
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                if (dx == 0 && dz == 0) continue;
                BlockPos childPos = masterPos.offset(dx, 0, dz);
                BlockState childState = level.getBlockState(childPos);
                if (childState.getBlock() instanceof LillypadChildBlock) {
                    if (level.getBlockEntity(childPos) instanceof LillypadChildBlockEntity childBe
                            && childBe.getMasterPos().equals(masterPos)) {
                        level.removeBlock(childPos, false);
                    }
                }
            }
        }
    }

}
