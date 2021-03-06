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
import javax.swing.SwingUtilities;
import org.omegat.core.CoreEvents;
import org.omegat.core.events.IApplicationEventListener;


public class TimeElapsed implements IApplicationEventListener{
     
    public static void loadPlugins() {
        
        CoreEvents.registerApplicationEventListener(new TimeElapsed());
        CoreEvents.registerProjectChangeListener(new ProjectChangeListener());
   }
 
    public static void unloadPlugins() {
        // do nothing
    }
 
    @Override
    public void onApplicationStartup() {
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
