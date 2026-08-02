package net.arniks.modneebu.init;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

import static net.arniks.modneebu.ModNeEbuMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SlimeItemBounceHandler {

    private static final Set<Integer> wasOnGround = new HashSet<>();

    private static final double VERTICAL_SPEED = 0.6;
    private static final double HORIZONTAL_SPEED = 0.4;

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END || event.level.isClientSide()) return;

        if (event.level instanceof ServerLevel serverLevel) {
            for (Entity entity : serverLevel.getAllEntities()) {
                if (entity instanceof ItemEntity item) {

                    boolean grounded = item.onGround();

                    int id = item.getId();
                    boolean wasGroundedLastTick = wasOnGround.contains(id);

                    BlockPos below = item.blockPosition().below();
                    boolean onSlime = serverLevel.getBlockState(below).is(Blocks.SLIME_BLOCK);

                    if (grounded && !wasGroundedLastTick && onSlime) {
                        Vec3 motion = item.getDeltaMovement();
                        Vec3 horizontal = new Vec3(motion.x, 0, motion.z);

                        if (horizontal.lengthSqr() < 1.0E-4) {
                            double a = serverLevel.random.nextDouble() * Math.PI * 2;
                            horizontal = new Vec3(Math.cos(a), 0, Math.sin(a));
                        }
                        else {
                            horizontal = horizontal.normalize();
                        }

                        item.setDeltaMovement(horizontal.x * HORIZONTAL_SPEED, VERTICAL_SPEED, horizontal.z * HORIZONTAL_SPEED);
                        item.hasImpulse = true;
                    }

                    if (grounded) wasOnGround.add(id);
                    else wasOnGround.remove(id);

                }
            }
            wasOnGround.removeIf(id -> serverLevel.getEntity(id) == null || !serverLevel.getEntity(id).isAlive());
        }
    }
}