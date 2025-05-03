package lol.moruto.serverrestore;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Options {

    private static String token = "";
    private static String guildId = "";

    private static Map<String, Object> config = new HashMap<>();

    public static void init() {
        try (InputStream input = Files.newInputStream(Paths.get("options.yml"))) {
            config = new Yaml().load(input);

            token = String.valueOf(config.getOrDefault("token", ""));
            guildId = String.valueOf(config.getOrDefault("guildid", ""));

        } catch (Exception e) {
            System.err.println("Failed to load options.yml");
            e.printStackTrace();
        }
    }

    public static Map<String, Object> getConfig() {
        return config;
    }

    public static String getToken() {
        return token;
    }

    public static String getGuildId() {
        return guildId;
    }
}
