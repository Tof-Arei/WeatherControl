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

import ch.ar.wc.env.WorldListener;
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
    
    private final FileConfiguration config = getConfig();
    
    private VanillaWeatherListener vWeatherListener;
    private WorldListener worldListener;
    private WeatherListener weatherListener;
    
    @Override
    public void onEnable() {
        instance = this;
        
        config();
        
        vWeatherListener = new VanillaWeatherListener();
        getServer().getPluginManager().registerEvents(vWeatherListener, this);
        if (config.getBoolean("custom-weather")) {
            worldListener = new WorldListener();
            getServer().getPluginManager().registerEvents(worldListener, this);
            weatherListener = new WeatherListener();
            getServer().getPluginManager().registerEvents(weatherListener, this);
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
        config.addDefault("rain-frequency", 1.0);
        config.addDefault("storms-frequency", 1.0);
        config.addDefault("lightning-frequency", 1.0);
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

    public VanillaWeatherListener getvWeatherListener() {
        return vWeatherListener;
    }

    public WorldListener getWorldListener() {
        return worldListener;
    }

    public WeatherListener getWeatherListener() {
        return weatherListener;
    }
}
