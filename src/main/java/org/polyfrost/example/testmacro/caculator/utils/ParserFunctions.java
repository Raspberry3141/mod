package org.polyfrost.example.testmacro.caculator.utils;


import org.polyfrost.example.testmacro.caculator.functions.Function;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.SpecialFunction;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.globals.FunctionSlip;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.globals.FunctionSlowness;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.globals.FunctionSwiftness;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.position.FunctionFacing;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.position.FunctionPosX;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.position.FunctionPosZ;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.velocity.FunctionVx;
import org.polyfrost.example.testmacro.caculator.functions.nonmovement.velocity.FunctionVz;
import org.polyfrost.example.testmacro.caculator.functions.sneak.*;
import org.polyfrost.example.testmacro.caculator.functions.sneaksprint.*;
import org.polyfrost.example.testmacro.caculator.functions.sprint.*;
import org.polyfrost.example.testmacro.caculator.functions.stop.FunctionStop;
import org.polyfrost.example.testmacro.caculator.functions.stop.FunctionStopAir;
import org.polyfrost.example.testmacro.caculator.functions.stop.FunctionStopJump;
import org.polyfrost.example.testmacro.caculator.functions.walk.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ParserFunctions {
    public static ArrayList<Function> functionInit() {
        ArrayList<Function> functions = new ArrayList<Function>();
        functions.addAll(Arrays.asList(new Function[] {
                new FunctionWalk (), new FunctionWalk45 (), new FunctionWalkAir (),
                new FunctionWalk45Air (), new FunctionWalkJump (), new FunctionWalkJump45(),
                new FunctionSprint (), new FunctionSprint45 (), new FunctionSprintAir (), new FunctionSprint45Air (),
                new FunctionLSprintJump (), new FunctionRSprintJump(), new FunctionLSprintJump45(), new FunctionRSprintJump45(),
                new FunctionSprintJump(), new FunctionSprintJump45(),
                new FunctionSneak (), new FunctionSneak45 (), new FunctionSneakAir (),
                new FunctionSneak45Air (), new FunctionSneakJump (), new FunctionSneakJump45(),
                new FunctionSneakSprint (), new FunctionSneakSprint45 (), new FunctionSneakSprintAir (), new FunctionSneakSprint45Air (),
                new FunctionLSneakSprintJump(), new FunctionRSneakSprintJump(), new FunctionLSneakSprintJump45(), new FunctionRSneakSprintJump45 (),
                new FunctionSneakSprintJump(), new FunctionSneakSprintJump45(),
                new FunctionStop (), new FunctionStopAir (), new FunctionStopJump ()
        }));
        return functions;
    }

    public static ArrayList<SpecialFunction> specialFunctionInit() {
        ArrayList<SpecialFunction> functions = new ArrayList<SpecialFunction>();
        functions.addAll(Arrays.asList(new SpecialFunction[] {
                new FunctionVx (), new FunctionVz (), new FunctionFacing (), new FunctionPosX (), new FunctionPosZ (),
                new FunctionSwiftness (), new FunctionSlowness (), new FunctionSlip ()
        }));
        return functions;
    }
}