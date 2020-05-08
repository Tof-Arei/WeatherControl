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

import ch.ar.env.commands.CommandTemplate;
import ch.ar.weathercontrol.commands.*;
import ch.ar.weathercontrol.WeatherControl;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author Arei
 */
public class Info  extends CommandTemplate {
    public Info(WeatherControl plugin) {
        super(plugin);
        
        name = "info";
        permission = "wc.cmd.info";
        minArgs = 0;
        help = "/<wc|weathercontrol> <info>";
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("WeatherControl").getConfig();
        
        sender.sendMessage("Outputing the content of WeatherControl config.yml :");
        for (String key : config.getKeys(false)) {
            sender.sendMessage(key + " : " + config.get(key));
        }
        
        /*sender.sendMessage("Outputing the content of WeatherControl config.yml :");
        sender.sendMessage("Verbose mode : " + config.getBoolean("verbose"));
        sender.sendMessage("Rain enabled : " + config.getBoolean("rain-enabled"));
        sender.sendMessage("Storms enabled : " + config.getBoolean("storms-enabled"));
        sender.sendMessage("Rain duration : " + config.getLong("rain-duration"));
        sender.sendMessage("Storms duration : " + config.getLong("storms-duration"));
        sender.sendMessage("Limit method : " + config.getString("limit-method"));
        sender.sendMessage("Custom duration enabled : " + config.getBoolean("custom-duration"));
        sender.sendMessage("Rain frequency : " + config.getDouble("rain-frequency"));
        sender.sendMessage("Storms frequency : " + config.getDouble("storms-frequency"));
        sender.sendMessage("Rain ticks : " + config.getLong("rain-ticks"));
        sender.sendMessage("Storms ticks : " + config.getLong("storms-ticks"));*/
        
        return true;
    }
}
