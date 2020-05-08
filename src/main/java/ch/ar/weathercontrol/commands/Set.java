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
import ch.ar.env.commands.CommandTemplate;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author Arei
 */
public class Set extends CommandTemplate {
    public Set(WeatherControl plugin) {
        super(plugin);
        
        name = "set";
        permission = "wc.cmd.set";
        minArgs = 2;
        help = "/<wc|weathercontrol> <set> <variable> <value>";
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        FileConfiguration config = plugin.getConfig();
        
        if (config.contains(args.get(0))) {
            if (config.isBoolean(args.get(0))) {
                config.set(args.get(0), Boolean.parseBoolean(args.get(1)));
            } else if (config.isDouble(args.get(0))) {
                config.set(args.get(0), Double.parseDouble(args.get(1)));
            } else if (config.isInt(args.get(0))) {
                config.set(args.get(0), Integer.parseInt(args.get(1)));
            } else {
                config.set(args.get(0), args.get(1));
            }
            
            sender.sendMessage("Storms probability set to " + args.get(0));
            plugin.saveConfig();
            
            return true;
        }

        return false;
    }
}