package com.mycompany.myapp;


import net.itca.game.display.MainMenu;

import com.codename1.io.Log;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Display;
import com.codename1.ui.Form;

import userclasses.StateMachine;

public class MyApplication {
   
    private Form current;

    public void init(Object context) {
        // Pro users - uncomment this code to get crash reports sent to you automatically
        /*Display.getInstance().addEdtErrorHandler(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                evt.consume();
                Log.p("Exception in AppName version " + Display.getInstance().getProperty("AppVersion", "Unknown"));
                Log.p("OS " + Display.getInstance().getPlatformName());
                Log.p("Error " + evt.getSource());
                Log.p("Current Form " + Display.getInstance().getCurrent().getName());
                Log.e((Throwable)evt.getSource());
                Log.sendLog();
            }
        });*/
    
    }

    public void start() {
        if(current != null){
            current.show();
            return;
        }
        new StateMachine("/theme");
        MainMenu m = new MainMenu();
        m.show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }
    
    public void destroy() {
    }
}
