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
package ch.ar.weathercontrol;

import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 *
 * @author Arei
 */
public class WeatherListener implements Listener {
    private static long lastRain = 0;
    private static long lastStorm = 0;
    
    private FileConfiguration config;
    
    @EventHandler
    public void onRain(WeatherChangeEvent e) {
        config = Bukkit.getServer().getPluginManager().getPlugin("WeatherControl").getConfig();
        
        if (e.toWeatherState()) {
            aboutToRain(e);
        } else {
            aboutToStopRaining(e);
        }
    }
    
    @EventHandler
    public void onStorm(ThunderChangeEvent e) {
        config = Bukkit.getServer().getPluginManager().getPlugin("WeatherControl").getConfig();
        
        if (e.toThunderState()) {
            aboutToStorm(e);
        } else {
            aboutToStopStorming(e);
        }
    }
    
    private void aboutToRain(WeatherChangeEvent e) {
        String limitMethod = config.getString("limit-method");
        boolean rainEnabled = config.getBoolean("rain-enabled");
        int rainFrequency = config.getInt("rain-frequency");
        int rainTicks = config.getInt("rain-ticks");
        
        if (!rainEnabled) {
            e.setCancelled(true);
        } else {
            switch (limitMethod) {
                case "rand":
                    randomRainLimit(e, rainFrequency);
                    break;
                case "ticks":
                    ticksRainLimit(e, rainTicks);
                    break;
            }
        }
    }
    
    private void aboutToStorm(ThunderChangeEvent e) {
        String limitMethod = config.getString("limit-method");
        boolean stormsEnabled = config.getBoolean("storms-enabled");
        int stormsFrequency = config.getInt("storms-frequency");
        int stormsTicks = config.getInt("storms-ticks");

        if (!stormsEnabled) {                
            e.setCancelled(true);
        } else {
            switch (limitMethod) {
                case "rand":
                    randomStormsLimit(e, stormsFrequency);
                    break;
                case "ticks":
                    ticksStormsLimit(e, stormsTicks);
                    break;
            }
        }
    }
    
    private void randomRainLimit(WeatherChangeEvent e, double rainFrequency) {     
        Date now = new Date();
        
        int min = (int) (rainFrequency * 100);
        if (min + (Math.random() * (100 - min)) != 100) {
            e.setCancelled(true);
        } else {
            lastRain = now.getTime();
        }
    }
    
    private void randomStormsLimit(ThunderChangeEvent e, double stormsFrequency) {     
        Date now = new Date();
        
        int min = (int) (stormsFrequency * 100);
        if (min + (Math.random() * (100 - min)) != 100) {
            e.setCancelled(true);
        } else {
            lastStorm = now.getTime();
        }
    }
    
    private void ticksRainLimit(WeatherChangeEvent e, long rainTicks) {
        Date now = new Date();
        if (e.getWorld().isThundering()) {
            if (now.getTime() - lastStorm >= (rainTicks / 20) * 1000) {
                lastStorm = now.getTime();
            } else {
                e.setCancelled(true);
            }
        }
    }
    
    private void ticksStormsLimit(ThunderChangeEvent e, long stormsTicks) {
        Date now = new Date();
        if (e.toThunderState()) {
            if (now.getTime() - lastStorm >= (stormsTicks / 20) * 1000) {
                lastStorm = now.getTime();
            } else {
                e.setCancelled(true);
            }
        }
    }
    
    private void aboutToStopRaining(WeatherChangeEvent e) {
        if (config.getBoolean("custom-duration")) {
            Date now = new Date();
            if (now.getTime() - lastRain < (config.getLong("rain-duration") / 20) * 1000) {
                e.setCancelled(true);
            }
        }
    }
    
    private void aboutToStopStorming(ThunderChangeEvent e) {
        if (config.getBoolean("custom-duration")) {
            Date now = new Date();
            if (now.getTime() - lastStorm < (config.getLong("storms-duration") / 20) * 1000) {
                e.setCancelled(true);
            }
        }
    }
}
