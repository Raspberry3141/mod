package org.polyfrost.example.testmacro.caculator.functions.nonmovement.position;

import org.polyfrost.example.testmacro.caculator.functions.Parser;
import org.polyfrost.example.testmacro.caculator.functions.Player;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.SpecialFunction;

public class FunctionPosX extends SpecialFunction {
    @Override
    public String[] names() {
        return new String[] {"positionx", "posx", "xposition", "xpos", "xcoord", "xcoords", "coordx", "coordsx"};
    }

    @Override
    public void specialRun(Player player, double args, Parser parser) {
        player.x = args;

    }
}