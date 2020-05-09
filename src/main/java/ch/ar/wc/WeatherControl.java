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
package ch.ar.wc;

import ch.ar.wc.env.event.weather.WeatherListener;
import ch.ar.wc.env.vanilla.VanillaWeatherListener;
import ch.ar.wc.env.commands.WCCommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Arei
 */
public class WeatherControl extends JavaPlugin {
    private static WeatherControl instance = null;
    
    private FileConfiguration config = getConfig();
    
    @Override
    public void onEnable() {
        instance = this;
        config();
        
        getServer().getPluginManager().registerEvents(new VanillaWeatherListener(), this);
        if (config.getBoolean("custom-weather")) {
            getServer().getPluginManager().registerEvents(new WeatherListener(), this);
        }
        getCommand("wc").setExecutor(new WCCommandExecutor(this));
    }
    
    private void config() {
        config.addDefault("verbose", false);
        config.addDefault("rain-enabled", true);
        config.addDefault("storms-enabled", true);
        config.addDefault("lightning-enabled", true);
        config.addDefault("custom-weather", false);
        config.addDefault("rain-duration", 6000);
        config.addDefault("storms-duration", 6000);
        config.addDefault("limit-method", "rand");
        config.addDefault("rain-frequency", 1);
        config.addDefault("storms-frequency", 1);
        config.addDefault("lightning-frequency", 1);
        config.addDefault("rain-ticks", 48000);
        config.addDefault("storms-ticks", 96000);
        config.addDefault("lightning-ticks", 600);
        
        saveDefaultConfig();
    }
    
    @Override
    public void saveConfig() {
        config.options().copyDefaults(true);
        super.saveConfig();
        reloadConfig();
    }
    
    public static WeatherControl getPlugin() {
        return instance;
    }
}
