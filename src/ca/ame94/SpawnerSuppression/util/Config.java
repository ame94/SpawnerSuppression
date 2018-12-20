package ca.ame94.SpawnerSuppression.util;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {

    /**
     * Create the plugin folder and blank config file if it doesn't exist yet.
     * Otherwise, load the config data.
     */
    public static void Init() {
        // Get the our plugin context/reference
        JavaPlugin p = PluginMgr.getPlugin();

        // Check if a config file hasn't been made yet
        if (!p.getDataFolder().exists()) {
            Logger.Info("Creating default configuration...");
            p.getConfig().options().copyDefaults(true);
            p.saveConfig();
        } else {
            // Config file found: here we'd actually load settings.
        }
    }

    /**
     * An example method for when we might want to start storing data.
     * @param someData The data to save. currently a string.
     */
    public static void storeData(String someData) {
        FileConfiguration c = PluginMgr.getPlugin().getConfig();
        c.set("some.yml.path.here", someData);

        PluginMgr.getPlugin().saveConfig();
    }

    /**
     * Remove data from the config
     * @param configSection The data to remove
     */
    public static void clearData(String configSection) {
        ConfigurationSection c = PluginMgr.getPlugin().getConfig().getConfigurationSection(configSection);

        c.set("", null);
        PluginMgr.getPlugin().saveConfig();
    }

    /**
     * Read a token in a given config path section of the plugin's config.
     * @param configPath The yml path the config
     * @param token The element name at the end of the path
     * @return A string
     */
    public static String ReadDataSection(String configPath, String token) {
        ConfigurationSection c = PluginMgr.getPlugin().getConfig().getConfigurationSection(configPath);

        return c.getString(token);
    }

}
