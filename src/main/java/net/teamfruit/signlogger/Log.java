package net.teamfruit.signlogger;

import com.google.common.base.Charsets;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// ログ
public class Log implements AutoCloseable {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss.n");

    private File path;
    private CSVPrinter writer;

    public Log(File path) throws IOException {
        this.path = path;

        CSVFormat format = CSVFormat.EXCEL;
        if (!path.exists())
            format = format.withHeader("時間", "座標", "UUID", "名前", "メッセージ");
        format = format.withQuoteMode(QuoteMode.MINIMAL);
        writer = format.print(new OutputStreamWriter(new FileOutputStream(path, true), Charsets.UTF_8));
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }

    public void log(LocalDateTime time, Player player, Block block, String text) throws IOException {
        String locText = String.format("%d %d %d", block.getX(), block.getY(), block.getZ());

        writer.printRecord(formatter.format(time), locText, player.getUniqueId().toString(), player.getName(), text);
        writer.flush();
    }
}
