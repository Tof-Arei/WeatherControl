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
package ch.ar.wc.env;

import ch.ar.wc.WeatherControl;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 *
 * @author Arei
 */
public class WCLogger {
    public static void log(String message, Level level) {
        if (WeatherControl.getPlugin().getConfig().getBoolean("verbose") && WeatherControl.getPlugin().getConfig().getInt("verbose-level") >= level.getLevel()) {
            message = "[" + WeatherControl.SHORTNAME + "][" + level.getCode() + "] " + message;
            
            switch (level) {
                case ERROR:
                    Bukkit.getLogger().log(java.util.logging.Level.SEVERE, message);
                    break;
                case WARNING:
                    Bukkit.getLogger().warning(message);
                    break;
                default:
                    Bukkit.getLogger().info(message);
            }   
            
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("wc.verbose")) {
                    player.sendMessage(message);
                }
            }
        }
    }
    
    public enum Level {
        ERROR(0, "ERR"),
        WARNING(1, "WRN"),
        INFO(2, "INFO"),
        WCHANGE(3, "WCHNG"),
        WEVENT(4, "WEVT"),
        DEBUG(5, "DBG");
        
        private final int level;
        private final String code;
        
        private Level(int level, String code) {
            this.level = level;
            this.code = code;
        }

        public int getLevel() {
            return level;
        }

        public String getCode() {
            return code;
        }
    }
}
