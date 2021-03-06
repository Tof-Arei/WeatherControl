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
package ch.ar.wc.minecraft.event.weather;

import ch.ar.wc.minecraft.env.event.weather.VanillaWeather;
import org.bukkit.event.weather.ThunderChangeEvent;

/**
 *
 * @author Arei
 */
public class Storm extends VanillaWeather {
    public Storm(ThunderChangeEvent vEvent) {
        super("Storm", "storms", vEvent);
    }
    
    @Override
    public void cancel() {
        ((ThunderChangeEvent) vEvent).setCancelled(true);
    }
}
