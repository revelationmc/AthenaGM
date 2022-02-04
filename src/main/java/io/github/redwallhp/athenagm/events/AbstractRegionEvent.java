package io.github.redwallhp.athenagm.events;

import io.github.redwallhp.athenagm.regions.CuboidRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public abstract class AbstractRegionEvent extends Event {
    private final CuboidRegion region;
    private final Player player;

    public AbstractRegionEvent(CuboidRegion region, Player player) {
        this.region = region;
        this.player = player;
    }

    public String getRegionName() {
        return this.region.getName();
    }

    public CuboidRegion getRegion() {
        return this.region;
    }

    public Player getPlayer() {
        return this.player;
    }
}
