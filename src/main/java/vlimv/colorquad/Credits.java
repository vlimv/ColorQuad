package vlimv.colorquad;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Credits extends Game implements View.OnClickListener{

    TextView text_background, text_subtle, text_music, text_frankum, text_icons, text_yesset, text_coding, text_alima;
    ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        Typeface Marske = Typeface.createFromAsset(getAssets(),"fonts/Marske.ttf");
        TextView title_color = (TextView)findViewById(R.id.title_color);
        TextView title_quad = (TextView)findViewById(R.id.title_quad);
        text_background = (TextView) findViewById(R.id.text_bg);
        text_background.setOnClickListener(this);
        text_subtle = (TextView) findViewById(R.id.subtle_patterns);
        text_subtle.setOnClickListener(this);
        text_music = (TextView) findViewById(R.id.text_music);
        text_music.setOnClickListener(this);
        text_frankum = (TextView) findViewById(R.id.text_frankum);
        text_frankum.setOnClickListener(this);
        text_icons = (TextView) findViewById(R.id.text_icons);
        text_icons.setOnClickListener(this);
        text_yesset = (TextView) findViewById(R.id.text_yesset);
        text_yesset.setOnClickListener(this);
        text_coding = (TextView) findViewById(R.id.text_coding);
        text_coding.setOnClickListener(this);
        text_alima = (TextView) findViewById(R.id.text_alima);
        text_alima.setOnClickListener(this);
        btn_back = (ImageButton) findViewById(R.id.button_back);
        btn_back.setOnClickListener(this);
        title_color.setTypeface(Marske);
        title_quad.setTypeface(Marske);
        text_background.setTypeface(Marske);
        text_subtle.setTypeface(Marske);
        text_music.setTypeface(Marske);
        text_frankum.setTypeface(Marske);
        text_icons.setTypeface(Marske);
        text_yesset.setTypeface(Marske);
        text_coding.setTypeface(Marske);
        text_alima.setTypeface(Marske);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_back) {
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
        } else {
            int random = (int) (Math.random() * 6);
            TextView t = (TextView) v;
            switch (random) {
                case 0:
                    t.setTextColor(Color.parseColor("#ff6600"));
                    break;
                case 1:
                    t.setTextColor(Color.parseColor("#9900cc"));
                    break;
                case 2:
                    t.setTextColor(Color.parseColor("#00ff00"));
                    break;
                case 3:
                    t.setTextColor(Color.parseColor("#fc9c3f"));
                    break;
                case 4:
                    t.setTextColor(Color.parseColor("#bc8bcc"));
                    break;
                case 5:
                    t.setTextColor(Color.parseColor("#79ff78"));
                    break;
            }
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
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
        Log.d("lifecycle", "im in onResume of MM, playMusic " + playMusic);
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
