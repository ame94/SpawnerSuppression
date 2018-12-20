package ca.ame94.SpawnerSuppression;

import ca.ame94.SpawnerSuppression.listeners.SpawnerBlock;
import ca.ame94.SpawnerSuppression.util.Config;
import ca.ame94.SpawnerSuppression.util.Logger;
import ca.ame94.SpawnerSuppression.util.PluginMgr;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Plugins can contain several classes, but one and one alone must extend the JavaPlugin
 * class provided by Bukkit so that the game server knows it is the "main" class for
 * that plugin. Our class PluginMain then overrides a few key methods from its base
 * class so it will actually function as a plugin.
 */
public class PluginMain extends JavaPlugin {

    @Override
    public void onEnable() {
        // The very first point of execution happens here.
        // Use a logging class to echo to the console that the plugin is starting up.
        Logger.Info("Starting up...");
        PluginMgr.Init(this);
        Config.Init();

        // Register our spawner event
        PluginMgr.RegisterEvent(new SpawnerBlock());
    }

    @Override
    public void onDisable()
    {
        // The last method run by the plugin before the server shuts down.
        Logger.Info("Shutting down...");
    }

    // As commands from console/users are given, this method processes them.
    // Context about who is sending it and what the command arguments are is provided.
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage("SpawnerSuppression help:");
            // Additional commands and their usages will be listed here.
            return true; // the command was processed successfully / understood return true
        }

        // if we get this far, the command was not understood and false is returned.
        return false;
    }
}
