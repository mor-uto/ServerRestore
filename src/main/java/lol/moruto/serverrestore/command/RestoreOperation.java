package lol.moruto.serverrestore.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public abstract class RestoreOperation {
    public abstract void execute(SlashCommandInteractionEvent event);
}
