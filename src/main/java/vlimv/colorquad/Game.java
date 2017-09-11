package vlimv.colorquad;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by HP on 07.02.2017.
 */
public class Game extends AppCompatActivity {
    SharedPreferences sPref, musicsPref, starsPref;
    static int max_level = 1;
    static int cur_level = 1;
    public boolean mIsBound = false;
    public boolean isPlayingMusic = true;
    public boolean continueBGMusic = false;
    public static boolean playMusic = true;
    public static int numOfStars[];
    static MusicService mServ;
    private ServiceConnection Scon = new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };
    public void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    public void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    void saveMusic(boolean playMusic) {
        musicsPref = getSharedPreferences("MUSIC", MODE_PRIVATE);
        SharedPreferences.Editor ed = musicsPref.edit();
        ed.clear();
        ed.putBoolean("MUSIC", playMusic);
        ed.apply();
    }

    boolean loadMusic() {
        musicsPref = getSharedPreferences("MUSIC", MODE_PRIVATE);
        boolean p = musicsPref.getBoolean("MUSIC", true);
        return p;
    }
    public void saveNumOfStars(int level, int s) {
        starsPref = getSharedPreferences("level " + level, MODE_PRIVATE);
        SharedPreferences.Editor ed = starsPref.edit();
        ed.clear();
        ed.putInt("level " + level, s);
        ed.apply();
    }
    public int loadNumOfStars(int level) {
        starsPref = getSharedPreferences("level " + level, MODE_PRIVATE);
        int numOfStars = starsPref.getInt("level " + level, 0);
        return numOfStars;
    }
}
