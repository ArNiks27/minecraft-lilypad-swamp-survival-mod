package net.arniks.modneebu.item;

import net.arniks.modneebu.init.ModNeEbuModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class CattailspikeItem extends Item {
	public CattailspikeItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}


	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		Level level = context.getLevel();
		BlockPos clickedPos = context.getClickedPos();
		ItemStack itemInHand = context.getItemInHand();

		if (player == null) return InteractionResult.PASS;

		BlockState clickedState = level.getBlockState(clickedPos);
		BlockPos spawnPos = clickedPos.above();
		if ((clickedState.is(Blocks.DIRT) || clickedState.is(Blocks.GRASS_BLOCK)) && level.getBlockState(spawnPos).isAir()) {

			if (!level.isClientSide) {
				BlockState cropState = ModNeEbuModBlocks.CATTAIL.get().defaultBlockState();
				level.setBlockAndUpdate(spawnPos, cropState);
				if (!player.isCreative()) {
					itemInHand.shrink(1);
				}
			}
			return InteractionResult.sidedSuccess(level.isClientSide);
		}

		return InteractionResult.PASS;
	}
}