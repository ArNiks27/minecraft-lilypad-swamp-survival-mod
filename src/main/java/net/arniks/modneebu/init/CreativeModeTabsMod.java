package net.arniks.modneebu.init;


import net.arniks.modneebu.ModNeEbuMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeModeTabsMod {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModNeEbuMod.MODID);

    public static final RegistryObject<CreativeModeTab> MOD_TAB = CREATIVE_MODE_TABS.register("modneebutab",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(net.minecraft.world.item.CreativeModeTabs.COMBAT)
                    .icon(() -> new ItemStack(ModNeEbuModBlocks.FLOWERLILLY.get()))
                    .title(Component.translatable("itemGroup.modneebutab"))
                    .displayItems((params, output) -> {
                        output.accept(ModNeEbuModItems.SEEDS.get());
                        output.accept(ModNeEbuModItems.CATTAILSPIKE.get());
                        output.accept(ModNeEbuModItems.FLOWERLILLY.get());
                        output.accept(ModNeEbuModItems.CATTAILNETUSHTUCHEK.get());
                        output.accept(ModNeEbuModItems.CATTAILSHTUKI.get());
                        output.accept(ModNeEbuModItems.LILLY_PAD_2.get());
                        output.accept(ModNeEbuModItems.LILLYPAD_1.get());
                        output.accept(ModNeEbuModItems.LILLYPAD_3.get());
                        output.accept(ModNeEbuModItems.LILLYPAD_4.get());
                        output.accept(ModNeEbuModItems.LILLYPAD_5.get());
                        output.accept(ModNeEbuModItems.BLOCK_MUD_1.get());
                        output.accept(ModNeEbuModItems.BLOCK_MUD_2.get());
                    })
                    .build());

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}

