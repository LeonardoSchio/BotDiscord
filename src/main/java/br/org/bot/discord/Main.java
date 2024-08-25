package br.org.bot.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.Collections;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String token = System.getProperty("TOKEN_JDA");


        JDA jda = JDABuilder.createLight(token, Collections.emptyList())
                .addEventListeners(new SlashCommandListener())
                .build();

        // Register your commands to make them visible globally on Discord:

        CommandListUpdateAction commands = jda.updateCommands();

        // Add all your commands on this action instance
        commands.addCommands(
                Commands.slash("say", "abacaxi")
                        .addOption(STRING, "content", "What the bot should say", true), // Accepting a user input
                Commands.slash("leave", "Makes the bot leave the server")
                        .setGuildOnly(true) // this doesn't make sense in DMs
                        .setDefaultPermissions(DefaultMemberPermissions.DISABLED) // only admins should be able to use this command.
        );

        // Then finally send your commands to discord using the API
        commands.queue();
    }
}