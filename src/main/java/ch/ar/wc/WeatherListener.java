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

import ch.ar.wc.env.event.weather.Clear;
import ch.ar.wc.env.event.weather.Rain;
import ch.ar.wc.env.event.weather.Storm;
import ch.ar.wc.env.event.weather.Weather;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
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
public class WeatherListener implements Listener {
    private static final Map<String, Weather> hmLastWeathers = new HashMap<>();
    
    private FileConfiguration config;
    
    // Vanilla rain event.
    @EventHandler
    public void onRain(WeatherChangeEvent e) {
        config = Bukkit.getServer().getPluginManager().getPlugin("WeatherControl").getConfig();
        
        // Have we taken weather over ?
        if (config.getBoolean("custom-weather")) {
            // Cancel vanilla rain event.
            e.setCancelled(true);
        } else {
            // Apply vanilla rain settings.
            weatherTurningBad(new Rain(e));
        }
    }
    
    // Vanilla storm event.
    @EventHandler
    public void onStorm(ThunderChangeEvent e) {
        config = Bukkit.getServer().getPluginManager().getPlugin("WeatherControl").getConfig();
        
        // Have we taken weather over ?
        if (config.getBoolean("custom-weather")) {
            // Cancel vanilla storm event.
            e.setCancelled(true);
        } else {
            // Apply vanilla storms settings.
            weatherTurningBad(new Storm(e));
        }
    }
    
    // Vanilla lightning strike event.
    @EventHandler
    public void onThunder(LightningStrikeEvent e) {
        
    }
    
    private void weatherTurningBad(Weather weather) {
        String limitMethod = config.getString("limit-method");
        
        if (!weather.isEnabled()) {
            weather.cancelVEvent();
        } else {
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
    
    private void weatherTurningGood() {
        hmLastWeathers.put("clear", new Clear());
    }
    
    private void randomLimit(Weather weather) {
        int min = (int) (weather.getFrequency() * 100);
        if (min + (Math.random() * (100 - min)) != 100) {
            weather.cancelVEvent();
        } else {
            hmLastWeathers.put(weather.getName(), weather);
        }
    }
    
    private void ticksLimit(Weather weather) {
        if (hmLastWeathers.containsKey(weather.getName())) {
            Date now = new Date();
            if (now.getTime() - weather.getTime() >= (weather.getTicks() / 20) * 1000) {
                hmLastWeathers.put(weather.getName(), weather);
            } else {
                weather.cancelVEvent();
            }
        } else {
            hmLastWeathers.put(weather.getName(), weather);
        }
    }
}
