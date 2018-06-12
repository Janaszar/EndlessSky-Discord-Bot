package me.mcofficer.james.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import me.mcofficer.james.James;
import me.mcofficer.james.Util;
import net.dv8tion.jda.core.EmbedBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class Info extends Command {

    private String commit;

    public Info() {
        this.name = "info";
        this.help = "Shows information about the bot.";
        getInfo();
    }

    @Override
    protected void execute(CommandEvent event) {
        String description = String.format(
                        "- **Author:** Maximilian Korber\n" +
                        "- **Language**: Java\n" +
                        "- **Utilized Libraries:** JDA, JDA-Utilities, ESParser-java\n" +
                        "- **Maintainers:** M\\*C\\*O, tehhowch\n" +
                        "\n" +
                        "**Latest Commit:** %s\n" +
                        "\n" +
                        "[View known issues and feature requests](%s)",
                        commit, James.GITHUB_URL + "issues/");

        EmbedBuilder builder = new EmbedBuilder()
                .setTitle("EndlessSky-Discord-Bot", James.GITHUB_URL)
                .setColor(event.getGuild().getSelfMember().getColor())
                .setDescription(description);
        event.reply(builder.build());
    }

    private void getInfo() {
        JSONObject latest = new JSONArray(Util.getContentFromUrl("https://api.github.com/repos/MCOfficer/EndlessSky-Discord-Bot/commits"))
                .getJSONObject(0);
        commit = String.format("[%s](%s): %s", latest.getString("sha").substring(0, 7), latest.getString("html_url"),
                latest.getJSONObject("commit").getString("message"));
    }
}
