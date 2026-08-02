
package net.arniks.modneebu.block;

import net.arniks.modneebu.init.ModNeEbuModBlocks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.Collections;
import java.util.List;

public class CattailnetushtuchekBlock extends Block implements BonemealableBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public CattailnetushtuchekBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.GRAVEL).strength(0f, 10f).noCollission().noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockState blockUnder = level.getBlockState(pos.below());

		if (blockUnder.is(ModNeEbuModBlocks.LILLYPADFAKE.get()) || (blockUnder.is(ModNeEbuModBlocks.LILLYPAD.get())))    {
			return false;
		}
		return super.canSurvive(state, level, pos);
	}
	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
		if (direction == Direction.DOWN && !this.canSurvive(state, level, currentPos)) {
			if (level instanceof Level serverLevel && !serverLevel.isClientSide) {
				double x = currentPos.getX() + 0.5;
				double y = currentPos.getY() + 0.2;
				double z = currentPos.getZ() + 0.5;
				ItemStack vanillaStack = new ItemStack(Items.STICK, 3);
				ItemEntity vanillaItem = new ItemEntity(serverLevel, x, y, z, vanillaStack);
				vanillaItem.setDeltaMovement(0.0, 0.2, 0.0);
				serverLevel.addFreshEntity(vanillaItem);
			}
			return Blocks.AIR.defaultBlockState();
		}
		return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			default -> box(0, 0, 0, 16, 32, 16);
			case NORTH -> box(0, 0, 0, 16, 32, 16);
			case EAST -> box(0, 0, 0, 16, 32, 16);
			case WEST -> box(0, 0, 0, 16, 32, 16);
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}
	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		if (!level.isClientSide) {
			level.setBlock(pos, ModNeEbuModBlocks.CATTAILSHTUKI.get().defaultBlockState(), 3);
		}
	}
}

