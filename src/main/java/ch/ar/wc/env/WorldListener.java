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
import ch.ar.wc.schedules.TrackTime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;

/**
 *
 * @author Arei
 */
public class WorldListener implements Listener {
    @EventHandler
    public void onLoad(WorldLoadEvent e) {
        TrackTime trackTime = new TrackTime(e.getWorld());
        WeatherControl.getPlugin().getWeatherListener().addTrackTime(e.getWorld().getName(), trackTime);
        trackTime.run();
    }
    
    @EventHandler
    public void onUnload(WorldUnloadEvent e) {
        WeatherControl.getPlugin().getWeatherListener().getTrackTime(e.getWorld().getName()).cancel();
        WeatherControl.getPlugin().getWeatherListener().removeTrackTime(e.getWorld().getName());
    }
}
