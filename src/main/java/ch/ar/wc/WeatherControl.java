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

import ch.ar.wc.listeners.WorldListener;
import ch.ar.wc.listeners.WeatherListener;
import ch.ar.wc.minecraft.listeners.VanillaWeatherListener;
import ch.ar.wc.env.commands.WCCommandExecutor;
import ch.ar.wc.env.WCLogger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
/**
 *
 * @author Arei
 */
public class WeatherControl extends JavaPlugin {
    public static final String NAME = "WeatherControl";
    public static final String SHORTNAME = "WC";
    public static final String VERSION = "1.0.0";
    public static final String MINECRAFT_VERSION = "1.12.2";
    
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
            initCustomWeather();
        }
        getCommand("wc").setExecutor(new WCCommandExecutor(this));
        WCLogger.log(NAME + " version " + VERSION + " started successfully.\n"
                + "Using custom weather : " + config.getBoolean("custom-weather"), WCLogger.Level.INFO);
    }
    
    private void initCustomWeather() {
        worldListener = new WorldListener();
        getServer().getPluginManager().registerEvents(worldListener, this);
        weatherListener = new WeatherListener();
        getServer().getPluginManager().registerEvents(weatherListener, this);
    }
    
    private void config() {
        config.addDefault("verbose", false);
        config.addDefault("verbose-level", 0);
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
        reloadConfig();
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
