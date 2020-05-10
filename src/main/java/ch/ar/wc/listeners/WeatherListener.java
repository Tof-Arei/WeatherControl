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
package ch.ar.wc.listeners;

import ch.ar.wc.schedules.TrackTime;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.event.Listener;

/**
 *
 * @author Arei
 */
public class WeatherListener implements Listener {
    private final Map<String, TrackTime> hmTrackTimes = new HashMap<>();
    private final Map<String, String> hmOngoingWeathers = new HashMap<>();
    
    public void addTrackTime(String world, TrackTime trackTime) {
        hmTrackTimes.put(world, trackTime);
    }
    
    public TrackTime getTrackTime(String world) {
        return hmTrackTimes.get(world);
    }
    
    public void removeTrackTime(String world) {
        hmTrackTimes.remove(world);
    }
}
