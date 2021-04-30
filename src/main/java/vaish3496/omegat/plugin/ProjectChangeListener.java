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
import java.io.FileNotFoundException;
import java.io.IOException;
import org.omegat.core.events.IProjectEventListener;


public class ProjectChangeListener implements IProjectEventListener{
 
    
    @Override
    public void onProjectChanged(PROJECT_CHANGE_TYPE eventType) {
        TimeElapsedTracker timeElapsedTracker = new TimeElapsedTracker();
        TimeLog timeLog = TimeLog.getTimeLog();
        switch(eventType){
            case LOAD:
                //do somethinge
                try {
                    timeLog.addJLabel();
                    timeLog.setElapsedTime(timeElapsedTracker.getTimeElapsed()); 
                    timeLog.startTimer();
                } catch (FileNotFoundException e) {
                    e.printStackTrace(System.err);
                } catch (IOException e){
                    e.printStackTrace(System.err);
                }
                break;

            case CLOSE:
                //do something
                try {
                    timeLog.stopTimer();
                    timeElapsedTracker.setTimeElaspsed(timeLog.getElapsedTime());
                    timeLog.removeJLabel();
                    
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
               
             
        }
    }
}
