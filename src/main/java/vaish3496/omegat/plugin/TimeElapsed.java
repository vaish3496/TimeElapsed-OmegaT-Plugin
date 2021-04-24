/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool 
          with fuzzy matching, translation memory, keyword search, 
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2000-2006 Keith Godfrey and Maxym Mykhalchuk
               2010 Volker Berlin
               Home page: http://www.omegat.org/
               Support center: http://groups.yahoo.com/group/OmegaT/

 This file is part of OmegaT.

 OmegaT is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 OmegaT is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **************************************************************************/

package vaish3496.omegat.plugin;

/**
 *
 * @author vaish3496
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.Box;
import javax.swing.Timer;
import org.omegat.core.CoreEvents;
import org.omegat.core.events.IApplicationEventListener;
import org.omegat.core.Core;
import org.omegat.util.Log;
import org.omegat.util.Platform;


public class TimeElapsed implements IApplicationEventListener{

    private static boolean initialized = false;
    static int seconds,minutes,hours,elapsedTime;
    JLabel time_elapsed;
 
    public static void loadPlugins() {
        try {
            // Not initialize in console mode
            if (initialized) {
                throw new RuntimeException("TimeElapsed plugin could be instantiated only once.");
            } else if (Platform.isWebStart()) {
                // Just log it, no error.
                Log.log("TimeElapsed plugin is not available with Java Web Start.");
            } else {
                CoreEvents.registerApplicationEventListener(new TimeElapsed());
            }
        } catch (Throwable ex) {
            String msg = ex.getMessage();
            Log.logErrorRB("LD_ERROR", msg);
            Core.pluginLoadingError(msg);
        } finally {
            initialized = true;
        }
   }
 
    public static void unloadPlugins() {
        // do nothing
    }
 
    @Override
    public void onApplicationStartup() {
        // insert Timer before the last menu (Help menu.)
        JMenuBar mainMenuBar = (JMenuBar) Core.getMainWindow().getMainMenu().getOptionsMenu().getParent();
        mainMenuBar.add(Box.createHorizontalGlue());
        time_elapsed = new JLabel("00:00:00");
        mainMenuBar.add(time_elapsed);
        
        // This is the timer code
        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 1000;
                hours = (elapsedTime/3600000);
                minutes =  (elapsedTime/60000)%60;
                seconds = (elapsedTime/1000)%60;
                String hours_string = String.format("%02d", hours);
                String minutes_string = String.format("%02d", minutes);
                String seconds_string = String.format("%02d", seconds);
                time_elapsed.setText(hours_string+":"+minutes_string+":"+seconds_string);
            }
        });
        
        timer.start();
 
        // remove ApplicationEventListener
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CoreEvents.unregisterApplicationEventListener(TimeElapsed.this);
            }
        });
    }
 
    
    @Override
    public void onApplicationShutdown(){
        // do nothing 
    }

  
    
}
