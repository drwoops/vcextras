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
package me.drwoops.vcextras;

import me.drwoops.vcextras.commands.AdventuringCommand;
import me.drwoops.vcextras.commands.HelpCommand;
import me.drwoops.vcextras.commands.VcCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Vcextras extends JavaPlugin {

    public HashMap<String, VcCommand> subcommands;

    @Override
    public void onEnable() {
        subcommands = new HashMap<String, VcCommand>();
        getCommand("vc").setExecutor(this);
        getCommand("vc").setTabCompleter(this);
        subcommands.put("help", new HelpCommand(this));
        subcommands.put("adventuring", new AdventuringCommand((this)));

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0 && subcommands.containsKey(args[0]))
            return subcommands.get(args[0]).onCommand(sender, command, label, args);
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 0)
            return new ArrayList<String>(subcommands.keySet());
        else if (args.length == 1) {
            ArrayList<String> completions = new ArrayList<String>();
            String prefix = args[0];
            for (String key: subcommands.keySet())
                if (key.startsWith(prefix)) completions.add(key);
            return completions;
        } else {
            String subcmd = args[0];
            if (subcommands.containsKey(subcmd))
                return subcommands.get(subcmd).onTabComplete(sender, command, alias, args);
            else return null;
        }
    }
}
