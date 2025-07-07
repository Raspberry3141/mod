package org.polyfrost.example.testmacro.command;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockWall;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

public class teleport extends CommandBase {
    @Override
    public String getCommandName() {
        return "tpl";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "tpl";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        final Minecraft instance = Minecraft.getMinecraft();
        BlockPos objective = instance.thePlayer.rayTrace(200, 1).getBlockPos();
        Block block = instance.theWorld.getBlockState(objective).getBlock();
        if (block.isAir(Minecraft.getMinecraft().theWorld,objective)) {
            return;
        }

        double boundsY = block.getBlockBoundsMaxY();

        double x = objective.getX() + 0.5;
        double y = objective.getY() + boundsY;
        double z = objective.getZ() + 0.5;

        if (block instanceof BlockSnow) y -= 0.125;
        if (block instanceof BlockFence || block instanceof BlockWall) y += 0.5;

        instance.thePlayer.sendChatMessage("/tp " + x + " " + y + " " + z);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
