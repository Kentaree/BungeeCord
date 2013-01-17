package net.md_5.bungee.command;

import java.util.Collection;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.UserConnection;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Command;

/**
 * Command to list and switch a player between available servers.
 */
public class CommandServer extends Command
{

    public CommandServer()
    {
        super("server", "bungeecord.command.server");
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        if (!(sender instanceof ProxiedPlayer))
        {
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        Collection<Server> servers = ProxyServer.getInstance().getServers();
        if (args.length == 0)
        {
            StringBuilder serverList = new StringBuilder();
            for (Server server : servers)
            {
                serverList.append(server.getInfo().getName());
                serverList.append(", ");
            }
            serverList.setLength(serverList.length() - 2);
            player.sendMessage(ChatColor.GOLD + "You may connect to the following servers at this time: " + serverList.toString());
        } else
        {
            Server server = ProxyServer.getInstance().getServer(args[0]);
            if (server == null)
            {
                player.sendMessage(ChatColor.RED + "The specified server does not exist");
            } else if (server == player.getServer())
            {
                player.sendMessage(ChatColor.RED + "You are already on this server.");
            } else
            {
                player.connect(server);
            }
        }
    }
}