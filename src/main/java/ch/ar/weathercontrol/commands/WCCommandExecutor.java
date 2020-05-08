/*
 *                GLWT(Good Luck With That) Public License
 *                  Copyright (c) Everyone, except Author
 * 
 * Everyone is permitted to copy, distribute, modify, merge, sell, publish,
 * sublicense or whatever they want with this software but at their OWN RISK.
 * 
 *                             Preamble
 * 
 * The author has absolutely no clue what the code in this project does.
 * It might just work or not, there is no third option.
 * 
 * 
 *                 GOOD LUCK WITH THAT PUBLIC LICENSE
 *    TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION, AND MODIFICATION
 * 
 *   0. You just DO WHATEVER YOU WANT TO as long as you NEVER LEAVE A
 * TRACE TO TRACK THE AUTHOR of the original product to blame for or hold
 * responsible.
 * 
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 * 
 * Good luck and Godspeed.
 */
package ch.ar.weathercontrol.commands;

import ch.ar.weathercontrol.WeatherControl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Arei
 */
public class WCCommandExecutor implements CommandExecutor {
    private final WeatherControl plugin;
    protected HashMap<String, CommandTemplate> hmCommands = new HashMap<>();
    
    public WCCommandExecutor(WeatherControl plugin) {
        this.plugin = plugin;
        registerCommands();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED.toString() + "You must be a player to use this command.");
            return false;
        }
        
        ArrayList<String> arg = new ArrayList<>(Arrays.asList(args));

        CommandTemplate cmd = null;
        if (!arg.isEmpty() && hmCommands.containsKey(arg.get(0))) {
            cmd = hmCommands.get(arg.get(0));
            arg.remove(0);
            
            if (arg.size() < cmd.getMinArgs()) {
                sender.sendMessage(ChatColor.RED.toString() + "Missing arguments for the /wc <" + cmd.getName() + ">. Min args : " + cmd.getMinArgs() + "\n"
                                  + ChatColor.WHITE.toString() + "/wc <" + cmd.getName() + "> help : " + cmd.getHelp());
                return false;
            }
        } else {
            sender.sendMessage(ChatColor.RED.toString() + "Error using the /wc command.");
            return false;
        }

        if (!sender.hasPermission(cmd.getPermission())) {
            sender.sendMessage(ChatColor.RED.toString() + "You don't have permission to use this command !");
            return true;
        }

        return cmd.onCommand(sender, arg);
    }
    
    private void registerCommands() {
        addCommand(new Info(plugin));
        addCommand(new Verbose(plugin));
        addCommand(new Rain(plugin));
        addCommand(new Storms(plugin));
        addCommand(new CustomDuration(plugin));
        addCommand(new Method(plugin));
    }
    
    private void addCommand(CommandTemplate command) {
        hmCommands.put(command.getName(), command);
    }
}
