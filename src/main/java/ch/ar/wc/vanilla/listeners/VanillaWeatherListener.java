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
package ch.ar.wc.vanilla.listeners;

import ch.ar.wc.env.event.WCLogger;
import ch.ar.wc.vanilla.env.event.weather.VanillaWeather;
import ch.ar.wc.vanilla.event.weather.Rain;
import ch.ar.wc.vanilla.event.weather.Storm;
import ch.ar.wc.vanilla.event.weather.LightningStrike;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 *
 * @author Arei
 */
public class VanillaWeatherListener implements Listener {
    private static final Map<String, VanillaWeather> hmLastWeathers = new HashMap<>();
    
    private FileConfiguration config;
    
    @EventHandler
    public void onRain(WeatherChangeEvent e) {
        config = Bukkit.getServer().getPluginManager().getPlugin("WeatherControl").getConfig();
        
        // Have we taken weather over ?
        if (config.getBoolean("custom-weather")) {
            // Cancel the vanilla rain event.
            e.setCancelled(true);
        } else {
            // About to rain ?
            if (e.toWeatherState()) {
                weatherTurningBad(new Rain(e));
            } else if (e.getWorld().hasStorm()) {
                // End of rain.
                WCLogger.log("Rain just stopped.", WCLogger.Level.WCHANGE);
            }
        }
    }
    
    // Vanilla storm event.
    @EventHandler
    public void onStorm(ThunderChangeEvent e) {
        config = Bukkit.getServer().getPluginManager().getPlugin("WeatherControl").getConfig();
        
        // Have we taken weather over ?
        if (config.getBoolean("custom-weather")) {
            // Cancel the vanilla storm event.
            e.setCancelled(true);
        } else {
            // About to storm ?
            if (e.toThunderState()) {
                weatherTurningBad(new Storm(e));
            } else if (e.getWorld().isThundering()) {
                // End of storm.
                WCLogger.log("Storm just stopped.", WCLogger.Level.WCHANGE);
            }
        }
    }
    
    // Vanilla lightning strike event.
    @EventHandler
    public void onLightningStrike(LightningStrikeEvent e) {
        // Have we taken weather over ?
        if (config.getBoolean("custom-weather")) {
            // Cancel vanilla lightning strike event.
            e.setCancelled(true);
        } else {
            Location location = e.getLightning().getLocation();
            String strLocation = String.valueOf(location.getX()) + "/"
                    + String.valueOf(location.getY()) + "/"
                    + String.valueOf(location.getZ());
            WCLogger.log("Lightning attempting to strike at " + strLocation, WCLogger.Level.WEVENT);
            weatherTurningBad(new LightningStrike(e));
        }
    }
    
    private void weatherTurningBad(VanillaWeather weather) {
        String limitMethod = config.getString("limit-method");
        
        if (!weather.isEnabled()) {
            weather.cancel();
        } else {
            WCLogger.log("Weather trying to change to " + weather.getName(), WCLogger.Level.WEVENT);
            
            switch (limitMethod) {
                case "rand":
                    randomLimit(weather);
                    break;
                case "ticks":
                    ticksLimit(weather);
                    break;
            }
        }
    }

    private void randomLimit(VanillaWeather weather) {
        int freq = (int) (weather.getFrequency() * 100);
        int rand = (int) (Math.random() * 100);
        
        if (rand >= 0 && rand <= freq) {
            WCLogger.log("Weather changed to " + weather.getName(), WCLogger.Level.WCHANGE);
            hmLastWeathers.put(weather.getName(), weather);
        } else {
            WCLogger.log("Weather " + weather.getName() + " canceled.", WCLogger.Level.WEVENT);
            weather.cancel();
        }
        WCLogger.log("freq=" + freq + " rand=" + rand, WCLogger.Level.DEBUG);
    }
    
    private void ticksLimit(VanillaWeather weather) {
        if (hmLastWeathers.containsKey(weather.getName())) {
            Date now = new Date();
            if (now.getTime() - weather.getTime() >= (weather.getTicks() / 20) * 1000) {
                WCLogger.log("Weather changed to " + weather.getName(), WCLogger.Level.WCHANGE); 
                hmLastWeathers.put(weather.getName(), weather);
            } else {
                WCLogger.log("Weather " + weather.getName() + " canceled.", WCLogger.Level.WEVENT);
                weather.cancel();
            }
        } else {
            WCLogger.log("Weather changed to " + weather.getName(), WCLogger.Level.WCHANGE);
            hmLastWeathers.put(weather.getName(), weather);
        }
    }
}
