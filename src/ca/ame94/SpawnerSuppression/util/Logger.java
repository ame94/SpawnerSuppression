package ca.ame94.SpawnerSuppression.util;

import org.bukkit.Bukkit;

import java.util.logging.Level;

/**
 * Offer three different levels of logging by changing the color of the message depending on severity.
 */
public class Logger {

    public static void Info(String msg) {
        Bukkit.getLogger().log(Level.INFO, "[SpawnerSuppression] " + msg);
    }

    public static void Warning(String msg) {
        Bukkit.getLogger().log(Level.WARNING, "[SpawnerSuppression] §e" + msg);
    }

    public static void Severe(String msg) {
        Bukkit.getLogger().log(Level.SEVERE, "[SpawnerSuppression] §c" + msg);
    }

}
