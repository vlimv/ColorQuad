package vlimv.colorquad;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.transitionseverywhere.Transition;
import com.transitionseverywhere.Recolor;
import com.transitionseverywhere.TransitionManager;

public class MainMenu extends Game implements View.OnClickListener{
    public class Dialog_quit extends android.app.Dialog {
        public Dialog_quit(Activity a) {
            super(a);
        }

        public void showDialog(Activity activity){
            final Dialog_quit dialog = new Dialog_quit(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_window_quit);

            Typeface Marske = Typeface.createFromAsset(getAssets(),"fonts/Marske.ttf");
            TextView text = (TextView) dialog.findViewById(R.id.text_quit);
            text.setTypeface(Marske);
            ImageButton btn_yes = (ImageButton) dialog.findViewById(R.id.button_yes);
            btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    quit();
                }
            });
            ImageButton btn_no = (ImageButton) dialog.findViewById(R.id.button_no);
            btn_no.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }


    ImageButton button_play, button_levels, button_love, button_sound;
    TextView title_color, title_quad;
    Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Typeface Marske = Typeface.createFromAsset(getAssets(),"fonts/Marske.ttf");
        doBindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        playMusic = loadMusic();
        button_play = (ImageButton) findViewById(R.id.button_play);
        button_levels = (ImageButton) findViewById(R.id.button_levels);
        button_love = (ImageButton) findViewById(R.id.button_love);
        button_sound = (ImageButton) findViewById(R.id.button_sound);
        title_color = (TextView) findViewById(R.id.title_color);
        title_quad = (TextView) findViewById(R.id.title_quad);
        final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.layout);
        if (playMusic) {
            startService(music);
            isPlayingMusic = true;
            button_sound.setImageResource(R.drawable.sound);
        } else {
            isPlayingMusic = false;
            button_sound.setImageResource(R.drawable.mute);
        }
        button_play.setOnClickListener(this);
        button_levels.setOnClickListener(this);
        button_love.setOnClickListener(this);
        button_sound.setOnClickListener(this);
        title_color.setTypeface(Marske);
        title_quad.setTypeface(Marske);

        anim = AnimationUtils.loadAnimation(this, R.anim.pulsation);
        anim.setRepeatCount(Animation.INFINITE);
        button_play.startAnimation(anim);
        button_levels.startAnimation(anim);
        button_love.startAnimation(anim);
        button_sound.startAnimation(anim);
        numOfStars = new int[120];
        for (int i = 0; i < 120; i++) {
            numOfStars[i] = loadNumOfStars(i + 1);
        }
        final Transition transition_1 = new Recolor();
        final Transition transition_2 = new Recolor();
        transition_1.setDuration(1000);
//        transition_1.addListener(new Transition.TransitionListener() {
//            @Override
//            public void onTransitionStart(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionEnd(Transition transition) {
//                transition_2.setDuration(2000);
//                int k;
//                TransitionManager.beginDelayedTransition(transitionsContainer, transition_2);
//                int c = title_color.getCurrentTextColor();
//                if (c == 0x00ccff) {
//                    k = R.color.pink;
//                } else {
//                    k = R.color.cyan;
//                }
//                title_color.setTextColor(getColor(getApplicationContext(), k));
//            }
//
//            @Override
//            public void onTransitionCancel(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionPause(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionResume(Transition transition) {
//
//            }
//        });
//        transition_2.addListener(new Transition.TransitionListener() {
//            @Override
//            public void onTransitionStart(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionEnd(Transition transition) {
//                int k;
//                TransitionManager.beginDelayedTransition(transitionsContainer, transition);
//                int c = title_color.getCurrentTextColor();
//                if (c == 0x00ccff) {
//                    k = R.color.pink;
//                } else {
//                    k = R.color.cyan;
//                }
//                title_color.setTextColor(getColor(getApplicationContext(), k));
//            }
//
//            @Override
//            public void onTransitionCancel(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionPause(Transition transition) {
//
//            }
//
//            @Override
//            public void onTransitionResume(Transition transition) {
//
//            }
//        });
//        int k;
//        int c = title_color.getCurrentTextColor();
//        if (c == 0x00ccff) {
//            k = R.color.cyan;
//        } else {
//            k = R.color.pink;
//
//        }
//        TransitionManager.beginDelayedTransition(transitionsContainer, transition_1);
//        title_color.setTextColor(getColor(getApplicationContext(), k));

    }
    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }
    private void checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {
            Intent intent = new Intent(this, Levels.class);
            Bundle extras = new Bundle();
            extras.putString("from_where", "menu");
            extras.putInt("level", max_level);
            continueBGMusic = true;
            intent.putExtras(extras);
            startActivity(intent);
            // This is just a normal run
            return;

        } else if (savedVersionCode == DOESNT_EXIST) {
            show_intro();
            // TODO This is a new install (or the user cleared the shared preferences)

        } else if (currentVersionCode > savedVersionCode) {

            // TODO This is an upgrade
        }

        // Update the shared preferences with the current version code
        prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();
    }

    public void onClick(View v) {
        final View vj = v;
        if (v.getId() != R.id.button_sound) {
            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    button_play.setVisibility(View.INVISIBLE);
                    button_sound.setVisibility(View.INVISIBLE);
                    button_levels.setVisibility(View.INVISIBLE);
                    button_love.setVisibility(View.INVISIBLE);
                    switch (vj.getId()) {
                        case R.id.button_play:
                            start_play();
                            break;
                        case R.id.button_levels:
                            show_levels();
                            break;
                        case R.id.button_love:
                            show_credits();
                            break;
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            button_play.startAnimation(anim);
            button_sound.startAnimation(anim);
            button_levels.startAnimation(anim);
            button_love.startAnimation(anim);
        } else {
            if (isPlayingMusic) {
                isPlayingMusic = false;
                saveMusic(false);
                mServ.pauseMusic();
                button_sound.setImageResource(R.drawable.mute);
            } else {
                mServ.resumeMusic();
                isPlayingMusic = true;
                saveMusic(true);
                button_sound.setImageResource(R.drawable.sound);
            }
        }
    }

    void quit() {
        this.finishAffinity();
    }
    public void start_play() {
        checkFirstRun();
    }

    public void show_levels() {
        Intent intent = new Intent(this, ChooseLevel.class);
        continueBGMusic = true;
        startActivity(intent);
    }

    public void show_credits() {
        Intent intent = new Intent(this, Credits.class);
        continueBGMusic = true;
        startActivity(intent);
    }
    public void show_intro() {
        Intent intent = new Intent(this, how_to.class);
        continueBGMusic = true;
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Dialog_quit dialog = new Dialog_quit(this);
        dialog.showDialog(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle", "im in onPause of MM, playMusic" + playMusic);
        doUnbindService();
        playMusic = loadMusic();
        if (!continueBGMusic) {
            mServ.pauseMusic();
            isPlayingMusic = false;
        } else if (playMusic && mServ != null){
            mServ.resumeMusic();
            isPlayingMusic = true;
        } else if (mServ == null) {
            doBindService();
            Intent music = new Intent();
            music.setClass(this,MusicService.class);
            startService(music);
            isPlayingMusic = true;
        }
        continueBGMusic = false;
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        continueBGMusic = false;
        playMusic = loadMusic();
        doBindService();
        if (playMusic && mServ!= null){
            isPlayingMusic = true;
            Log.d("fucking music", "hey, mServ is not null");
            mServ.resumeMusic();
        } else if(!playMusic) {
            isPlayingMusic = false;
        }
    }
}
