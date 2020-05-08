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
public class Method  extends CommandTemplate {
    public Method(WeatherControl plugin) {
        super(plugin);
        
        name = "method";
        permission = "wc.cmd.method";
        minArgs = 1;
        help = "/<wc|weathercontrol> <method> <rand|ticks>";
    }

    @Override
    public boolean onCommand(CommandSender sender, List<String> args) {
        if (args.contains("rand") || args.contains("ticks")) {
            plugin.getConfig().set("limit-method", args.get(0));
            sender.sendMessage("Limit method : " + args.get(0));
            plugin.saveConfig();
            return true;
        } else {
            return false;
        }
    }
}
