package org.polyfrost.example.testmacro.caculator.functions.nonmovement.velocity;

import org.polyfrost.example.testmacro.caculator.functions.Parser;
import org.polyfrost.example.testmacro.caculator.functions.Player;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.SpecialFunction;

public class FunctionVx extends SpecialFunction {

    @Override
    public String[] names() {
        return new String[] {"vx", "setvx", "setvelocityx", "speedx", "setspeedx"};
    }

    @Override
    public void specialRun(Player player, double args, Parser parser) {
        player.vx = args;

    }

}