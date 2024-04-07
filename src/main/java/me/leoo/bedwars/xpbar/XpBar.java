package me.leoo.bedwars.xpbar;

import com.andrei1058.bedwars.BedWars;
import lombok.Getter;
import me.leoo.bedwars.xpbar.configuration.MainConfig;
import me.leoo.bedwars.xpbar.listeners.JoinEvent;
import me.leoo.bedwars.xpbar.listeners.LevelupXpgainEvent;
import me.leoo.bedwars.xpbar.listeners.XpBarEvent;
import me.leoo.bedwars.xpbar.utils.BedwarsMode;
import me.leoo.utils.bukkit.Utils;
import me.leoo.utils.bukkit.config.ConfigManager;
import me.leoo.utils.bukkit.events.Events;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class XpBar extends JavaPlugin {

    @Getter
    private static XpBar plugin;

    private ConfigManager mainConfig;
    private BedwarsMode bedwarsMode;

    @Override
    public void onEnable() {
        plugin = this;

        Utils.initialize(this);

        for (BedwarsMode mode : BedwarsMode.values()) {
            if (Bukkit.getPluginManager().isPluginEnabled(mode.getName())) {
                bedwarsMode = mode;

                getLogger().info("Hooked into " + mode.getName());
            }
        }

        if (bedwarsMode == null) {
            getLogger().info("Bedwars1058/BedwarsProxy not found. Disabling...");

            Bukkit.getPluginManager().disablePlugin(this);

            return;
        }

        mainConfig = new MainConfig("config", bedwarsMode.getPath());

        registerEvents();

        getLogger().info(getDescription().getName() + " plugin by itz_leoo has been successfully enabled.");
    }

    @Override
    public void onDisable() {
        Utils.disable();

        getLogger().info(getDescription().getName() + " plugin by itz_leoo has been successfully disabled.");
    }

    private void registerEvents() {
        Events.register(new JoinEvent());

        if (bedwarsMode.equals(BedwarsMode.BEDWARS)) {
            Events.register(new XpBarEvent(), new LevelupXpgainEvent());
        }
    }

    public static XpBar get() {
        return plugin;
    }
}
