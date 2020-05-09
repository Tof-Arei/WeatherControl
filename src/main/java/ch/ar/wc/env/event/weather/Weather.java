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
package ch.ar.wc.env.event.weather;

import ch.ar.wc.WeatherControl;
import ch.ar.wc.env.event.WCEvent;
import ch.ar.wc.env.event.WeatherLogger;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author Arei
 */
public abstract class Weather extends WCEvent {    
    private final String name;
    private final String prefix;
    
    private final boolean enabled;
    private final double frequency;
    private final int ticks;
    private final int duration;
    
    private final World world;

    public Weather(String name, String prefix, World world) {
        this.name = name;
        this.prefix = prefix;
        
        FileConfiguration config = WeatherControl.getPlugin().getConfig();
        
        enabled = config.getBoolean(prefix + "-enabled");
        frequency = config.getDouble(prefix + "-frequency");
        ticks = config.getInt(prefix + "-ticks");
        duration = config.getInt(prefix + "-frequency");
        
        this.world = world;
        
        WeatherLogger.log(this);
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public double getFrequency() {
        return frequency;
    }

    public int getTicks() {
        return ticks;
    }

    public int getDuration() {
        return duration;
    }
    
    public World getWorld() {
        return world;
    }
}
