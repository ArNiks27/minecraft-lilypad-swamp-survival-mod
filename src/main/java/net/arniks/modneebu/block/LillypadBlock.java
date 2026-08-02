
package net.arniks.modneebu.block;

import net.arniks.modneebu.init.ModNeEbuModBlocks;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.phys.BlockHitResult;
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

public class LillypadBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public LillypadBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.GRAVEL).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
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
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		ItemStack itemStack = player.getItemInHand(hand);
		if (itemStack.is(Items.KELP)) {
			if (!level.isClientSide()) {
				level.setBlock(pos, ModNeEbuModBlocks.LILLYPADLAMINAR.get().defaultBlockState(), 3);
				if (level instanceof ServerLevel serverLevel) {
					BlockState state_partic = Blocks.LILY_PAD.defaultBlockState();
					BlockParticleOption particleData = new BlockParticleOption(ParticleTypes.BLOCK, state_partic);
					serverLevel.sendParticles(
							particleData,
							pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5 ,
							10,
							0.3, 1,  0.3,
							0.1
					);
				}
				if (!player.isCreative()) {
					itemStack.shrink(1);
				}
			}
			return InteractionResult.sidedSuccess(level.isClientSide());
		}
		return super.use(state, level, pos, player, hand, hitResult);
	}


	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			default -> box(0, 15, 0, 16, 16, 16);
			case NORTH -> box(0, 15, 0, 16, 16, 16);
			case EAST -> box(0, 15, 0, 16, 16, 16);
			case WEST -> box(0, 15, 0, 16, 16, 16);
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
}
