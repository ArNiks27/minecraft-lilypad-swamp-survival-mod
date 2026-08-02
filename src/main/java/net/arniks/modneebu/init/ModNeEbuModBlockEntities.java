
package net.arniks.modneebu.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.arniks.modneebu.block.entity.Lillypad5TileEntity;
import net.arniks.modneebu.block.entity.Lillypad4TileEntity;
import net.arniks.modneebu.block.entity.Lillypad3TileEntity;
import net.arniks.modneebu.block.entity.Lillypad1TileEntity;
import net.arniks.modneebu.block.entity.LillyPad2TileEntity;
import net.arniks.modneebu.ModNeEbuMod;

public class ModNeEbuModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ModNeEbuMod.MODID);
	public static final RegistryObject<BlockEntityType<Lillypad1TileEntity>> LILLYPAD_1 = REGISTRY.register("lillypad_1", () -> BlockEntityType.Builder.of(Lillypad1TileEntity::new, ModNeEbuModBlocks.LILLYPAD_1.get()).build(null));
	public static final RegistryObject<BlockEntityType<LillyPad2TileEntity>> LILLY_PAD_2 = REGISTRY.register("lilly_pad_2", () -> BlockEntityType.Builder.of(LillyPad2TileEntity::new, ModNeEbuModBlocks.LILLY_PAD_2.get()).build(null));
	public static final RegistryObject<BlockEntityType<Lillypad3TileEntity>> LILLYPAD_3 = REGISTRY.register("lillypad_3", () -> BlockEntityType.Builder.of(Lillypad3TileEntity::new, ModNeEbuModBlocks.LILLYPAD_3.get()).build(null));
	public static final RegistryObject<BlockEntityType<Lillypad4TileEntity>> LILLYPAD_4 = REGISTRY.register("lillypad_4", () -> BlockEntityType.Builder.of(Lillypad4TileEntity::new, ModNeEbuModBlocks.LILLYPAD_4.get()).build(null));
	public static final RegistryObject<BlockEntityType<Lillypad5TileEntity>> LILLYPAD_5 = REGISTRY.register("lillypad_5", () -> BlockEntityType.Builder.of(Lillypad5TileEntity::new, ModNeEbuModBlocks.LILLYPAD_5.get()).build(null));
	// Start of user code block custom block entities
	// Block entity для невидимого child-блока площадки мультиблока.
	public static final RegistryObject<BlockEntityType<net.arniks.modneebu.block.entity.LillypadChildBlockEntity>> LILLYPAD_CHILD = REGISTRY.register("lillypad_child",
			() -> BlockEntityType.Builder.of(net.arniks.modneebu.block.entity.LillypadChildBlockEntity::new, ModNeEbuModBlocks.LILLYPAD_CHILD.get()).build(null));

	// End of user code block custom block entities
	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
