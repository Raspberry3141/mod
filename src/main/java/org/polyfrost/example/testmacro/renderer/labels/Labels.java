package org.polyfrost.example.testmacro.renderer.labels;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static org.polyfrost.example.testmacro.eventhandler.TickHandler.*;


public class Labels {
    @SubscribeEvent
    public void draw(RenderGameOverlayEvent.Text event) {
        if (Minecraft.getMinecraft().theWorld.isRemote) {
            FontRenderer fontrenderer = Minecraft.getMinecraft().fontRendererObj;
            fontrenderer.drawString("X:" + String.format("%.4f", posX), 10, 10, 0xFFFFFF);
            fontrenderer.drawString("Y:" + String.format("%.4f", posY), 10, 20, 0xFFFFFF);
            fontrenderer.drawString("Z:" + String.format("%.4f", posZ), 10, 30, 0xFFFFFF);
            fontrenderer.drawString("F:" + String.format("%.4f", formatYaw(yaw)), 10, 40, 0xFFFFFF);
        }
    }

    public static float formatYaw(float yaw) {
        float facing = yaw % 360;
        if (facing > 180) facing -= 360;
        else if (facing < -180) facing += 360;
        return facing;
    }
}