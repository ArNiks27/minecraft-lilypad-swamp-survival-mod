package net.arniks.modneebu.init;

import net.arniks.modneebu.block.*;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.arniks.modneebu.ModNeEbuMod;

public class ModNeEbuModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, ModNeEbuMod.MODID);
	public static final RegistryObject<Block> LILLYPAD_1 = REGISTRY.register("lillypad_1", () -> new Lillypad1Block());
	public static final RegistryObject<Block> LILLY_PAD_2 = REGISTRY.register("lilly_pad_2", () -> new LillyPad2Block());
	public static final RegistryObject<Block> LILLYPAD_3 = REGISTRY.register("lillypad_3", () -> new Lillypad3Block());
	public static final RegistryObject<Block> LILLYPAD_4 = REGISTRY.register("lillypad_4", () -> new Lillypad4Block());
	public static final RegistryObject<Block> LILLYPAD_5 = REGISTRY.register("lillypad_5", () -> new Lillypad5Block());
	public static final RegistryObject<Block> FLOWERLILLY = REGISTRY.register("flowerlilly", () -> new FlowerlillyBlock());
	public static final RegistryObject<Block> LILLYPAD = REGISTRY.register("lillypad", () -> new LillypadBlock());
	public static final RegistryObject<Block> LILLYPADFAKE = REGISTRY.register("lillypadfake", () -> new LillypadfakeBlock());
	public static final RegistryObject<Block> LILLYPADLAMINAR = REGISTRY.register("lillypadlaminar", () -> new LillypadlaminarBlock());
	public static final RegistryObject<Block> CATTAILSHTUKI = REGISTRY.register("cattailshtuki", () -> new CattailshtukiBlock());
	public static final RegistryObject<Block> CATTAILNETUSHTUCHEK = REGISTRY.register("cattailnetushtuchek", () -> new CattailnetushtuchekBlock());
	public static final RegistryObject<Block> CATTAIL = REGISTRY.register("cattail", () -> new CattailBlock());
	public static final RegistryObject<Block> BLOCK_MUD_1 = REGISTRY.register("block_mud_1", () -> new BlockMudpolovina());
	public static final RegistryObject<Block> BLOCK_MUD_2 = REGISTRY.register("block_mud_2", () -> new BlockMudpolovina2());
	// Start of user code block custom blocks
	// Служебный невидимый блок для площадки мультиблока lilypad_4/lilypad_5.
	// Не имеет BlockItem — ставится только кодом из LillyPomogator, никогда игроком напрямую.
	public static final RegistryObject<Block> LILLYPAD_CHILD = REGISTRY.register("lillypad_child", () -> new LillypadChildBlock());
	// End of user code block custom blocks
}
