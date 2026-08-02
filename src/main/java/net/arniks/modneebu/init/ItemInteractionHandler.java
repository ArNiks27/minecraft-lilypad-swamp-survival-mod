package net.arniks.modneebu.init;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

import static net.arniks.modneebu.ModNeEbuMod.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class ItemInteractionHandler {

    private static final ResourceLocation ALIEN_BASE_BLOCK = new ResourceLocation("bolotno_mod", "mud");

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase == TickEvent.Phase.END && !event.level.isClientSide) {
            Level level = event.level;
            if (level.getGameTime() % 5 == 0) {
                for (net.minecraft.world.entity.player.Player player : level.players()) {
                    AABB searchBox = player.getBoundingBox().inflate(32.0D);
                    List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, searchBox);

                    for (ItemEntity itemEntity : items) {
                        if (!itemEntity.isAlive()) continue;

                        ItemStack stack = itemEntity.getItem();
                        boolean seeds = stack.is(ModNeEbuModItems.SEEDS.get());

                        // тоже иишка придумала определять в рыхлом снеге ваще в первый раз узнал что можно такой хитростью получать положение предмета
                        BlockPos posInside = itemEntity.blockPosition();
                        BlockPos posUnder = posInside.below();

                        BlockState stateInside = level.getBlockState(posInside);
                        BlockState stateUnder = level.getBlockState(posUnder);

                        Block blockInside = stateInside.getBlock();
                        Block blockUnder = stateUnder.getBlock();

                        ResourceLocation blockInsideRegName = ForgeRegistries.BLOCKS.getKey(blockInside);
                        ResourceLocation blockUnderRegName = ForgeRegistries.BLOCKS.getKey(blockUnder);

                        // cнег
                        if (seeds && ((blockInsideRegName != null && blockInsideRegName.equals(ALIEN_BASE_BLOCK)) ||
                                (blockUnderRegName != null && blockUnderRegName.equals(ALIEN_BASE_BLOCK)))) {

                            // тут сгенерала но вроде легко
                            BlockPos spawnPos = (blockInsideRegName != null && blockInsideRegName.equals(ALIEN_BASE_BLOCK)) ? posInside : posUnder;
                            RandomSource random = level.getRandom();

                            BlockState newState = random.nextBoolean() ?
                                    ModNeEbuModBlocks.LILLYPAD.get().defaultBlockState() :
                                    ModNeEbuModBlocks.LILLYPADFAKE.get().defaultBlockState();

                            level.setBlockAndUpdate(spawnPos, newState);

                            if (level instanceof ServerLevel serverLevel) {
                                BlockState state = Blocks.CAVE_VINES.defaultBlockState();
                                BlockParticleOption particleData = new BlockParticleOption(ParticleTypes.BLOCK, state);
                                serverLevel.sendParticles(
                                        particleData,
                                        spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5,
                                        40,
                                        0.3, 1, 0.3,
                                        0.1
                                );
                            }

                            stack.shrink(1);
                            if (stack.isEmpty()) {
                                itemEntity.discard();
                            }
                            continue;
                        }

                        // глина
                        if (blockUnder == Blocks.CLAY || blockInside == Blocks.CLAY && seeds  ) {

                            BlockPos clayPos = (blockUnder == Blocks.CLAY) ? posUnder : posInside;
                            Block replacement = ForgeRegistries.BLOCKS.getValue(ALIEN_BASE_BLOCK);

                            if (replacement != null) {
                                level.setBlock(clayPos.above(), Blocks.BIG_DRIPLEAF.defaultBlockState(), 3);

                            }
                            if (level instanceof ServerLevel serverLevel) {
                                BlockState state = Blocks.CAVE_VINES.defaultBlockState();
                                BlockParticleOption particleData = new BlockParticleOption(ParticleTypes.BLOCK, state);
                                serverLevel.sendParticles(
                                        particleData,
                                        clayPos.getX() + 0.5, clayPos.getY(), clayPos.getZ() + 0.5,
                                        50,
                                        0.3, 1, 0.3,
                                        0.1
                                );
                            }
                            stack.shrink(1);
                            if (stack.isEmpty()) {
                                itemEntity.discard();
                            }
                            continue;
                        }

                        // любой предмет упал на фейковую кувшинку
                        if (blockUnder == ModNeEbuModBlocks.LILLYPADFAKE.get() || blockInside == ModNeEbuModBlocks.LILLYPADFAKE.get() && !seeds  ) {

                            BlockPos breakPos = (blockUnder == ModNeEbuModBlocks.LILLYPADFAKE.get()) ? posUnder : posInside;
                            Block replacement = ForgeRegistries.BLOCKS.getValue(ALIEN_BASE_BLOCK);

                            if (replacement != null) {
                                level.setBlockAndUpdate(breakPos, replacement.defaultBlockState());

                                if (level instanceof ServerLevel serverLevel) {
                                    BlockState state = Blocks.LILY_PAD.defaultBlockState();
                                    BlockParticleOption particleData = new BlockParticleOption(ParticleTypes.BLOCK, state);
                                    serverLevel.sendParticles(
                                            particleData,
                                            breakPos.getX() + 0.5, breakPos.getY(), breakPos.getZ() + 0.5,
                                            40,
                                            0.3, 1, 0.3,
                                            0.1
                                    );
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}