package org.polyfrost.example;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.polyfrost.example.command.ExampleCommand;
import org.polyfrost.example.config.TestConfig;
import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import net.minecraftforge.fml.common.Mod;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.polyfrost.example.testmacro.command.simulate;
import org.polyfrost.example.testmacro.eventhandler.TickHandler;
import org.polyfrost.example.testmacro.renderer.LineStrips;
import org.polyfrost.example.testmacro.renderer.labels.Labels;
import org.polyfrost.example.testmacro.utils.posXYZ;

import static org.polyfrost.example.testmacro.eventhandler.TickHandler.positionQueue;

/**
 * The entrypoint of the Example Mod that initializes it.
 *
 * @see Mod
 * @see InitializationEvent
 */
@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod {

    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    @Mod.Instance(MODID)
    public static ExampleMod INSTANCE; // Adds the instance of the mod, so we can access other variables.
    public static TestConfig config;

    //test
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent preEvent) {
        for (int i =0;i<positionQueue.length;i++) {
            positionQueue[i] = new posXYZ (0D,0D,0D);
        }
    }

    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new TestConfig();
        CommandManager.INSTANCE.registerCommand(new ExampleCommand());

        //test macro
        MinecraftForge.EVENT_BUS.register(new TickHandler ());
        MinecraftForge.EVENT_BUS.register(new Labels ());
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new LineStrips ());
        ClientCommandHandler.instance.registerCommand(new simulate ());

    }
}
