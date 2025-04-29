package lol.moruto.serverrestore.command;

import lol.moruto.serverrestore.command.impl.RestoreChannels;
import lol.moruto.serverrestore.command.impl.RestoreRoles;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.List;

public class CommandListener extends ListenerAdapter {
    private final List<RestoreOperation> operations = Arrays.asList(
            new RestoreChannels(),
            new RestoreRoles()
    );

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("restore")) {
            operations.forEach(operation -> operation.execute(event));
        }
    }
}
