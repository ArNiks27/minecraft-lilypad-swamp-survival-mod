package net.arniks.modneebu.block;

import net.arniks.modneebu.init.ModNeEbuModBlocks;
import net.arniks.modneebu.init.ModNeEbuModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Collections;
import java.util.List;

public class CattailBlock extends Block implements BonemealableBlock  {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 2);

	public CattailBlock() {
		super(BlockBehaviour.Properties.of()
				.sound(SoundType.GRAVEL)
				.strength(0f, 10f)
				.noOcclusion()
				.noCollission()
				.randomTicks()
				.isRedstoneConductor((bs, br, bp) -> false));

		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(STAGE, 0));
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		int currentStage = state.getValue(STAGE);
		if (currentStage == 2) {

			if (!level.isClientSide) {
				level.setBlockAndUpdate(pos, state.setValue(STAGE, 1));

				BlockPos spawnPos = pos.above();
				int count = 3;
				Block.popResource(level, spawnPos, new ItemStack(ModNeEbuModItems.CATTAILSPIKE.get(), count));
			}
			level.playSound(player, pos, net.minecraft.sounds.SoundEvents.SHEEP_SHEAR, net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
		return InteractionResult.PASS;
	}


	@Override
	public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
		int currentStage = state.getValue(STAGE);
		if (currentStage == 1) {
			return Collections.singletonList(new ItemStack(Items.STICK, 3));
		}
		if (currentStage == 2) {
			return Collections.singletonList(new ItemStack(ModNeEbuModItems.CATTAILSPIKE.get(), 1));
		}

		return Collections.emptyList();
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
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockState blockBelow = level.getBlockState(pos.below());
		return blockBelow.is(Blocks.DIRT) || blockBelow.is(Blocks.GRASS_BLOCK);
	}

	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return state.getValue(STAGE) < 2;
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		int currentStage = state.getValue(STAGE);
		if (currentStage < 2 && random.nextInt(3) == 0) {
			level.setBlock(pos, state.setValue(STAGE, currentStage + 1), 2);
		}
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		if (!state.canSurvive(level, pos)) {
			return Blocks.AIR.defaultBlockState();
		}
		return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
		builder.add(STAGE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(STAGE, 0);
	}

	public static void grow(ServerLevel level, BlockPos pos, BlockState state) {
		int currentStage = state.getValue(STAGE);
		if (currentStage < 2) {
			level.setBlock(pos, state.setValue(STAGE, currentStage + 1), 3);
		}
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
		return state.getValue(STAGE) < 2;
	}

	@Override
	public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
		grow(level, pos, state);
	}
}