package org.polyfrost.example.testmacro.caculator.functions.sneaksprint;

import org.polyfrost.example.testmacro.caculator.functions.Function;
import org.polyfrost.example.testmacro.caculator.functions.Player;
import org.polyfrost.example.testmacro.caculator.utils.Arguments;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionSneakSprintJump45 extends Function {

    @Override
    public String[] names() {
        // TODO Auto-generated method stub
        return new String[] {"sneaksprintjump45", "snsj45", "crouchsprintjump45", "csj45"};
    }

    @Override
    public void run(Player player, int duration, float facing, ArrayList<Character> modifiers, HashMap<String, Double> effects)
            throws InvalidKeypressException {
        Arguments args = new Arguments();
        args.replace("duration", 1);
        args.replace("facing", (float) (facing));
        if (duration > 0) args.replace("forward", 1);
        else if (duration < 0) args.replace("forward", -1);

        checkNoModifiers(modifiers);
        checkEffects(effects, args, duration);

        args.replace("sneaking", true);
        args.replace("jumping", true);
        args.replace("sprinting", true);
        player.move(args);

        args.replace("facing", (float) (facing + 45));
        args.replace("duration", Math.abs(duration) - 1);
        args.replace("strafing", 1);
        args.replace("jumping", false);
        args.replace("airborne", true);
        player.move(args);


    }

}