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
package ch.ar.wc.env.event;

import ch.ar.wc.WeatherControl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author Arei
 */
public class WCLogger {
    public static final int ERROR = 0;
    public static final int WARNING = 1;
    public static final int WCHANGE = 2;
    public static final int WEVENT = 3;
    public static final int DEBUG = 4;
    
    public static void log(String message, int level) {
        if (WeatherControl.getPlugin().getConfig().getInt("verbose-level") >= level) {
            message = "[" + WeatherControl.SHORTNAME + "] " + message;
            Bukkit.getLogger().info(message);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("wc.verbose")) {
                    player.sendMessage(message);
                }
            }
        }
    }
}
