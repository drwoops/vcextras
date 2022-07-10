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
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.bukkit.Bukkit.advancementIterator;


public class TodoCommand extends VcCommand {

    public TodoCommand(Vcextras plugin) {
        super(plugin, "list missing criteria for an advancement.");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length != 2) return false;
            if (!plugin.getConfig().getBoolean("subcommands.todo.allow-all") &&
                    !plugin.getConfig().getStringList("subcommands.todo.allowed").contains(args[1]))
            {
                player.sendMessage("you do not have /todo permission for this advancement");
                return true;
            }
            NamespacedKey key = NamespacedKey.fromString(args[1], plugin);
            Advancement adv = Bukkit.getAdvancement(key);
            if (adv == null) {
                player.sendMessage("no such advancement");
                return true;
            }
            AdvancementProgress prog = player.getAdvancementProgress(adv);
            Collection<String> rest = prog.getRemainingCriteria();
            if (rest.size() == 0) {
                player.sendMessage("you have completed " + key.toString());
            } else {
                player.sendMessage("you still need the following criteria:");
                for (String c: rest){
                    player.sendMessage(" - " + c);
                }
            }
            return true;
        } else
            return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        boolean allow_all = plugin.getConfig().getBoolean("subcommands.todo.allow-all");
        ArrayList<String> completions = new ArrayList<String>();
        String uadv = (args.length > 1) ? args[1] : null;
        if (allow_all) {
            Iterator<Advancement> ai = advancementIterator();
            while (ai.hasNext()) {
                Advancement a = ai.next();
                String name = a.getKey().toString();
                if (uadv == null || name.contains(uadv)) {
                    completions.add(name);
                }
            }
        } else {
            List<String> allowed = plugin.getConfig().getStringList("subcommands.todo.allowed");
            for (String a: allowed) {
                if (uadv == null || a.contains(uadv)) {
                    completions.add(a);
                }
            }
        }
        return completions;
    }
}
