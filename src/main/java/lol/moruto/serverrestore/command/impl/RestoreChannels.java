package lol.moruto.serverrestore.command.impl;

import lol.moruto.serverrestore.Options;
import lol.moruto.serverrestore.command.RestoreOperation;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.*;

public class RestoreChannels extends RestoreOperation {
    private static final List<ServerCategory> serverCategories = new ArrayList<>();

    public RestoreChannels() {
        Object rawCategories = Options.getConfig().get("serverCategories");

        if (rawCategories instanceof Map<?, ?>) {
            Map<?, ?> categoriesMap = (Map<?, ?>) rawCategories;
            for (Map.Entry<?, ?> entry : categoriesMap.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();

                if (key instanceof String && value instanceof List<?>) {
                    String categoryName = (String) key;
                    List<?> rawChannels = (List<?>) value;

                    List<String> channels = new ArrayList<>();
                    for (Object channel : rawChannels) {
                        if (channel instanceof String) {
                            channels.add((String) channel);
                        }
                    }

                    serverCategories.add(new ServerCategory(categoryName, channels));
                }
            }
        }

    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (Options.getConfig().get("restoreOperations.restoreChannels").equals("false")) return;

        for (ServerCategory serverCategory : serverCategories) {
            Category category = event.getGuild().createCategory(serverCategory.getName()).complete();
            serverCategory.getChannels().forEach(channelName -> category.createTextChannel(channelName).queue());
        }
    }

    public static class ServerCategory {
        private final String name;
        private final List<String> channels;

        public ServerCategory(String name, List<String> channels) {
            this.name = name;
            this.channels = channels != null ? channels : Collections.emptyList();
        }

        public String getName() {
            return name;
        }

        public List<String> getChannels() {
            return channels;
        }
    }
}
