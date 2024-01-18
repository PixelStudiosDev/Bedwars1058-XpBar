package me.leoo.bedwars.xpbar.utils;

import me.leoo.bedwars.xpbar.configuration.Path;

public enum EventType {

    JOIN, REJOIN, ARENA_START, KILL_DEATH, MOVE, RESPAWN, LEVELUP_XPGAIN;

    public String getName() {
        return name().toLowerCase();
    }

    public String getLevelPath() {
        return Path.LEVEL_EVENT.replace("%type%", getName());
    }

    public String getExperiencePath() {
        return Path.XP_EVENT.replace("%type%", getName());
    }
}
