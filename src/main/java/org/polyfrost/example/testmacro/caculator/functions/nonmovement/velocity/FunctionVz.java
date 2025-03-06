package org.polyfrost.example.testmacro.caculator.functions.nonmovement.velocity;

import org.polyfrost.example.testmacro.caculator.functions.Parser;
import org.polyfrost.example.testmacro.caculator.functions.Player;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.SpecialFunction;

public class FunctionVz extends SpecialFunction {

    @Override
    public String[] names() {
        return new String[] {"vz", "setvz", "setvelocityz", "speedz", "setspeedz"};
    }

    @Override
    public void specialRun(Player player, double args, Parser parser) {
        player.vz = args;

    }

}