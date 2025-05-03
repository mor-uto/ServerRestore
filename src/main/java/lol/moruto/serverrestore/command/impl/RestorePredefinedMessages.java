package lol.moruto.serverrestore.command.impl;

import lol.moruto.serverrestore.Options;
import lol.moruto.serverrestore.command.RestoreOperation;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.Map;

public class RestorePredefinedMessages extends RestoreOperation {
    Map<String, Map<String, Object>> messages = (Map<String, Map<String, Object>>) Options.getConfig().get("predefinedMessages");

    public RestorePredefinedMessages() {

    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (Options.getConfig().get("restoreOperations.restorePredefinedMessages").equals("false")) return;

        if (messages != null) {
            for (Map.Entry<String, Map<String, Object>> entry : messages.entrySet()) {
                Map<String, Object> messageData = entry.getValue();
                String channelName = (String) messageData.get("channel");
                String message = messageData.get("message").toString();

                TextChannel channel = event.getJDA().getTextChannelsByName(channelName, false).stream().findFirst().get();
                channel.sendMessage(message).queue();
            }
        }
    }
}
