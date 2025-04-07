package org.polyfrost.example.testmacro.caculator.functions.repeat;

import org.polyfrost.example.testmacro.caculator.functions.Function;
import org.polyfrost.example.testmacro.caculator.functions.Parser;
import org.polyfrost.example.testmacro.caculator.functions.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionRepeat extends Function {
    @Override
    public String[] names() {
        return new String[]{"r", "repeat"};
    }

    @Override
    public void run(Player player, int duration, float facing, ArrayList<Character> modifiers, HashMap<String, Double> effects) throws DurationException, InvalidKeypressException {
        // Not used for repeat function
    }

    public void repeat(Player player, String innerCommand, int times, Parser parser) throws Exception {
        //System.out.println("Repeating '" + innerCommand + "' " + times + " times");
        Parser subParser = new Parser(); // Create a new parser instance for each repetition
        for (int i = 0; i < times; i++) {
            subParser.parse(player, innerCommand); // Parse the inner command independently
        }
    }
}