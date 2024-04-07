package me.leoo.bedwars.xpbar.utils;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.proxy.BedWarsProxy;
import com.cryptomorin.xseries.XSound;
import lombok.experimental.UtilityClass;
import me.leoo.bedwars.xpbar.XpBar;
import me.leoo.bedwars.xpbar.configuration.Path;
import me.leoo.utils.bukkit.bukkit.BukkitUtils;
import me.leoo.utils.bukkit.config.ConfigManager;
import me.leoo.utils.bukkit.task.Tasks;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.List;

@UtilityClass
public class XpBarUtil {

    private static final ConfigManager config = XpBar.get().getMainConfig();

    public void check(Player player, EventType eventType) {
        checkLevelBar("level", eventType, player);
        checkLevelBar("experience", eventType, player);
    }

    private void checkLevelBar(String type, EventType eventType, Player player) {
        String path = Path.XP_BAR + "." + type + ".";

        if (config.getBoolean(path + ".events." + eventType.getName())) {
            List<String> worlds = config.getList(path + ".worlds");

            if (worlds.contains("all") || worlds.contains(path)) {
                switch (type) {
                    case "level":
                        updateLevel(player);
                        break;
                    case "experience":
                        updateExp(player);
                        break;
                }
            }
        }
    }

    private void updateLevel(Player player) {
        if (player != null) {
            Tasks.later(() -> {
                if (XpBar.get().getBedwarsMode().equals(BedwarsMode.PROXY)) {
                    player.setLevel(0);
                    player.setLevel(BedWarsProxy.getLevelManager().getPlayerLevel(player));
                } else {
                    player.setLevel(0);
                    player.setLevel(BedWars.getLevelSupport().getPlayerLevel(player));
                }
            }, 10L);
        } else {
            Bukkit.getLogger().info("Event's player is null");
        }
    }

    private void updateExp(Player player) {
        if (player != null) {
            Tasks.later(() -> {
                if (XpBar.get().getBedwarsMode().equals(BedwarsMode.PROXY)) {
                    if (BedWarsProxy.getLevelManager().getCurrentXp(player) > BedWarsProxy.getLevelManager().getRequiredXp(player)) {
                        player.setExp(0);
                    } else {
                        player.setExp(0);
                        player.setExp((float) BedWarsProxy.getLevelManager().getCurrentXp(player) / BedWarsProxy.getLevelManager().getRequiredXp(player));
                    }
                } else {
                    if (BedWars.getLevelSupport().getCurrentXp(player) > BedWars.getLevelSupport().getRequiredXp(player)) {
                        player.setExp(0);
                    } else {
                        player.setExp(0);
                        player.setExp((float) BedWars.getLevelSupport().getCurrentXp(player) / BedWars.getLevelSupport().getRequiredXp(player));
                    }
                }
            }, 10L);
        }
    }

    public void sendFireworks(Player player) {
        BukkitUtils.sendFireworks(player.getLocation(), 1, Color.RED, Color.WHITE);

        player.getWorld().playSound(player.getLocation(), XSound.ENTITY_FIREWORK_ROCKET_BLAST.or(XSound.AMBIENT_CAVE).parseSound(), 5.0F, 0.5F);
    }
}
