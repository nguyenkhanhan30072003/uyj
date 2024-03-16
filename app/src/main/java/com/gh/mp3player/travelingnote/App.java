package com.gh.mp3player.travelingnote;

import android.app.Application;


public class App extends Application {
   private static App instance;
   public Storage storage;

    @Override
    public void onCreate() {
        super.onCreate();
        storage=new Storage();
        instance=this;
    }

    public Storage getStorage() {
        return storage;
    }

    public static App getInstance() {
        return instance;
    }
}