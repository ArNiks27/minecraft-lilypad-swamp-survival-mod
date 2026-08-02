
package net.arniks.modneebu.block;

import net.arniks.modneebu.init.ModNeEbuModBlocks;
import net.arniks.modneebu.init.ModNeEbuModItems;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

import static net.minecraft.world.phys.shapes.Shapes.box;

public class FlowerlillyBlock extends Block implements BonemealableBlock {
	public FlowerlillyBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.GRAVEL).strength(1f, 10f).noCollission().noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0));
	}
	public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);


	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		Block blockBelow = level.getBlockState(pos.below()).getBlock();
		return blockBelow == ModNeEbuModBlocks.LILLYPAD_1.get()
				|| blockBelow == ModNeEbuModBlocks.LILLY_PAD_2.get()
				|| blockBelow == ModNeEbuModBlocks.LILLYPAD_3.get()
				|| blockBelow == ModNeEbuModBlocks.LILLYPAD_4.get()
				|| blockBelow == ModNeEbuModBlocks.LILLYPAD_5.get();
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		if (direction == Direction.DOWN && !state.canSurvive(level, pos)) {
			return Blocks.AIR.defaultBlockState();
		}
		return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
	}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
		BlockPos below = pos.below();
		BlockState belowState = level.getBlockState(below);
		if (!level.isClientSide() && state.getValue(STAGE) < 2) {
			level.scheduleTick(pos, this, 1200);
		}

		if (!level.isClientSide() && state.getValue(STAGE) < 3 && (belowState.getBlock() instanceof  Lillypad4Block || belowState.getBlock() instanceof  Lillypad5Block)) {
			level.scheduleTick(pos, this, 1200);
		}
		super.onPlace(state, level, pos, oldState, isMoving);
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		super.tick(state, level, pos, random);
		int currentStage = state.getValue(STAGE);
		BlockPos below = pos.below();
		BlockState belowState = level.getBlockState(below);

		if (currentStage < 2) {
			int nextStage = currentStage + 1;
			level.setBlock(pos, state.setValue(STAGE, nextStage), 3);

			if (nextStage < 2) {
				level.scheduleTick(pos, this, 1200);
			}
		}

		if (currentStage < 3 && (belowState.getBlock() instanceof  Lillypad4Block || belowState.getBlock() instanceof  Lillypad5Block)) {
			int nextStage = currentStage + 1;
			level.setBlock(pos, state.setValue(STAGE, nextStage), 3);

			if (nextStage < 3) {
				level.scheduleTick(pos, this, 1200);
			}
		}
	}

	public static void grow(ServerLevel level, BlockPos pos, BlockState state) {
		int currentStage = state.getValue(STAGE);
		BlockPos below = pos.below();
		BlockState belowState = level.getBlockState(below);
		if (currentStage < 2) {
			level.setBlock(pos, state.setValue(STAGE, currentStage + 1), 3);
		}
		if (currentStage < 3 &&  belowState.getBlock() instanceof Lillypad4Block) {
			level.setBlock(pos, state.setValue(STAGE, currentStage + 1), 3);
		}
		if (currentStage < 3 &&  belowState.getBlock() instanceof Lillypad5Block) {
			level.setBlock(pos, state.setValue(STAGE, currentStage + 1), 3);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (state.getValue(STAGE) == 2) {
			if (!level.isClientSide()) {
				int count = level.random.nextInt(1) + 1;

				Block.popResource(level, pos, new ItemStack(ModNeEbuModItems.SEEDS.get(), count));
				level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
			}
			return InteractionResult.sidedSuccess(level.isClientSide());
		}

		if (state.getValue(STAGE) == 3) {
			if (!level.isClientSide()) {
				int count = level.random.nextInt(1) + 3;

				Block.popResource(level, pos, new ItemStack(ModNeEbuModItems.SEEDS.get(), count));
				level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

			}
			return InteractionResult.sidedSuccess(level.isClientSide());
		}
		return InteractionResult.PASS;
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

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STAGE);
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
		return Shapes.or(box(6, 0, 6, 10, 2, 10), box(7, 2, 7, 9, 3, 9));
	}

}
