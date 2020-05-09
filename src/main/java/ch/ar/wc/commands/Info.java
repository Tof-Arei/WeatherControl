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
package ch.ar.wc.commands;

import ch.ar.wc.env.commands.CommandTemplate;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author Arei
 */
public class Info  extends CommandTemplate {
    public Info() {
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
        
        return true;
    }
}
