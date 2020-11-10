package net.teamfruit.signlogger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class SignEventListener implements Listener {
    private ZoneId timezone = ZoneId.of("Asia/Tokyo");

    @EventHandler
    public void onSignEvent(SignChangeEvent event) {
        LocalDateTime now = LocalDateTime.now(timezone);
        try {
            SignLogger.log.log(now, event.getPlayer(), event.getBlock(), String.join("\n", event.getLines()));
        } catch (IOException ignored) {
        }
    }
}
