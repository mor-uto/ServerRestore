package lol.moruto.serverrestore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Options {
    public static String TOKEN = "";
    public static String GUILDID = "";

    public static void init() {
        try (BufferedReader br = new BufferedReader(new FileReader("options.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("=", 2);
                if (parts.length != 2) continue;

                String key = parts[0].trim();
                String value = parts[1].trim();

                switch (key) {
                    case "TOKEN":
                        TOKEN = value;
                        break;
                    case "GUILDID":
                        GUILDID = value;
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read options.txt");
            e.printStackTrace();
        }
    }
}
