package org.polyfrost.example.testmacro.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import static org.polyfrost.example.testmacro.caculator.functions.Player.*;


@SideOnly(Side.CLIENT)
public class LineStrips {
    @SubscribeEvent
    public void draw(DrawBlockHighlightEvent event) {
        configureGL(event);

        Tessellator ts = Tessellator.getInstance();
        WorldRenderer wr = ts.getWorldRenderer();
        //for (int i =1;i< positionQueue.length;i++) {
        //    if (positionQueue[i].getX() != positionQueue[i-1].getX() &&
        //        positionQueue[i].getY() != positionQueue[i-1].getY() &&
        //        positionQueue[i].getZ() != positionQueue[i-1].getZ()
        //    ) {
        //    }
        //    GL11.glColor4f(251F,0F, 185F,0.33F);
        //    wr.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);
        //    GL11.glLineWidth(10F);
        //    wr.pos(positionQueue[i-1].getX(), positionQueue[i-1].getY(), positionQueue[i-1].getZ()).color(251F, 0F, 185F, 0.33F).endVertex();
        //    wr.pos(positionQueue[i].getX(), positionQueue[i].getY(), positionQueue[i].getZ()).color(251F, 0F, 285F, 0.33F).endVertex();
        //    ts.draw();
        //}
        if (xresult.size() != 0) {
            for (int i =1;i< xresult.size();i++) {
                wr.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION_COLOR);
                GL11.glLineWidth(10F);
                wr.pos(xresult.get(i-1), yresult.get(i-1), zresult.get(i-1)).color(251F, 0F, 185F, 0.33F).endVertex();
                wr.pos(xresult.get(i), yresult.get(i), zresult.get(i)).color(251F, 0F, 285F, 0.33F).endVertex();
                ts.draw();
            }
        }
        revertGLConfig();
    }

    private void revertGLConfig() {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    private void configureGL(DrawBlockHighlightEvent event) {
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        double x = event.player.prevPosX + (event.player.posX - event.player.prevPosX) * (double)event.partialTicks;
        double y = event.player.prevPosY + (event.player.posY - event.player.prevPosY) * (double)event.partialTicks;
        double z = event.player.prevPosZ + (event.player.posZ - event.player.prevPosZ) * (double)event.partialTicks;

        Vec3 pos = new Vec3(x, y, z);
        GL11.glTranslated(-pos.xCoord, -pos.yCoord, -pos.zCoord);
    }

}