package org.polyfrost.example.config;

import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.core.OneKeyBind;
import cc.polyfrost.oneconfig.libs.universal.UKeyboard;
import org.polyfrost.example.ExampleMod;
import org.polyfrost.example.hud.TestHud;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;
import org.polyfrost.example.testmacro.eventhandler.TickHandler;

/**
 * The main Config entrypoint that extends the Config type and inits the config options.
 * See <a href="https://docs.polyfrost.cc/oneconfig/config/adding-options">this link</a> for more config Options
 */
public class TestConfig extends Config {

    //simple keybind to not run /s run all time
    @KeyBind(
            name = "Run Macro"
    )
    public static OneKeyBind runMacro = new OneKeyBind(UKeyboard.KEY_NONE);

    public TestConfig() {
        super(new Mod(ExampleMod.NAME, ModType.UTIL_QOL), ExampleMod.MODID + ".json");
        initialize();

        registerKeyBind(runMacro, TickHandler::runCommand);
    }
}

