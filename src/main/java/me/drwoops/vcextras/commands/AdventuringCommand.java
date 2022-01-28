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

import java.util.*;

public class AdventuringCommand extends VcCommand {

    public AdventuringCommand(Vcextras plugin) {
        super(plugin, "list missing biomes for adventuring time advancement");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            NamespacedKey key = NamespacedKey.minecraft("adventure/adventuring_time");
            Advancement adv = Bukkit.getAdvancement(key);
            AdvancementProgress prog = player.getAdvancementProgress(adv);
            Collection<String> rest = prog.getRemainingCriteria();
            if (rest.size() == 0) {
                player.sendMessage("you have completed Adventuring Time");
            } else {
                player.sendMessage("you still need the following biomes:");
                for (String c: rest){
                    player.sendMessage(" - "+c);
                }
            }
            return true;
        } else
            return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
