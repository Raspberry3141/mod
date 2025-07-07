package org.polyfrost.example.testmacro.eventhandler;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.polyfrost.example.testmacro.caculator.functions.Parser;
import org.polyfrost.example.testmacro.caculator.functions.Player;
import org.polyfrost.example.testmacro.utils.MacroInput;
import org.polyfrost.example.testmacro.utils.posXYZ;

import java.util.ArrayList;

public class TickHandler {
    private static EntityOtherPlayerMP fakeEntity = null; // fake entity

    public static posXYZ[] positionQueue = new posXYZ[60];
    private static int macroTimer = 0;
    private static boolean macroRunning = false;
    public static ArrayList<MacroInput> inputarry = new ArrayList<>();
    private static int tickLength;
    private static String argument;
    private int attackTimer = 0;

    public static double posX = 0, posY = 0, posZ = 0;
    public static double velX = 0, velY = 0, velZ = 0;
    public static float yaw = 0;
    public static double forward = 0;
    public static double strafe = 0;

    public static void initializeFakeEntity() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.theWorld != null) {
            if (fakeEntity == null || fakeEntity.isDead) {
                GameProfile fakeProfile = new GameProfile(java.util.UUID.randomUUID(), "FakeMacroPlayer");
                fakeEntity = new EntityOtherPlayerMP(mc.theWorld, fakeProfile);

                EntityPlayerSP realPlayer = mc.thePlayer;
                if (realPlayer != null) {
                    fakeEntity.setLocationAndAngles(realPlayer.posX, realPlayer.posY, realPlayer.posZ, realPlayer.rotationYaw, realPlayer.rotationPitch);
                    fakeEntity.inventory.copyInventory(realPlayer.inventory);
                }
                fakeEntity.noClip = true;
                fakeEntity.setInvisible(false);
                fakeEntity.setCustomNameTag("FakeMacroPlayer");
                fakeEntity.setAlwaysRenderNameTag(true);

                mc.theWorld.spawnEntityInWorld(fakeEntity);
            }
        }
    }

    public static void runCommand() throws IllegalStateException {
        if (macroRunning) {
            // prevent running macro twice or more
            return;
        }

        macroTimer = 0;
        macroRunning = true;
        inputarry = Player.inputs;
        tickLength = inputarry.size();

        // kill entity if it exists
        if (fakeEntity != null) {
            fakeEntity.setDead();
            fakeEntity = null;
        }
        // spawnentity on macro start
        initializeFakeEntity();
    }

    public static void enableLines(String arg) throws Exception {
        argument = arg;
        Player player = new Player(Minecraft.getMinecraft().theWorld);
        Player.inputs.clear();
        Player.xresult.clear();
        Player.yresult.clear();
        Player.zresult.clear();
        (new Parser()).parse(player, argument);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        for (Entity e:Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if (e !=mc.thePlayer && mc.thePlayer.getDistanceToEntity(e)<=4.2F && attackTimer>5 && e instanceof EntityAnimal && ((EntityLivingBase) e).getHealth()>0) {
                float yaw = mc.thePlayer.cameraYaw;
                float pitch = mc.thePlayer.rotationPitch;
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(yaw,pitch,mc.thePlayer.onGround));
                mc.playerController.attackEntity(mc.thePlayer,e);
                mc.thePlayer.swingItem();
                attackTimer =0;
            }
        }
        attackTimer++;
    }

    @SubscribeEvent
    public void ListenForInfo(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;

        if (player == null) return;
        if (mc.theWorld.isRemote) {
            posX = player.posX;
            posY = player.posY;
            posZ = player.posZ;
            velX = player.motionX;
            velY = player.motionY;
            velZ = player.motionZ;
            yaw = player.rotationYaw;

            for (int i = positionQueue.length - 1; i > 0; i--) {
                positionQueue[i] = positionQueue[i - 1];
            }
            positionQueue[0] = new posXYZ(posX, posY, posZ);
        }
    }

    @SubscribeEvent
    public void runMacro(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        GameSettings options = mc.gameSettings;

        if (macroRunning) {
            if (macroTimer < tickLength) {
                //player maccro
                // need to add setting to not run the macro with the fake entity and the player same time

                KeyBinding.setKeyBindState(options.keyBindForward.getKeyCode(), inputarry.get(macroTimer).isW());
                KeyBinding.setKeyBindState(options.keyBindLeft.getKeyCode(), inputarry.get(macroTimer).isA());
                KeyBinding.setKeyBindState(options.keyBindBack.getKeyCode(), inputarry.get(macroTimer).isS());
                KeyBinding.setKeyBindState(options.keyBindRight.getKeyCode(), inputarry.get(macroTimer).isD());
                KeyBinding.setKeyBindState(options.keyBindJump.getKeyCode(), inputarry.get(macroTimer).isJump());
                KeyBinding.setKeyBindState(options.keyBindSprint.getKeyCode(), inputarry.get(macroTimer).isSprint());
                KeyBinding.setKeyBindState(options.keyBindSneak.getKeyCode(), inputarry.get(macroTimer).isSneak());
                player.rotationYaw = inputarry.get(macroTimer).getAngle();

                // init fake entity
                initializeFakeEntity();
                // update entity
                if (fakeEntity != null) {
                    double targetX = Player.xresult.get(macroTimer);
                    double targetY = Player.yresult.get(macroTimer);
                    double targetZ = Player.zresult.get(macroTimer);
                    float targetYaw = inputarry.get(macroTimer).getAngle(); // idk if it works correctly yet

                    fakeEntity.setPositionAndRotation(targetX, targetY, targetZ, targetYaw, 0.0F);

                    // animation to look like a normal player
                    fakeEntity.rotationYawHead = inputarry.get(macroTimer).getAngle(); // facing
                    fakeEntity.setSprinting(inputarry.get(macroTimer).isSprint()); // Sprint
                    fakeEntity.setSneaking(inputarry.get(macroTimer).isSneak()); // Sneak
                    fakeEntity.jumpMovementFactor = inputarry.get(macroTimer).isJump() ? 0.02F : 0.0F; // jump

                    // show walk animation
                    if (inputarry.get(macroTimer).isW() || inputarry.get(macroTimer).isA() || inputarry.get(macroTimer).isS() || inputarry.get(macroTimer).isD()) {
                        fakeEntity.limbSwing += 0.1F;
                        fakeEntity.limbSwingAmount = 1.0F;
                    } else {
                        fakeEntity.limbSwingAmount = 0.0F;
                    }

                    fakeEntity.onUpdate(); //update entity
                }

                macroTimer++;
            } else {
                macroRunning = false;
                macroTimer = 0;
                KeyBinding.unPressAllKeys();
/*
                // kill entity on macro end
                if (fakeEntity != null) {
                    fakeEntity.setDead();
                    fakeEntity = null;
                }

 */
            }
        }
    }

    @SubscribeEvent
    public void updateLinePos(TickEvent.ClientTickEvent event) {
    }
}