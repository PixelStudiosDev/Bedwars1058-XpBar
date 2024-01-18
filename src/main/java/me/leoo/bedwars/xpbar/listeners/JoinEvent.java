package me.leoo.bedwars.xpbar.listeners;

import me.leoo.bedwars.xpbar.utils.EventType;
import me.leoo.bedwars.xpbar.utils.XpBarUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        XpBarUtil.check(player, EventType.JOIN);
    }

}
