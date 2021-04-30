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
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.Timer;
import org.omegat.core.Core;
public class TimeLog {
    private static int elapsedTime;
    JMenuBar mainMenuBar = (JMenuBar) Core.getMainWindow().getMainMenu().getOptionsMenu().getParent();
    JLabel time_elapsed = new JLabel("00:00:00");
    
    Timer timer = new Timer(1000, new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime += 1000;
            int hours = (elapsedTime/3600000);
            int minutes = (elapsedTime/60000)%60;
            int seconds = (elapsedTime/1000)%60;
            String hours_string = String.format("%02d", hours);
            String minutes_string = String.format("%02d", minutes);
            String seconds_string = String.format("%02d", seconds);
            time_elapsed.setText(hours_string+":"+minutes_string+":"+seconds_string);
        }
    });
   
    // singleton design pattern
    private TimeLog(){
    }
    
    private static TimeLog _TimeLog;
    
    public static TimeLog getTimeLog(){
        if (_TimeLog == null) {
            _TimeLog = new TimeLog();
        }
        return _TimeLog;
    }
    
    public void startTimer(){
        timer.start();
    }
    
    public void stopTimer(){
        timer.stop();
    }
    
    public void setElapsedTime(int elapsedTime){
        TimeLog.elapsedTime = elapsedTime;
    }
    
    public int getElapsedTime(){
        return elapsedTime;
    }
    
    public void addJLabel(){
        mainMenuBar.add(Box.createHorizontalGlue());
        mainMenuBar.add(time_elapsed);
    }
    
    public void removeJLabel() {
        time_elapsed.setText("");
    }
}
