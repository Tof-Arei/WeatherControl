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
package ch.ar.wc.schedules;

import ch.ar.wc.env.Schedule;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.World;

/**
 *
 * @author Arei
 */
public class TrackTime extends Schedule {
    private long previousTicks = 0;
    private long day = 0;
    private long todayTicks = 0;
    
    public TrackTime(World world) {
        super(world);
    }
    
    private synchronized void trackTime() {
        long currenTicks = world.getFullTime();
        day = currenTicks / 24000;
        todayTicks = currenTicks - (24000 * day);
        previousTicks = currenTicks;
    }
    
    @Override
    public void run() {
        while (!isCancelled()) {
            trackTime();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TrackTime.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public long getPreviousTicks() {
        return previousTicks;
    }

    public long getDay() {
        return day;
    }

    public long getTodayTicks() {
        return todayTicks;
    }
}
