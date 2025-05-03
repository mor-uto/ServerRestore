package lol.moruto.serverrestore;

import lol.moruto.serverrestore.command.CommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.internal.utils.JDALogger;

public class Main {
    private static JDA jda;

    public static void main(String[] args) {
        Options.init();
        JDALogger.setFallbackLoggerEnabled(false);

        try {
            jda = JDABuilder.createLight(Options.getToken())
                    .addEventListeners(new CommandListener())
                    .build().awaitReady();

            jda.getGuildById(Options.getGuildId()).upsertCommand("restore", "Restores the server").queue();
            System.out.println("[ServerRestore] Finished Loading.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
