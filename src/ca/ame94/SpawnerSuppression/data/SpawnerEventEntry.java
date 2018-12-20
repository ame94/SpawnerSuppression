package ca.ame94.SpawnerSuppression.data;

import org.bukkit.Location;

public class SpawnerEventEntry {

    private int id;
    private Location location;
    private int time;

    public SpawnerEventEntry(int id, Location location) {
        this.id = id;
        this.location = location;
        time = (int)(System.currentTimeMillis() / 1000L);
    }

    public Location getLocation() {
        return location;
    }

    public int getTime() {
        return time;
    }

    public int getId() {
        return id;
    }
}
