package io.github.redwallhp.athenagm.events;

import io.github.redwallhp.athenagm.regions.CuboidRegion;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class PlayerEnterRegionEvent extends AbstractRegionEvent {
    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public PlayerEnterRegionEvent(CuboidRegion region, Player player) {
        super(region, player);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
