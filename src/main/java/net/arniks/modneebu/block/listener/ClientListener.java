package net.arniks.modneebu.block.listener;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.arniks.modneebu.init.ModNeEbuModBlockEntities;
import net.arniks.modneebu.block.renderer.Lillypad5TileRenderer;
import net.arniks.modneebu.block.renderer.Lillypad4TileRenderer;
import net.arniks.modneebu.block.renderer.Lillypad3TileRenderer;
import net.arniks.modneebu.block.renderer.Lillypad1TileRenderer;
import net.arniks.modneebu.block.renderer.LillyPad2TileRenderer;
import net.arniks.modneebu.ModNeEbuMod;

@Mod.EventBusSubscriber(modid = ModNeEbuMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientListener {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerBlockEntityRenderer(ModNeEbuModBlockEntities.LILLYPAD_1.get(), context -> new Lillypad1TileRenderer());
		event.registerBlockEntityRenderer(ModNeEbuModBlockEntities.LILLY_PAD_2.get(), context -> new LillyPad2TileRenderer());
		event.registerBlockEntityRenderer(ModNeEbuModBlockEntities.LILLYPAD_3.get(), context -> new Lillypad3TileRenderer());
		event.registerBlockEntityRenderer(ModNeEbuModBlockEntities.LILLYPAD_4.get(), context -> new Lillypad4TileRenderer());
		event.registerBlockEntityRenderer(ModNeEbuModBlockEntities.LILLYPAD_5.get(), context -> new Lillypad5TileRenderer());
	}
}
