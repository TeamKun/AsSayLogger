package net.teamfruit.assaylogger;

import net.teamfruit.assay.AsSayEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class AsSayEventListener implements Listener {
    private ZoneId timezone = ZoneId.of("Asia/Tokyo");

    @EventHandler
    public void onSignEvent(AsSayEvent event) {
        LocalDateTime now = LocalDateTime.now(timezone);
        try {
            AsSayLogger.log.log(now, event.getSender(), event.getAs(), event.getText());
        } catch (IOException ignored) {
        }
    }
}
