package net.arniks.modneebu.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.item.ItemStack;

import net.arniks.modneebu.block.entity.LillypadChildBlockEntity;
import net.arniks.modneebu.init.ModNeEbuModBlockEntities;

import javax.annotation.Nullable;

import java.util.Collections;
import java.util.List;

public class LillypadChildBlock extends BaseEntityBlock implements EntityBlock {
	private static final VoxelShape SHAPE = box(0, 15, 0, 16, 16, 16);

	public LillypadChildBlock() {
		super(BlockBehaviour.Properties.of()
				.noOcclusion()
				.strength(-1.0F, 3600000.0F) // как у barrier: нельзя сломать вручную, только кодом
				.isRedstoneConductor((bs, br, bp) -> false));
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.INVISIBLE;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return ModNeEbuModBlockEntities.LILLYPAD_CHILD.get().create(pos, state);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
		return 0;
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
		return Collections.emptyList();
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock()) && !level.isClientSide() && !isMoving) {
			if (level.getBlockEntity(pos) instanceof LillypadChildBlockEntity childBe) {
				BlockPos masterPos = childBe.getMasterPos();
				BlockState masterState = level.getBlockState(masterPos);
				boolean masterStillHere = masterState.getBlock() instanceof Lillypad4Block
						|| masterState.getBlock() instanceof Lillypad5Block;
				if (masterStillHere) {
					level.removeBlock(masterPos, false);
				}
			}
		}
		super.onRemove(state, level, pos, newState, isMoving);
	}
}
