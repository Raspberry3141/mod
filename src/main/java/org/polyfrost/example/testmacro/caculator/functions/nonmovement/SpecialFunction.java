package org.polyfrost.example.testmacro.caculator.functions.nonmovement;



import org.polyfrost.example.testmacro.caculator.functions.Function;
import org.polyfrost.example.testmacro.caculator.functions.Parser;
import org.polyfrost.example.testmacro.caculator.functions.Player;
import org.polyfrost.example.testmacro.caculator.utils.ParserException;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class SpecialFunction extends Function {

    public abstract void specialRun(Player player, double args, Parser parser) throws ParserException;

    @Override //This method is not used
    public final void run(Player player, int duration, float facing, ArrayList<Character> modifiers, HashMap<String,Double> effects)
            throws DurationException, InvalidKeypressException {
        return;
    }

}