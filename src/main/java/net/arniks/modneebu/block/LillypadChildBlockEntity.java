package net.arniks.modneebu.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import net.arniks.modneebu.init.ModNeEbuModBlockEntities;


public class LillypadChildBlockEntity extends BlockEntity {

	private BlockPos masterPos = BlockPos.ZERO;

	public LillypadChildBlockEntity(BlockPos pos, BlockState state) {
		super(ModNeEbuModBlockEntities.LILLYPAD_CHILD.get(), pos, state);
	}

	public BlockPos getMasterPos() {
		return masterPos;
	}

	public void setMasterPos(BlockPos masterPos) {
		this.masterPos = masterPos;
		this.setChanged();
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		if (tag.contains("MasterX")) {
			masterPos = new BlockPos(tag.getInt("MasterX"), tag.getInt("MasterY"), tag.getInt("MasterZ"));
		}
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		tag.putInt("MasterX", masterPos.getX());
		tag.putInt("MasterY", masterPos.getY());
		tag.putInt("MasterZ", masterPos.getZ());
	}
}
