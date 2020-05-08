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
import java.util.List;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Arei
 */
public class Rain extends CommandTemplate {
    public Rain(WeatherControl plugin) {
        super(plugin);
        
        name = "rain";
        permission = "wc.cmd.rain";
        minArgs = 2;
        help = "/<wc|weathercontrol> <rain> <set> <true|false>\n"
                + "/<wc|weathercontrol> <rain> <freq> <0..1>";
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        if (args.contains("set") && CommandUtils.isNumeric(args.get(1))) {
            plugin.getConfig().set("rain-enabled", Boolean.parseBoolean(args.get(1)));
            sender.sendMessage("Rain enabled : " + args.get(1));
            plugin.saveConfig();
            return true;
        } else if (args.contains("freq") && CommandUtils.isNumeric(args.get(1))) {
            plugin.getConfig().set("rain-frequency", Double.parseDouble(args.get(1)));
            sender.sendMessage("Rain probability set to " + args.get(1));
            plugin.saveConfig();
            return true;
        } else if (args.contains("dura") && CommandUtils.isNumeric(args.get(1))) {
            plugin.getConfig().set("rain-duration", Long.parseLong(args.get(1)));
            sender.sendMessage("Rain duration set to " + args.get(1));
            plugin.saveConfig();
            return true;
        } else if (args.contains("ticks") && CommandUtils.isNumeric(args.get(1))) {
            plugin.getConfig().set("rain-ticks", Integer.parseInt(args.get(1)));
            sender.sendMessage("Rain ticks set to " + args.get(1));
            plugin.saveConfig();
            return true;
        }
        
        return false;
    }
}
