package ca.ame94.SpawnerSuppression.data;

import org.bukkit.Location;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class acts as a spawner database where all events are recorded. Each spawn event that happens
 * globally on the server will pass through this class to determine whether or not to cancel the event
 * if it is too close to another spawner that has fired recently - itself included.
 */
public class SpawnerDB {

    /** New spawn events are collected here */
    public static Map<Integer, SpawnerEventEntry> EventHistory = new HashMap<>();

    /** Minimum distance between spawner blocks. Represented as distance
     * squared; sqrt() is unnecessarily computationally expensive to be used internally. */
    public static int MinSpawnDistanceSquared = 400; // 20 by default

    /** Minimum time needed before a new event in the same location can replace an old */
    public static int SpawnCooldownTime = 30;

    /** Incremented for each new event created */
    private static int IDCounter = 0;

    public static int getMinSpawnDistance() {
        return (int)Math.sqrt(MinSpawnDistanceSquared);
    }

    public static void setMinSpawnDistance(int minSpawnDistance) {
        MinSpawnDistanceSquared = minSpawnDistance * minSpawnDistance;
    }

    public static int getSpawnCooldownTime() {
        return SpawnCooldownTime;
    }

    public static void setSpawnCooldownTime(int spawnCooldownTime) {
        SpawnCooldownTime = spawnCooldownTime;
    }

    /**
     * Returns an event entry for the given location where it's distance is less than
     * or equal to a location found within the event list. Old entries are automatically
     * removed.
     * @param location The relative location to look for
     * @return An event proximal to location given, null otherwise.
     */
    public static SpawnerEventEntry GetEntryForNearbyLocation(Location location) {
        SpawnerEventEntry ReturnedEvent = null;
        int CurrentTime = (int)(System.currentTimeMillis() / 1000L);
        List<Integer> OldEntries = new ArrayList<>();

        // For each entry in the event history
        for (Integer id : EventHistory.keySet()) {
            SpawnerEventEntry Event = EventHistory.get(id);
            // Check if it is within range of the given location
            if (location.distanceSquared(Event.getLocation()) <= MinSpawnDistanceSquared) {
                // If one is found, check its age
                if (Event.getTime() + SpawnCooldownTime < CurrentTime) {
                    // Too outdated for use, record for later removal
                    OldEntries.add(id);
                } else {
                    // Stop looking once an event has been found.
                    ReturnedEvent = Event;
                    break;
                }
            }
        }

        // Remove any old entries that were found
        if (OldEntries.size() > 0) {
            for (int entry : OldEntries) {
                EventHistory.remove(entry);
            }
        }

        return ReturnedEvent;
    }

    /**
     * Creates a new event for the given location. Assumes that GetEntryForNearbyLocation
     * has already been called to ensure an event near that location doesn't already exist.
     * @param location The new location
     */
    public static void InsertNewLocation(Location location) {
        EventHistory.put(IDCounter, new SpawnerEventEntry(IDCounter, location));
        ++IDCounter;
    }

}
