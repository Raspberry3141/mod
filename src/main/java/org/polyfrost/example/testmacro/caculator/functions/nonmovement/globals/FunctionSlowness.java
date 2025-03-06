package org.polyfrost.example.testmacro.caculator.functions.nonmovement.globals;


import org.polyfrost.example.testmacro.caculator.functions.Parser;
import org.polyfrost.example.testmacro.caculator.functions.Player;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.SpecialFunction;
import org.polyfrost.example.testmacro.caculator.utils.ParserException;

public class FunctionSlowness extends SpecialFunction {
    @Override
    public String[] names() {
        return new String[] {"slowness", "slow"};
    }

    @Override
    public void specialRun(Player player, double args, Parser parser) throws ParserException {
        if ((int) args != args) throw new ParserException("Potion effect level must be an integer.");

        parser.default_effects.put("slowness", args);


    }
}