package me.leoo.bedwars.xpbar.listeners;

import com.andrei1058.bedwars.api.events.player.PlayerLevelUpEvent;
import com.andrei1058.bedwars.api.events.player.PlayerXpGainEvent;
import me.leoo.bedwars.xpbar.XpBar;
import me.leoo.bedwars.xpbar.utils.EventType;
import me.leoo.bedwars.xpbar.utils.XpBarUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;


public class LevelupXpgainEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLevelUp(PlayerLevelUpEvent event) {
        Player player = event.getPlayer();

        XpBarUtil.check(player, EventType.LEVELUP_XPGAIN);

        if (XpBar.get().getMainConfig().getBoolean("xp_bar.level.update_events.levelup_xpgain_fireworks.enabled")) {
            XpBarUtil.sendFireworks(player);
        }

        //it will be added in future
        //ActionBarAPI.sendActionBar(p, "§6§lLevel up! §f" + e.getNewLevel(), 60);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onXpGain(PlayerXpGainEvent event) {
        Player player = event.getPlayer();

        XpBarUtil.check(player, EventType.LEVELUP_XPGAIN);

        //it will be added in future
        //ActionBarAPI.sendActionBar(p, "§b§lXp added! §f" + e.getAmount(), 60);
    }
}
