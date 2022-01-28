/*
 Copyright (c) 2021 by drwoops <thedrwoops@gmail.com>

 This file is part of Vcextras.

 Vcextras is free software: you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Vcextras is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with Foobar.  If not, see <https://www.gnu.org/licenses/>.
*/
package me.drwoops.vcextras.commands;

import me.drwoops.vcextras.Vcextras;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand extends VcCommand {

    public HelpCommand(Vcextras plugin) {
        super(plugin, "show info on 1 or all subcommands");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                for (String key : plugin.subcommands.keySet()) {
                    player.sendMessage(key+": "+plugin.subcommands.get(key).help);
                }
                return true;
            } if (args.length == 2) {
                String key = args[1];
                if (plugin.subcommands.containsKey(key)) {
                    player.sendMessage(key+": "+plugin.subcommands.get(key).help);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1)
            return new ArrayList<String>(plugin.subcommands.keySet());
        else if (args.length == 2) {
            ArrayList<String> completions = new ArrayList<String>();
            String prefix = args[1];
            for (String key: plugin.subcommands.keySet())
                if (key.startsWith(prefix)) completions.add(key);
            return completions;
        } else
            return null;
    }
}
