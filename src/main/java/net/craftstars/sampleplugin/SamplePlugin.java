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

package net.craftstars.sampleplugin;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.craftstars.sampleplugin.commands.samplepluginCommand;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class SamplePlugin extends JavaPlugin
{
    public final static Logger logger = Logger.getLogger("Minecraft");
    
    private Configuration configuration;
    private PluginDescriptionFile description;
    
    @Override
    public void onEnable()
    {
        this.configuration = this.getConfiguration();
        this.description = this.getDescription();
        
        this.loadConfiguration();
        
        this.registerCommands();
        
        SamplePlugin.logger.log(Level.INFO, SamplePlugin.bracketize(this.description.getName())+" Plugin v"+this.description.getVersion()+" loaded!");
    }
    
    @Override
    public void onDisable()
    {
        SamplePlugin.logger.log(Level.INFO, SamplePlugin.bracketize(this.description.getName())+" disabled!");
    }
    
    private void registerCommands()
    {
        this.getCommand("sampleplugin").setExecutor(new samplepluginCommand());
    }
    
    private void loadConfiguration()
    {
        this.configuration.load();
        
        try
        {
            File dataFolder = this.getDataFolder();
            
            if (!dataFolder.exists()) dataFolder.mkdirs();
            
            File configFile = new File(dataFolder, "config.yml");
            
            if (!configFile.exists())
            {
                SamplePlugin.logger.info(SamplePlugin.bracketize(this.description.getName())+" Configuration file does not exist. Attempting to create default one...");
                InputStream defaultConfig = this.getClass().getResourceAsStream(File.separator+"config.yml");
                FileWriter out = new FileWriter(configFile);
                for (int i = 0; (i = defaultConfig.read()) > 0;) out.write(i);
                out.flush();
                out.close();
                defaultConfig.close();
                this.configuration.load();
                SamplePlugin.logger.info(SamplePlugin.bracketize(this.description.getName())+" Default configuration created successfully! You can now stop the server and edit plugin/SamplePlugin/config.yml.");
            }
        }
        catch (Exception ex)
        {
            SamplePlugin.logger.warning(SamplePlugin.bracketize(this.description.getName())+" Could not read and/or write config.yml! Continuing with default values!");
        }
    }
    
    public static String bracketize(String str)
    {
        return "["+str+"]";
    }
}