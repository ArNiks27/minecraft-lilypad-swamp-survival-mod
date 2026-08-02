package net.arniks.modneebu.block;

import net.arniks.modneebu.init.LillyPomogator;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.arniks.modneebu.init.ModNeEbuModBlockEntities;

import javax.annotation.Nullable;

import java.util.List;
import java.util.Collections;

public class Lillypad5Block extends BaseEntityBlock implements EntityBlock, BonemealableBlock {
	public static final IntegerProperty ANIMATION = IntegerProperty.create("animation", 0, (int) 1);
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	private static final VoxelShape SHAPE = box(-48, 0, -48, 64, 20, 64);
	private static final VoxelShape COLLISION_SHAPE = Shapes.or(
			box(-48, 15, -48, 64, 16, 64),
			box(0, 0, 0, 16, 16, 16));
	private static final int MULTIBLOCK_RADIUS = 3;

	public Lillypad5Block() {
		super(BlockBehaviour.Properties.of()

				.sound(SoundType.GRAVEL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return ModNeEbuModBlockEntities.LILLYPAD_5.get().create(blockPos, blockState);
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		LillyPomogator.handleOnPlace(level, pos, state);
		super.onPlace(state, level, pos, oldState, isMoving);
	}

	@Override
	public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
		super.setPlacedBy(level, pos, state, placer, stack);
		LillyPomogator.placeLilypadChildren(level, pos, MULTIBLOCK_RADIUS);
	}

	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock()) && !isMoving) {
			LillyPomogator.removeLilypadChildren(level, pos, MULTIBLOCK_RADIUS);
		}
		super.onRemove(state, level, pos, newState, isMoving);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		LillyPomogator.handleUpdateShape(level, pos, state, direction);
		return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
	}


	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		LillyPomogator.handleTick(level, pos);
		super.tick(state, level, pos, random);
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean p_50900_) {
		return LillyPomogator.canBonemeal(level, pos);
	}

	@Override
	public boolean isBonemealSuccess(Level p_220878_, RandomSource p_220879_, BlockPos p_220880_, BlockState p_220881_) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		LillyPomogator.applyBonemeal(level, pos);
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
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return COLLISION_SHAPE;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ANIMATION, FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}
}
