package ca.ame94.SpawnerSuppression.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMgr {

    /**
     * The plugin manager is something global to bukkit that organizes how plugins load and interact,
     * and the pluginRef field is simply a reference to our plugin.
     */
    private static PluginManager pluginMgr;
    private static JavaPlugin pluginRef;

    public static PluginManager getPluginMgr() {
        return pluginMgr;
    }

    /** This will return the plugin instance */
    public static JavaPlugin getPlugin() {
        return pluginRef;
    }

    /** Called from onEnable to populate the fields above */
    public static void Init(JavaPlugin p) {
        pluginMgr = Bukkit.getPluginManager();
        pluginRef = p;
    }

    /** Tell the plugin manager about an event we wish to register. */
    public static void RegisterEvent(Listener l) {
        pluginMgr.registerEvents(l, pluginRef);
    }

    /** Reload the config */
    public static void Reload() {
        pluginRef.reloadConfig();
    }
}
