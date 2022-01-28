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
import org.bukkit.command.TabExecutor;

public abstract class VcCommand implements TabExecutor {
    public final String help;
    public final Vcextras plugin;

    public VcCommand(Vcextras plugin, String help) {
        super();
        this.plugin = plugin;
        this.help = help;
    }
}
