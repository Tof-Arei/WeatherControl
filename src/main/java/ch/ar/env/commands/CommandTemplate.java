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
package ch.ar.env.commands;

import ch.ar.weathercontrol.WeatherControl;
import java.util.List;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Arei
 */
public abstract class CommandTemplate {
    protected WeatherControl plugin;
    protected String name;
    protected String permission;
    protected int minArgs = 0;
    protected String help;
    
    protected CommandTemplate(WeatherControl plugin) {
        this.plugin = plugin;
    }
    
    public abstract boolean onCommand(CommandSender sender, List<String> args);

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public int getMinArgs() {
        return minArgs;
    }

    public String getHelp() {
        return help;
    }
}
