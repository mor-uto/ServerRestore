package lol.moruto.serverrestore.command.impl;

import lol.moruto.serverrestore.command.RestoreOperation;
import lol.moruto.serverrestore.Options;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestoreRoles extends RestoreOperation {
    private final List<ServerRole> serverRoles = new ArrayList<>();

    public RestoreRoles() {
        Object rawRoles = Options.getConfig().get("roles");

        if (rawRoles instanceof Map<?, ?>) {
            Map<?, ?> rolesMap = (Map<?, ?>) rawRoles;

            for (Map.Entry<?, ?> entry : rolesMap.entrySet()) {
                Object roleName = entry.getKey();
                Object roleConfig = entry.getValue();

                if (roleName instanceof String && roleConfig instanceof Map<?, ?>) {
                    Map<?, ?> roleDetails = (Map<?, ?>) roleConfig;

                    boolean displaySeparately = false;
                    boolean anyoneMention = false;

                    Object displaySeparatelyObj = roleDetails.get("displaySeperately");
                    Object anyoneMentionObj = roleDetails.get("anyoneMention");

                    if (displaySeparatelyObj instanceof Boolean) displaySeparately = (Boolean) displaySeparatelyObj;
                    if (anyoneMentionObj instanceof Boolean) anyoneMention = (Boolean) anyoneMentionObj;

                    serverRoles.add(new ServerRole((String) roleName, displaySeparately, anyoneMention));
                }
            }
        }

    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (Options.getConfig().get("restoreOperations.restoreRoles").equals("false")) return;

        Guild guild = event.getGuild();
        if (guild == null) {
            event.reply("Could not retrieve the guild.").queue();
            return;
        }

        serverRoles.forEach(role -> createRole(guild, role));
    }

    private void createRole(Guild guild, ServerRole serverRole) {
        Role existingRole = guild.getRolesByName(serverRole.getName(), true).stream().findFirst().orElse(null);
        if (existingRole != null) return;
        guild.createRole().setName(serverRole.getName()).setMentionable(serverRole.isAnyoneMention()).setHoisted(serverRole.isDisplaySeparately()).queue();
    }

    public static class ServerRole {
        private final String name;
        private final boolean displaySeparately;
        private final boolean anyoneMention;

        public ServerRole(String name, boolean displaySeparately, boolean anyoneMention) {
            this.name = name;
            this.displaySeparately = displaySeparately;
            this.anyoneMention = anyoneMention;
        }

        public String getName() {
            return name;
        }

        public boolean isDisplaySeparately() {
            return displaySeparately;
        }

        public boolean isAnyoneMention() {
            return anyoneMention;
        }
    }
}
