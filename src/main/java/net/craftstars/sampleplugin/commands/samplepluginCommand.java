/*
 * This file is part of SamplePlugin.
 * 
 * SamplePlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * SamplePlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with SamplePlugin.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.craftstars.sampleplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class samplepluginCommand implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
    {
        if (args.length >= 1)
        {
            if (args[0].equals("invalid"))
            {
                // Simulate invalid command - return false
                // This causes the game client to render the 'usage' field in the plugin.yml
                return false;
            }
        }
        
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            
            player.sendMessage("You used the SamplePlugin command! Woot!");
        }
        else
        {
            // Maybe the command came from console?
            sender.sendMessage("Not sure who you are! No console support!");
        }
        
        return true;
    }
    
}