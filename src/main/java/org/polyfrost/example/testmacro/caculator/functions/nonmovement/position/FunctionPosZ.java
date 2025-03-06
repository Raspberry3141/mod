package org.polyfrost.example.testmacro.caculator.functions.nonmovement.position;

import org.polyfrost.example.testmacro.caculator.functions.Parser;
import org.polyfrost.example.testmacro.caculator.functions.Player;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.SpecialFunction;

public class FunctionPosZ extends SpecialFunction {
    @Override
    public String[] names() {
        return new String[] {"positionz", "posz", "zposition", "zpos", "zcoord", "zcoords", "coordz", "coordsz"};
    }

    @Override
    public void specialRun(Player player, double args, Parser parser) {
        player.z = args;

    }
}