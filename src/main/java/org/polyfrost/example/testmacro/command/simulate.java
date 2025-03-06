package org.polyfrost.example.testmacro.command;


import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import org.polyfrost.example.testmacro.caculator.functions.Player;
import org.polyfrost.example.testmacro.eventhandler.TickHandler;


public class simulate extends CommandBase {
    @Override
    public String getCommandName() {
        return "s";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/s help";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args[0].equals("run")) {
            TickHandler.runCommand();
        } else if (args[0].equals("hide")) {
            Player.inputs.clear();
            Player.xresult.clear();
            Player.yresult.clear();
            Player.zresult.clear();
        } else {
            String ConcatenatedString = "";
            Player.inputs.clear();
            Player.xresult.clear();
            Player.yresult.clear();
            Player.zresult.clear();
            for (String arg : args) {
                ConcatenatedString += arg + " ";
            }
            try {
                TickHandler.enableLines(ConcatenatedString);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}