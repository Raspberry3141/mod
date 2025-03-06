package org.polyfrost.example.testmacro.caculator.functions.nonmovement.position;


import org.polyfrost.example.testmacro.caculator.functions.Parser;
import org.polyfrost.example.testmacro.caculator.functions.Player;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.SpecialFunction;

public class FunctionFacing extends SpecialFunction {
    @Override
    public String[] names() {
        return new String[] {"f", "facing", "face"};
    }

    @Override
    public void specialRun(Player player, double args, Parser parser) {
        parser.default_facing = (float) args;

    }
}