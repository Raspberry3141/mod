package org.polyfrost.example.testmacro.caculator.functions.nonmovement.globals;


import org.polyfrost.example.testmacro.caculator.functions.Parser;
import org.polyfrost.example.testmacro.caculator.functions.Player;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.SpecialFunction;
import org.polyfrost.example.testmacro.caculator.utils.ParserException;

public class FunctionSlip extends SpecialFunction {
    @Override
    public String[] names() {
        return new String[] {"slip", "slipperiness"};
    }

    @Override
    public void specialRun(Player player, double args, Parser parser) throws ParserException {

        parser.default_effects.put("slip", args);


    }
}