package me.leoo.bedwars.xpbar.listeners;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent;
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent;
import com.andrei1058.bedwars.api.events.player.PlayerReJoinEvent;
import com.andrei1058.bedwars.api.events.player.PlayerReSpawnEvent;
import me.leoo.bedwars.xpbar.utils.EventType;
import me.leoo.bedwars.xpbar.utils.XpBarUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class XpBarEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onKill(PlayerKillEvent event) {
        Player killer = event.getKiller();
        Player victim = event.getVictim();

        if (killer != null) {
            XpBarUtil.check(killer, EventType.KILL_DEATH);
        }

        XpBarUtil.check(victim, EventType.KILL_DEATH);
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onArenaStart(GameStateChangeEvent event) {
        if(event.getNewState() != GameState.playing)return;

        for (Player player : event.getArena().getPlayers()) {
            XpBarUtil.check(player, EventType.ARENA_START);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        XpBarUtil.check(player, EventType.MOVE);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRejoin(PlayerReJoinEvent event) {
        Player player = event.getPlayer();

        XpBarUtil.check(player, EventType.REJOIN);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRespawn(PlayerReSpawnEvent event) {
        Player player = event.getPlayer();

        XpBarUtil.check(player, EventType.RESPAWN);
    }
}
