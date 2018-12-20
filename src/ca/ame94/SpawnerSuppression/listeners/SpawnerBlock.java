package ca.ame94.SpawnerSuppression.listeners;

import ca.ame94.SpawnerSuppression.data.SpawnerDB;
import ca.ame94.SpawnerSuppression.data.SpawnerEventEntry;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;

public class SpawnerBlock implements Listener {

    @EventHandler
    public void onSpawnerSpawn(SpawnerSpawnEvent event) {

        // Get the spawner block location in the world that just fired.
        Location SpawnerBlockLocation = event.getSpawner().getLocation();

        // Check to see if an event at that location has already occurred within a defined time frame
        SpawnerEventEntry spawnerEventEntry = SpawnerDB.GetEntryForNearbyLocation(SpawnerBlockLocation);
        if (spawnerEventEntry == null) {
            // null was returned, so one doesn't exist: insert this record and allow the mobs to spawn.
            SpawnerDB.InsertNewLocation(SpawnerBlockLocation);
        } else {
            // A location was found and the record hasn't expired yet: deny the entity from spawning.
            event.setCancelled(true);
        }
    }

}
