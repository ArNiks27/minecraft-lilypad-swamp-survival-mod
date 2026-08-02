package net.arniks.modneebu.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.arniks.modneebu.item.SeedsItem;
import net.arniks.modneebu.item.CattailspikeItem;
import net.arniks.modneebu.block.display.Lillypad5DisplayItem;
import net.arniks.modneebu.block.display.Lillypad4DisplayItem;
import net.arniks.modneebu.block.display.Lillypad3DisplayItem;
import net.arniks.modneebu.block.display.Lillypad1DisplayItem;
import net.arniks.modneebu.block.display.LillyPad2DisplayItem;
import net.arniks.modneebu.ModNeEbuMod;

public class ModNeEbuModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ModNeEbuMod.MODID);
	public static final RegistryObject<Item> LILLYPAD_1 = REGISTRY.register(ModNeEbuModBlocks.LILLYPAD_1.getId().getPath(), () -> new Lillypad1DisplayItem(ModNeEbuModBlocks.LILLYPAD_1.get(), new Item.Properties()));
	public static final RegistryObject<Item> LILLY_PAD_2 = REGISTRY.register(ModNeEbuModBlocks.LILLY_PAD_2.getId().getPath(), () -> new LillyPad2DisplayItem(ModNeEbuModBlocks.LILLY_PAD_2.get(), new Item.Properties()));
	public static final RegistryObject<Item> LILLYPAD_3 = REGISTRY.register(ModNeEbuModBlocks.LILLYPAD_3.getId().getPath(), () -> new Lillypad3DisplayItem(ModNeEbuModBlocks.LILLYPAD_3.get(), new Item.Properties()));
	public static final RegistryObject<Item> LILLYPAD_4 = REGISTRY.register(ModNeEbuModBlocks.LILLYPAD_4.getId().getPath(), () -> new Lillypad4DisplayItem(ModNeEbuModBlocks.LILLYPAD_4.get(), new Item.Properties()));
	public static final RegistryObject<Item> LILLYPAD_5 = REGISTRY.register(ModNeEbuModBlocks.LILLYPAD_5.getId().getPath(), () -> new Lillypad5DisplayItem(ModNeEbuModBlocks.LILLYPAD_5.get(), new Item.Properties()));
	public static final RegistryObject<Item> FLOWERLILLY = block(ModNeEbuModBlocks.FLOWERLILLY);
	public static final RegistryObject<Item> LILLYPAD = block(ModNeEbuModBlocks.LILLYPAD);
	public static final RegistryObject<Item> LILLYPADFAKE = block(ModNeEbuModBlocks.LILLYPADFAKE);
	public static final RegistryObject<Item> LILLYPADLAMINAR = block(ModNeEbuModBlocks.LILLYPADLAMINAR);
	public static final RegistryObject<Item> SEEDS = REGISTRY.register("seeds", () -> new SeedsItem());
	public static final RegistryObject<Item> CATTAILSPIKE = REGISTRY.register("cattailspike", () -> new CattailspikeItem());
	public static final RegistryObject<Item> CATTAILSHTUKI = block(ModNeEbuModBlocks.CATTAILSHTUKI);
	public static final RegistryObject<Item> CATTAILNETUSHTUCHEK = block(ModNeEbuModBlocks.CATTAILNETUSHTUCHEK);
	public static final RegistryObject<Item> CATTAIL = block(ModNeEbuModBlocks.CATTAIL);
	public static final RegistryObject<Item> BLOCK_MUD_1 = block(ModNeEbuModBlocks.BLOCK_MUD_1);
	public static final RegistryObject<Item> BLOCK_MUD_2 = block(ModNeEbuModBlocks.BLOCK_MUD_2);

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
