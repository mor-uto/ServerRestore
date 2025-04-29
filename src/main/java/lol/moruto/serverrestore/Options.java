package lol.moruto.serverrestore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Options {
    public static String TOKEN = "";
    public static String GUILDID = "";

    public static void init() {
        try (BufferedReader br = new BufferedReader(new FileReader("options.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            String[] parts = sb.toString().split("=");

            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
