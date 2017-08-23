package vlimv.colorquad;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by HP on 06.02.2017.
 */
public class Levels extends Game implements View.OnClickListener, View.OnLongClickListener {
    public class Dialog extends android.app.Dialog {
        public Dialog(Activity a, int s) {
            super(a);
            numberOfStars = s;
        }
        int numberOfStars;
        ImageView stars[] = new ImageView[3];
        public void showDialog(Activity activity){
            final Dialog dialog = new Dialog(activity, numberOfStars);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_window);
            Typeface Marske = Typeface.createFromAsset(getAssets(), "fonts/Marske.ttf");
            TextView text = (TextView) dialog.findViewById(R.id.text);
            int random = (int) (Math.random() * 5 + 1.0);
            int random_color = (int) (Math.random() * 5 + 1.0);
            switch (random_color) {
                case 1:
                    text.setTextColor(getColor(getApplicationContext(), R.color.orange));
                    break;
                case 2:
                    text.setTextColor(getColor(getApplicationContext(), R.color.green));
                    break;
                case 3:
                    text.setTextColor(getColor(getApplicationContext(), R.color.purple));
                    break;
                case 4:
                    text.setTextColor(getColor(getApplicationContext(), R.color.light_purple));
                    break;
                case 5:
                    text.setTextColor(getColor(getApplicationContext(), R.color.light_green));
            }
            if (numberOfStars == 1) {
                switch (random) {
                    case 1:
                        text.setText(R.string.one_star_text1);
                        break;
                    case 2:
                        text.setText(R.string.one_star_text2);
                        break;
                    case 3:
                        text.setText(R.string.one_star_text3);
                        break;
                    case 4:
                        text.setText(R.string.one_star_text4);
                        break;
                    case 5:
                        text.setText(R.string.one_star_text5);
                        break;
                }
            } else if (numberOfStars == 2) {
                switch (random) {
                    case 1:
                        text.setText(R.string.two_star_text1);
                        break;
                    case 2:
                        text.setText(R.string.two_star_text2);
                        break;
                    case 3:
                        text.setText(R.string.two_star_text3);
                        break;
                    case 4:
                        text.setText(R.string.two_star_text4);
                        break;
                    case 5:
                        text.setText(R.string.two_star_text5);
                        break;
                }
            } else if (numberOfStars == 3) {
                switch (random) {
                    case 1:
                        text.setText(R.string.three_star_text1);
                        break;
                    case 2:
                        text.setText(R.string.three_star_text2);
                        break;
                    case 3:
                        text.setText(R.string.three_star_text3);
                        break;
                    case 4:
                        text.setText(R.string.three_star_text4);
                        break;
                    case 5:
                        text.setText(R.string.three_star_text5);
                        break;
                }
            }
            text.setTypeface(Marske);
            stars[0] = (ImageView)dialog.findViewById(R.id.star1);
            stars[1] = (ImageView)dialog.findViewById(R.id.star2);
            stars[2] = (ImageView)dialog.findViewById(R.id.star3);
            ImageButton btn_menu = (ImageButton) dialog.findViewById(R.id.btn_menu);
            btn_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    return_menu();
                }
            });
            ImageButton btn_next = (ImageButton) dialog.findViewById(R.id.btn_next);
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    next_btn();
                }
            });
            ImageButton btn_again = (ImageButton) dialog.findViewById(R.id.btn_again);
            btn_again.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialog.dismiss();
                    restart();
                }
            });
            for (int i = 0; i < numberOfStars; i++) {
                stars[i].setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_stars);
                stars[i].startAnimation(anim);
            }
            dialog.show();
        }
    }
    public class Dialog_last_level extends android.app.Dialog {
        public Dialog_last_level(Activity a, int s) {
            super(a);
            numberOfStars = s;
        }
        int numberOfStars;
        ImageView stars[] = new ImageView[3];
        public void showDialog(Activity activity){
            final Dialog_last_level dialog = new Dialog_last_level(activity, numberOfStars);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_window);

            Typeface Marske = Typeface.createFromAsset(getAssets(), "fonts/Marske.ttf");
            TextView text = (TextView) dialog.findViewById(R.id.text);
            text.setText(R.string.last_level);
            text.setTypeface(Marske);
            stars[0] = (ImageView)dialog.findViewById(R.id.star1);
            stars[1] = (ImageView)dialog.findViewById(R.id.star2);
            stars[2] = (ImageView)dialog.findViewById(R.id.star3);
            ImageButton btn_menu = (ImageButton) dialog.findViewById(R.id.btn_menu);
            btn_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    return_menu();
                }
            });
            ImageButton btn_next = (ImageButton) dialog.findViewById(R.id.btn_next);
            btn_next.setImageResource(R.drawable.love);
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    show_credits();
                }
            });
            ImageButton btn_again = (ImageButton) dialog.findViewById(R.id.btn_again);
            btn_again.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialog.dismiss();
                    restart();
                }
            });
            for (int i = 0; i < numberOfStars; i++) {
                stars[i].setVisibility(View.VISIBLE);
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_stars);
                stars[i].startAnimation(anim);
            }
            dialog.show();
        }
    }
    void show_credits() {
        Intent i = new Intent(this, Credits.class);
        startActivity(i);
    }

    void next_btn() {
        Intent intent = new Intent(this, Levels.class);
        cur_level++;
        saveLevel(cur_level);
        Bundle extras = new Bundle();
        extras.putString("from_where", "choose_level");
        extras.putInt("level", cur_level);
        intent.putExtras(extras);
        continueBGMusic = true;
        startActivity(intent);
    }

    void return_menu() {
        Intent intent = new Intent(this, MainMenu.class);
        saveLevel(cur_level);
        saveLevel(max_level);
        continueBGMusic = true;
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    void show_intro() {
        Intent intent = new Intent(this, how_to.class);
        continueBGMusic = true;
        startActivity(intent);
    }
    void show_levels() {
        Intent intent = new Intent(this, ChooseLevel.class);
        continueBGMusic = true;
        startActivity(intent);
    }

    void restart() {
        continueBGMusic = true;
        for (int i = START_ROW; i <= START_ROW + CUR_ROWS - 1; i++) {
            for (int j = START_COLUMN; j <= START_COLUMN + CUR_COLUMNS - 1; j++) {
                if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                    int colorFrom;
                    states[i][j] = 0;
                    buttons[i][j].setImageResource(R.drawable.rectangle);
                    buttons[i][j].setEnabled(false);
                    if (buttons[i][j].getBackground() != null) {
                        colorFrom = ((ColorDrawable)buttons[i][j].getBackground()).getColor();
                    } else {
                        colorFrom = R.color.transparent;
                    }
                    int colorTo = R.color.transparent;
                    ObjectAnimator anim = ObjectAnimator.ofObject(buttons[i][j], "backgroundColor", new ArgbEvaluator(), colorFrom, colorTo);
                    anim.setDuration(1500).start();

                    anim.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            for (int i = START_ROW; i <= START_ROW + CUR_ROWS - 1; i++) {
                                for (int j = START_COLUMN; j <= START_COLUMN + CUR_COLUMNS - 1; j++) {
                                    if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                                        buttons[i][j].setEnabled(true);
                                        buttons[i][j].setBackgroundColor(getColor(getApplicationContext(), R.color.transparent));
                                    }
                                }
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }
            }
        }
    }

    public class Dialog_2 extends android.app.Dialog {
        public Dialog_2(Activity a) {
            super(a);
        }

        public void showDialog(Activity activity){
            final Dialog_2 dialog = new Dialog_2(activity);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme_fade;
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_window_2);
            ImageButton btn_menu = (ImageButton) dialog.findViewById(R.id.button_home);
            btn_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    return_menu();
                }
            });
            final ImageButton btn_sound = (ImageButton) dialog.findViewById(R.id.button_sound);
            if (playMusic) {
                isPlayingMusic = true;
                btn_sound.setImageResource(R.drawable.sound);
            } else {
                isPlayingMusic = false;
                btn_sound.setImageResource(R.drawable.mute);
            }
            btn_sound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isPlayingMusic) {
                        isPlayingMusic = false;
                        saveMusic(false);
                        mServ.pauseMusic();
                        btn_sound.setImageResource(R.drawable.mute);
                    } else {
                        mServ.resumeMusic();
                        isPlayingMusic = true;
                        saveMusic(true);
                        btn_sound.setImageResource(R.drawable.sound);
                    }
                }
            });
            ImageButton btn_levels = (ImageButton) dialog.findViewById(R.id.button_levels);
            btn_levels.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    show_levels();
                }
            });
            ImageButton btn_restart = (ImageButton) dialog.findViewById(R.id.button_restart);
            btn_restart.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dialog.dismiss();
                    restart();
                }
            });
            dialog.show();
        }
    }
    public class Dialog_teach_brown extends android.app.Dialog {
        public Dialog_teach_brown(Activity a) {
            super(a);
        }
        public void showDialog(Activity activity){
            final Dialog_teach_brown dialog = new Dialog_teach_brown(activity);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_teach_brown);

            Typeface Marske = Typeface.createFromAsset(getAssets(), "fonts/Marske.ttf");
            TextView text = (TextView) dialog.findViewById(R.id.text_1);
            if (Locale.getDefault().getLanguage().equals("ru")) {
                SpannableString text_spannable = new SpannableString(text.getText().toString());
                text_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#663300")), 21, 31, 0);
                text.setText(text_spannable);
            } else {
                SpannableString text_spannable = new SpannableString(text.getText().toString());
                text_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#663300")), 28, 33, 0);
                text.setText(text_spannable);
            }
            text.setTypeface(Marske);
            ImageButton btn_no = (ImageButton) dialog.findViewById(R.id.button_no);
            btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    return_menu();
                }
            });
            ImageButton btn_yes = (ImageButton) dialog.findViewById(R.id.button_yes);
            btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    public class Dialog_teach_pink_and_cyan extends android.app.Dialog {
        public Dialog_teach_pink_and_cyan(Activity a) {
            super(a);
        }
        public void showDialog(Activity activity){
            final Dialog_teach_pink_and_cyan dialog = new Dialog_teach_pink_and_cyan(activity);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_teach_pink_and_cyan);

            Typeface Marske = Typeface.createFromAsset(getAssets(), "fonts/Marske.ttf");
            TextView text = (TextView) dialog.findViewById(R.id.text_1);
            SpannableString text_spannable = new SpannableString(text.getText().toString());
            if (Locale.getDefault().getLanguage().equals("ru")) {
                    text_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0066")), 13, 21, 0);
                    text_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ccff")), 23, 31, 0);
                    text_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ffff99")), 34, 48, 0);
            } else {
                text_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0066")), 13, 17, 0);
                text_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ccff")), 19, 23, 0);
                text_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ffff99")), 28, 40, 0);
            }
            text.setText(text_spannable);
            text.setTypeface(Marske);
            ImageButton btn_no = (ImageButton) dialog.findViewById(R.id.button_no);
            btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    return_menu();
                }
            });
            ImageButton btn_yes = (ImageButton) dialog.findViewById(R.id.button_yes);
            btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    public class Dialog_level_36 extends android.app.Dialog {
        public Dialog_level_36(Activity a, int i) {
            super(a);
            id = i;
        }
        int id;
        public void showDialog(Activity activity){
            final Dialog_level_36 dialog = new Dialog_level_36(activity, id);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_window_quit);

            Typeface Marske = Typeface.createFromAsset(getAssets(), "fonts/Marske.ttf");
            TextView text = (TextView) dialog.findViewById(R.id.text_quit);
            text.setText(id);
            text.setTypeface(Marske);
            ImageButton btn_no = (ImageButton) dialog.findViewById(R.id.button_no);
            btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    return_menu();
                }
            });
            ImageButton btn_yes = (ImageButton) dialog.findViewById(R.id.button_yes);
            btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
    public class Dialog_teach_light extends android.app.Dialog {
        public Dialog_teach_light(Activity a) {
            super(a);
        }
        public void showDialog(Activity activity){
            final Dialog_teach_light dialog = new Dialog_teach_light(activity);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_teach_light_colors);
            Typeface Marske = Typeface.createFromAsset(getAssets(), "fonts/Marske.ttf");
            TextView text = (TextView) dialog.findViewById(R.id.text_1);
            ImageView img = (ImageView) dialog.findViewById(R.id.light_orange);
            SpannableString text_spannable = new SpannableString(text.getText().toString());
            if (Locale.getDefault().getLanguage().equals("ru")) {
                text_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#fc9c3f")), 38, 55, 0);
            } else {
                text_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#fc9c3f")), 36, 48, 0);
            }
            text.setText(text_spannable);

            text.setTypeface(Marske);
            ImageButton btn_no = (ImageButton) dialog.findViewById(R.id.button_no);
            btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    return_menu();
                }
            });
            ImageButton btn_yes = (ImageButton) dialog.findViewById(R.id.button_yes);
            btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
    public class Dialog_teach_other_light extends android.app.Dialog {
        public Dialog_teach_other_light(Activity a) {
            super(a);
        }
        public void showDialog(Activity activity){
            final Dialog_teach_other_light dialog = new Dialog_teach_other_light(activity);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.dialog_teach_other_light);

            Typeface Marske = Typeface.createFromAsset(getAssets(), "fonts/Marske.ttf");
            TextView text = (TextView) dialog.findViewById(R.id.text);
            SpannableString text_spannable = new SpannableString(text.getText().toString());
            if (Locale.getDefault().getLanguage().equals("ru")) {
                text_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#bc8bcc")), 20, 37, 0);
                text_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#79ff78")), 40, 54, 0);
            } else {
                text_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#bc8bcc")), 23, 35, 0);
                text_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#79ff78")), 40, 51, 0);
            }
            text.setText(text_spannable);
            text.setTypeface(Marske);
            ImageButton btn_no = (ImageButton) dialog.findViewById(R.id.button_no);
            btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    return_menu();
                }
            });
            ImageButton btn_yes = (ImageButton) dialog.findViewById(R.id.button_yes);
            btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }
    static final int MAX_ROWS = 11;
    static final int MAX_COLUMNS = 9;
    static final int NUM_OF_LEVELS = 120;
    int CUR_ROWS = 11;
    int CUR_COLUMNS = 9;
    int CUR_MIDDLE_COLORS = 20;
    int START_ROW = 0;
    int START_COLUMN = 0;
    ImageButton buttons[][] = new ImageButton[11][9];
    ImageView hint;
    int minSteps[] = new int [NUM_OF_LEVELS];
    int states[][] = new int[11][9];
    int sides[] = new int[4];
    TextView text;
    RelativeLayout r_layout;
    ImageButton button_pause, button_question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
        doBindService();
        playMusic = loadMusic();
        buttons[0][0] = (ImageButton) findViewById(R.id.button00);
        buttons[0][1] = (ImageButton) findViewById(R.id.button01);
        buttons[0][2] = (ImageButton) findViewById(R.id.button02);
        buttons[0][3] = (ImageButton) findViewById(R.id.button03);
        buttons[0][4] = (ImageButton) findViewById(R.id.button04);
        buttons[0][5] = (ImageButton) findViewById(R.id.button05);
        buttons[0][6] = (ImageButton) findViewById(R.id.button06);
        buttons[0][7] = (ImageButton) findViewById(R.id.button07);
        buttons[0][8] = (ImageButton) findViewById(R.id.button08);
        buttons[1][0] = (ImageButton) findViewById(R.id.button10);
        buttons[1][1] = (ImageButton) findViewById(R.id.button11);
        buttons[1][2] = (ImageButton) findViewById(R.id.button12);
        buttons[1][3] = (ImageButton) findViewById(R.id.button13);
        buttons[1][4] = (ImageButton) findViewById(R.id.button14);
        buttons[1][5] = (ImageButton) findViewById(R.id.button15);
        buttons[1][6] = (ImageButton) findViewById(R.id.button16);
        buttons[1][7] = (ImageButton) findViewById(R.id.button17);
        buttons[1][8] = (ImageButton) findViewById(R.id.button18);
        buttons[2][0] = (ImageButton) findViewById(R.id.button20);
        buttons[2][1] = (ImageButton) findViewById(R.id.button21);
        buttons[2][2] = (ImageButton) findViewById(R.id.button22);
        buttons[2][3] = (ImageButton) findViewById(R.id.button23);
        buttons[2][4] = (ImageButton) findViewById(R.id.button24);
        buttons[2][5] = (ImageButton) findViewById(R.id.button25);
        buttons[2][6] = (ImageButton) findViewById(R.id.button26);
        buttons[2][7] = (ImageButton) findViewById(R.id.button27);
        buttons[2][8] = (ImageButton) findViewById(R.id.button28);
        buttons[3][0] = (ImageButton) findViewById(R.id.button30);
        buttons[3][1] = (ImageButton) findViewById(R.id.button31);
        buttons[3][2] = (ImageButton) findViewById(R.id.button32);
        buttons[3][3] = (ImageButton) findViewById(R.id.button33);
        buttons[3][4] = (ImageButton) findViewById(R.id.button34);
        buttons[3][5] = (ImageButton) findViewById(R.id.button35);
        buttons[3][6] = (ImageButton) findViewById(R.id.button36);
        buttons[3][7] = (ImageButton) findViewById(R.id.button37);
        buttons[3][8] = (ImageButton) findViewById(R.id.button38);
        buttons[4][0] = (ImageButton) findViewById(R.id.button40);
        buttons[4][1] = (ImageButton) findViewById(R.id.button41);
        buttons[4][2] = (ImageButton) findViewById(R.id.button42);
        buttons[4][3] = (ImageButton) findViewById(R.id.button43);
        buttons[4][4] = (ImageButton) findViewById(R.id.button44);
        buttons[4][5] = (ImageButton) findViewById(R.id.button45);
        buttons[4][6] = (ImageButton) findViewById(R.id.button46);
        buttons[4][7] = (ImageButton) findViewById(R.id.button47);
        buttons[4][8] = (ImageButton) findViewById(R.id.button48);
        buttons[5][0] = (ImageButton) findViewById(R.id.button50);
        buttons[5][1] = (ImageButton) findViewById(R.id.button51);
        buttons[5][2] = (ImageButton) findViewById(R.id.button52);
        buttons[5][3] = (ImageButton) findViewById(R.id.button53);
        buttons[5][4] = (ImageButton) findViewById(R.id.button54);
        buttons[5][5] = (ImageButton) findViewById(R.id.button55);
        buttons[5][6] = (ImageButton) findViewById(R.id.button56);
        buttons[5][7] = (ImageButton) findViewById(R.id.button57);
        buttons[5][8] = (ImageButton) findViewById(R.id.button58);
        buttons[6][0] = (ImageButton) findViewById(R.id.button60);
        buttons[6][1] = (ImageButton) findViewById(R.id.button61);
        buttons[6][2] = (ImageButton) findViewById(R.id.button62);
        buttons[6][3] = (ImageButton) findViewById(R.id.button63);
        buttons[6][4] = (ImageButton) findViewById(R.id.button64);
        buttons[6][5] = (ImageButton) findViewById(R.id.button65);
        buttons[6][6] = (ImageButton) findViewById(R.id.button66);
        buttons[6][7] = (ImageButton) findViewById(R.id.button67);
        buttons[6][8] = (ImageButton) findViewById(R.id.button68);
        buttons[7][0] = (ImageButton) findViewById(R.id.button70);
        buttons[7][1] = (ImageButton) findViewById(R.id.button71);
        buttons[7][2] = (ImageButton) findViewById(R.id.button72);
        buttons[7][3] = (ImageButton) findViewById(R.id.button73);
        buttons[7][4] = (ImageButton) findViewById(R.id.button74);
        buttons[7][5] = (ImageButton) findViewById(R.id.button75);
        buttons[7][6] = (ImageButton) findViewById(R.id.button76);
        buttons[7][7] = (ImageButton) findViewById(R.id.button77);
        buttons[7][8] = (ImageButton) findViewById(R.id.button78);
        buttons[8][0] = (ImageButton) findViewById(R.id.button80);
        buttons[8][1] = (ImageButton) findViewById(R.id.button81);
        buttons[8][2] = (ImageButton) findViewById(R.id.button82);
        buttons[8][3] = (ImageButton) findViewById(R.id.button83);
        buttons[8][4] = (ImageButton) findViewById(R.id.button84);
        buttons[8][5] = (ImageButton) findViewById(R.id.button85);
        buttons[8][6] = (ImageButton) findViewById(R.id.button86);
        buttons[8][7] = (ImageButton) findViewById(R.id.button87);
        buttons[8][8] = (ImageButton) findViewById(R.id.button88);
        buttons[9][0] = (ImageButton) findViewById(R.id.button90);
        buttons[9][1] = (ImageButton) findViewById(R.id.button91);
        buttons[9][2] = (ImageButton) findViewById(R.id.button92);
        buttons[9][3] = (ImageButton) findViewById(R.id.button93);
        buttons[9][4] = (ImageButton) findViewById(R.id.button94);
        buttons[9][5] = (ImageButton) findViewById(R.id.button95);
        buttons[9][6] = (ImageButton) findViewById(R.id.button96);
        buttons[9][7] = (ImageButton) findViewById(R.id.button97);
        buttons[9][8] = (ImageButton) findViewById(R.id.button98);
        buttons[10][0] = (ImageButton) findViewById(R.id.button100);
        buttons[10][1] = (ImageButton) findViewById(R.id.button101);
        buttons[10][2] = (ImageButton) findViewById(R.id.button102);
        buttons[10][3] = (ImageButton) findViewById(R.id.button103);
        buttons[10][4] = (ImageButton) findViewById(R.id.button104);
        buttons[10][5] = (ImageButton) findViewById(R.id.button105);
        buttons[10][6] = (ImageButton) findViewById(R.id.button106);
        buttons[10][7] = (ImageButton) findViewById(R.id.button107);
        buttons[10][8] = (ImageButton) findViewById(R.id.button108);
        minSteps[0] = 6; minSteps[1] = 5; minSteps[2] = 5; minSteps[3] = 5; minSteps[4] = 5; minSteps[5] = 12;
        minSteps[6] = 11; minSteps[7] = 11; minSteps[8] = 10; minSteps[9] = 11; minSteps[10] = 12;
        minSteps[11] = 11; minSteps[12] = 12; minSteps[13] = 10; minSteps[14] = 12; minSteps[15] = 12;
        minSteps[16] = 11; minSteps[17] = 10; minSteps[18] = 12; minSteps[19] = 12; minSteps[20] = 18;
        minSteps[21] = 17; minSteps[22] = 18; minSteps[23] = 17; minSteps[24] = 17; minSteps[25] = 17;
        minSteps[26] = 16; minSteps[27] = 16; minSteps[28] = 19; minSteps[29] = 17; minSteps[30] = 18;
        minSteps[31] = 16; minSteps[32] = 16; minSteps[33] = 17; minSteps[34] = 15; minSteps[35] = 20;
        minSteps[36] = 21; minSteps[37] = 22; minSteps[38] = 21; minSteps[39] = 23; minSteps[40] = 20;
        minSteps[41] = 22; minSteps[42] = 22; minSteps[43] = 20; minSteps[44] = 21; minSteps[45] = 24;
        minSteps[46] = 21; minSteps[47] = 24; minSteps[48] = 27; minSteps[49] = 26; minSteps[50] = 25;
        minSteps[51] = 23; minSteps[52] = 23; minSteps[53] = 23; minSteps[54] = 22; minSteps[55] = 25;
        minSteps[56] = 22; minSteps[57] = 24; minSteps[58] = 24; minSteps[59] = 25; minSteps[60] = 24;
        minSteps[61] = 25; minSteps[62] = 24; minSteps[63] = 25; minSteps[64] = 26; minSteps[65] = 31;
        minSteps[66] = 30; minSteps[67] = 31; minSteps[68] = 27; minSteps[69] = 27; minSteps[70] = 30;
        minSteps[71] = 28; minSteps[72] = 30; minSteps[73] = 29; minSteps[74] = 28; minSteps[75] = 26;
        minSteps[76] = 30; minSteps[77] = 29; minSteps[78] = 31; minSteps[79] = 28; minSteps[80] = 27;
        minSteps[81] = 31; minSteps[82] = 29; minSteps[83] = 30; minSteps[84] = 26; minSteps[85] = 28;
        minSteps[86] = 31; minSteps[87] = 28; minSteps[88] = 28; minSteps[89] = 29; minSteps[90] = 28;
        minSteps[91] = 30; minSteps[92] = 27; minSteps[93] = 28; minSteps[94] = 29; minSteps[95] = 31;
        minSteps[96] = 30; minSteps[97] = 27; minSteps[98] = 28; minSteps[99] = 30; minSteps[100] = 31;
        minSteps[101] = 27; minSteps[102] = 30; minSteps[103] = 28; minSteps[104] = 27; minSteps[105] = 27;
        minSteps[106] = 30; minSteps[107] = 29; minSteps[108] = 30; minSteps[109] = 25; minSteps[110] = 28;
        minSteps[111] = 27; minSteps[112] = 31; minSteps[113] = 28; minSteps[114] = 29; minSteps[115] = 27;
        minSteps[116] = 27; minSteps[117] = 28; minSteps[118] = 29; minSteps[119] = 29;
        text = (TextView) findViewById(R.id.text_level);
        r_layout = (RelativeLayout)  findViewById(R.id.r_layout);
        button_pause = (ImageButton) findViewById(R.id.button_pause);
        button_question = (ImageButton) findViewById(R.id.button_question);
        button_pause.setOnClickListener(this);
        button_question.setOnClickListener(this);
        Typeface Marske = Typeface.createFromAsset(getAssets(),"fonts/Marske.ttf");
        text.setTypeface(Marske);
        Bundle extras = getIntent().getExtras();
        String from_where = extras.getString("from_where");
        if (from_where.equals("choose_level")) {
            cur_level = extras.getInt("level");
        } else if (from_where.equals("menu")){
            cur_level = loadLevel();
        } else {
            text.setText("error");
        }
        max_level = loadLevel();
        hint = (ImageView) findViewById(R.id.hint);
        if (cur_level == 6) {
            Dialog_teach_brown d = new Dialog_teach_brown(this);
            d.showDialog(this);
        } else if (cur_level == 11) {
            Dialog_teach_pink_and_cyan d = new Dialog_teach_pink_and_cyan(this);
            d.showDialog(this);
        } else if (cur_level == 36) {
            Dialog_level_36 d = new Dialog_level_36(this, R.string.text_level_36);
            d.showDialog(this);
        } else if (cur_level == 46) {
            Dialog_teach_light d = new Dialog_teach_light(this);
            d.showDialog(this);
        } else if (cur_level == 48) {
            Dialog_teach_other_light d = new Dialog_teach_other_light(this);
            d.showDialog(this);
        } else if (cur_level == 66) {
            Dialog_level_36 d = new Dialog_level_36(this, R.string.text_level_66);
            d.showDialog(this);
        } else if (cur_level == 85) {
            Dialog_level_36 d = new Dialog_level_36(this, R.string.text_level_85);
            d.showDialog(this);
        } else if (cur_level == 91) {
            Dialog_teach_light d = new Dialog_teach_light(this);
            d.showDialog(this);
        }
        if (cur_level <= 40) {
            text.setTextColor(Color.parseColor("#00ccff"));
        } else if (cur_level > 40 && cur_level <= 80) {
            text.setTextColor(Color.parseColor("#ffff00"));
            button_pause.setBackgroundColor(Color.parseColor("#00ccff"));
        } else if (cur_level > 80 && cur_level <= 120) {
            text.setTextColor(Color.parseColor("#ff0066"));
            button_question.setBackgroundColor(Color.parseColor("#00ccff"));
        }
        String txt = getString(R.string.level);
        txt += " " + cur_level;
        text.setText(txt);
        if (cur_level <= 5) {
            CUR_ROWS = 5;
            CUR_COLUMNS = 5;
            CUR_MIDDLE_COLORS = 4;
            START_ROW = 2;
            START_COLUMN = 2;
            build_game_beginner();
        } else if (cur_level <= 20) {
            CUR_ROWS = 5;
            CUR_COLUMNS = 9;
            CUR_MIDDLE_COLORS = 8;
            START_ROW = 2;
            START_COLUMN = 0;
            build_game_easy();
        } else if (cur_level <= 35) {
            CUR_ROWS = 7;
            CUR_COLUMNS = 9;
            CUR_MIDDLE_COLORS = 12;
            START_ROW = 2;
            START_COLUMN = 0;
            build_game_middle();
        } else if (cur_level <= 65) {
            CUR_ROWS = 9;
            CUR_COLUMNS = 9;
            CUR_MIDDLE_COLORS = 16;
            START_ROW = 0;
            START_COLUMN = 0;
            TableLayout layout = (TableLayout) findViewById(R.id.table_layout);
            final float GESTURE_THRESHOLD_DP = 50.0f;
            final float scale = getResources().getDisplayMetrics().density;
            int mGestureThreshold = (int) (GESTURE_THRESHOLD_DP * scale + 0.5f);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) layout.getLayoutParams();
            params.topMargin = mGestureThreshold;
            build_game_hard();
        } else {
            CUR_ROWS = 11;
            CUR_COLUMNS = 9;
            CUR_MIDDLE_COLORS = 20;
            START_ROW = 0;
            START_COLUMN = 0;
            build_game_super_hard();
        }
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_COLUMNS; j++) {
                if ((i >= START_ROW && i < START_ROW + CUR_ROWS) && (j >= START_COLUMN && j < START_COLUMN + CUR_COLUMNS)) {
                    if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                        buttons[i][j].setOnClickListener(this);
                        buttons[i][j].setTag(i*10 + j);
                    } else if (i % 2 == 1 & j % 2 == 1) {
                        buttons[i][j].setOnLongClickListener(this);
                        buttons[i][j].setTag(i*10 + j);
                    }
                } else {
                    buttons[i][j].setVisibility(View.INVISIBLE);
                    buttons[i][j].setEnabled(false);
                }
            }
        }
    }

    void build_game_beginner() {
        switch (cur_level) {
            case 1:
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 2:
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 3:
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 4:
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                break;
            case 5:
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
        }
    }
    void build_game_easy () {
        switch (cur_level) {
            case 6:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                break;
            case 7:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 8:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                break;
            case 9:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                break;
            case 10:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                break;
            case 11:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                break;
            case 12:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                break;
            case 13:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 14:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 15:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 16:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                break;
            case 17:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 18:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 19:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                break;
            case 20:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                break;
        }
    }
    void build_game_middle () {
        switch (cur_level) {
            case 21:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
            case 22:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                break;
            case 23:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 24:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
            case 25:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 26:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 27:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 28:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                break;
            case 29:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                break;
            case 30:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 31:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                break;
            case 32:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 33:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 34:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                break;
            case 35:
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
        }
    }
    void build_game_hard() {
        switch (cur_level) {
            case 36:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 37:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 38:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 39:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 40:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                break;
            case 41:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                break;
            case 42:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 43:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
            case 44:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                break;
            case 45:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                break;
            case 46:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
            case 47:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 48:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                break;
            case 49:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 50:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
            case 51:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 52:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                break;
            case 53:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                break;
            case 54:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 55:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                break;
            case 56:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                break;
            case 57:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
            case 58:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                break;
            case 59:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                break;
            case 60:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
            case 61:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                break;
            case 62:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                break;
            case 63:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                break;
            case 64:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                break;
            case 65:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                break;
        }
    }
    void build_game_super_hard() {
        switch(cur_level) {
            case 66:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 67:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 68:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
            case 69:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 70:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 71:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                break;
            case 72:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 73:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                break;
            case 74:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                break;
            case 75:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 76:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 77:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 78:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
            case 79:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 80:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                break;
            case 81:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                break;
            case 82:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 83:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 84:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
            case 85:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
            case 86:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                break;
            case 87:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                break;
            case 88:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 89:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                break;
            case 90:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                break;
            case 91:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                break;
            case 92:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 93:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                break;
            case 94:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                break;
            case 95:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 96:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
            case 97:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
            case 98:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 99:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                break;
            case 100:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                break;
            case 101:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                break;
            case 102:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                break;
            case 103:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 104:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 105:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 106:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                break;
            case 107:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                break;
            case 108:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                break;
            case 109:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                break;
            case 110:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                break;
            case 111:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                break;
            case 112:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                break;
            case 113:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                break;
            case 114:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                break;
            case 115:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                break;
            case 116:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                break;
            case 117:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                break;
            case 118:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                break;
            case 119:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_purple));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.brown));
                break;
            case 120:
                buttons[1][1].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[1][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][5].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[1][7].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][1].setBackgroundColor(getColor(getApplicationContext(), R.color.cyan));
                buttons[3][3].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[3][5].setBackgroundColor(getColor(getApplicationContext(), R.color.pink));
                buttons[3][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_green));
                buttons[5][1].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[5][3].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][5].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[5][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][1].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[7][5].setBackgroundColor(getColor(getApplicationContext(), R.color.green));
                buttons[7][7].setBackgroundColor(getColor(getApplicationContext(), R.color.purple));
                buttons[9][1].setBackgroundColor(getColor(getApplicationContext(), R.color.light_orange));
                buttons[9][3].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                buttons[9][5].setBackgroundColor(getColor(getApplicationContext(), R.color.orange));
                buttons[9][7].setBackgroundColor(getColor(getApplicationContext(), R.color.light_yellow));
                break;
        }


    }
    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public boolean check_result (int color_middle) {
        int red = 0, yellow = 0, blue = 0, white = 0, transparent = 0;
        for (int i = 0; i < 4; i++) {
            if (sides[i] == getColor(getApplicationContext(), R.color.red)) {
                red++;
            } else if (sides[i] == getColor(getApplicationContext(), R.color.yellow)) {
                yellow++;
            } else if (sides[i] == getColor(getApplicationContext(), R.color.blue)) {
                blue++;
            } else if (sides[i] == getColor(getApplicationContext(), R.color.transparent)) {
                transparent++;
            } else if (sides[i] == getColor(getApplicationContext(), R.color.white)) {
                white++;
            }
        }
        if (color_middle == getColor(getApplicationContext(), R.color.orange) && red == 1 && yellow == 1 && transparent == 2)
            return true;
        if (color_middle == getColor(getApplicationContext(), R.color.purple) && red == 1 && blue == 1 && transparent == 2)
            return true;
        if (color_middle == getColor(getApplicationContext(), R.color.green) && yellow == 1 && blue == 1 && transparent == 2)
            return true;
        if (color_middle == getColor(getApplicationContext(), R.color.brown) && red == 1 && yellow == 1 && blue == 1 && transparent == 1)
            return true;
        if (color_middle == getColor(getApplicationContext(), R.color.pink) && red == 1 && white == 1 && transparent == 2)
            return true;
        if (color_middle == getColor(getApplicationContext(), R.color.cyan) && blue == 1 && white == 1 && transparent == 2)
            return true;
        if (color_middle == getColor(getApplicationContext(), R.color.light_yellow) && yellow == 1 && white == 1 && transparent == 2)
            return true;
        if (color_middle == getColor(getApplicationContext(), R.color.light_orange) && red == 1 && yellow == 1 && white == 1 && transparent == 1)
            return true;
        if (color_middle == getColor(getApplicationContext(), R.color.light_purple) && red == 1 && blue == 1 && white == 1 && transparent == 1)
            return true;
        if (color_middle == getColor(getApplicationContext(), R.color.light_green) && yellow == 1 && blue == 1 && white == 1 && transparent == 1)
            return true;
        return false;
    }
    public boolean check_win() {
        int check = 0;
        for (int i = START_ROW + 1; i <= START_ROW + CUR_ROWS - 2; i = i + 2) {
            for (int j = START_COLUMN + 1; j <= START_COLUMN + CUR_COLUMNS - 2; j = j + 2) {
                int color_middle;
                color_middle = ((ColorDrawable)buttons[i][j].getBackground()).getColor();
                if (buttons[i-1][j].getBackground() != null) {
                    sides[0] = ((ColorDrawable)buttons[i-1][j].getBackground()).getColor();
                } else {
                    sides[0] = 0;
                }
                if (buttons[i+1][j].getBackground() != null) {
                    sides[1] = ((ColorDrawable)buttons[i+1][j].getBackground()).getColor();
                } else {
                    sides[1] = 0;
                }
                if (buttons[i][j-1].getBackground() != null) {
                    sides[2] = ((ColorDrawable)buttons[i][j-1].getBackground()).getColor();
                } else {
                    sides[2] = 0;
                }
                if (buttons[i][j+1].getBackground() != null) {
                    sides[3] = ((ColorDrawable)buttons[i][j+1].getBackground()).getColor();
                } else {
                    sides[3] = 0;
                }
                if (check_result(color_middle)) {
                    check++;
                }
            }
        }
        if (check == CUR_MIDDLE_COLORS) {
            return true;
        }
        return false;
    }
    public void onClick(View v) {
        if(v.getId() == R.id.button_pause) {
            saveLevel(cur_level);
            Dialog_2 dialog_2 = new Dialog_2(this);
            dialog_2.showDialog(this);
        } else if (v.getId() == R.id.button_question) {
            saveLevel(cur_level);
            show_intro();
        } else {
            int k = (int) v.getTag();
            int row = k / 10;
            int column = k % 10;
            if (states[row][column] == 0) {
                buttons[row][column].setBackgroundColor(getColor(getApplicationContext(), R.color.red));
                buttons[row][column].setImageResource(R.drawable.rectangle_not_solid);
                states[row][column]++;
            } else if (states[row][column] == 1) {
                buttons[row][column].setBackgroundColor(getColor(getApplicationContext(), R.color.yellow));
                buttons[row][column].setImageResource(R.drawable.rectangle_not_solid);
                states[row][column]++;
            } else if (states[row][column] == 2) {
                buttons[row][column].setBackgroundColor(getColor(getApplicationContext(), R.color.blue));
                buttons[row][column].setImageResource(R.drawable.rectangle_not_solid);
                states[row][column]++;
            } else if (states[row][column] == 3) {
                if (cur_level > 10) {
                    buttons[row][column].setBackgroundColor(getColor(getApplicationContext(), R.color.white));
                    buttons[row][column].setImageResource(R.drawable.rectangle_not_solid);
                    states[row][column]++;
                } else {
                    buttons[row][column].setBackgroundColor(getColor(getApplicationContext(), R.color.transparent));
                    buttons[row][column].setImageResource(R.drawable.rectangle);
                    states[row][column] = 0;
                }
            } else if (states[row][column] == 4) {
                buttons[row][column].setBackgroundColor(getColor(getApplicationContext(), R.color.transparent));
                buttons[row][column].setImageResource(R.drawable.rectangle);
                states[row][column] = 0;
            }
            if (check_win()) {
                if (max_level == cur_level && max_level < NUM_OF_LEVELS) {
                    max_level++;
                }
                for (int i = START_ROW; i <= START_ROW + CUR_ROWS - 1; i++) {
                    for (int j = START_COLUMN; j <= START_COLUMN + CUR_COLUMNS - 1; j++) {
                        buttons[i][j].setEnabled(false);
                    }
                }
                saveLevel(max_level);
                int s = 0;
                if (how_many_steps() <= minSteps[cur_level - 1]) {
                    s = 3;
                } else if (how_many_steps() == minSteps[cur_level - 1] + 1) {
                    s = 2;
                } else if (how_many_steps() > minSteps[cur_level - 1] + 1) s = 1;
                final_animation(s);
                numOfStars = new int[120];
                for (int i = 0; i < 120; i++) {
                    numOfStars[i] = loadNumOfStars(i + 1);
                }
                if (numOfStars[cur_level - 1] < s) {
                    saveNumOfStars(cur_level, s);
                    numOfStars[cur_level - 1] = loadNumOfStars(cur_level);
                }
            }
        }
    }
    void final_animation(final int s) {
        for (int i = START_ROW; i <= START_ROW + CUR_ROWS - 1 ; i++) {
            final Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.change_size);
            anim.setStartOffset(i * 150);
            for (int j = START_COLUMN; j <= START_COLUMN + CUR_COLUMNS - 1; j++) {
                buttons[i][j].startAnimation(anim);
                if (i == START_ROW + CUR_ROWS - 1) {
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            show_dialogs(s);

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            }
        }
    }
    void show_dialogs(int s){
        if (cur_level == 120) {
            Dialog_last_level d = new Dialog_last_level(this, s);
            d.showDialog(this);
        } else {
            Dialog dialog = new Dialog(this, s);
            dialog.showDialog(this);
        }
    }
    int how_many_steps() {
        int r = 0;
        for (int i = START_ROW; i <= START_ROW + CUR_ROWS - 1; i++) {
            for (int j = START_COLUMN; j <= START_COLUMN + CUR_COLUMNS - 1; j++) {
                if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                    ColorDrawable buttonColor = (ColorDrawable) buttons[i][j].getBackground();
                    if (buttonColor != null) {
                        int colorId = buttonColor.getColor();
                        if (colorId != ContextCompat.getColor(this, R.color.transparent)) {
                            r++;
                        }
                    }
                }
            }
        }
        Log.d("TAG", "" + r);
        return r;
    }
    public boolean onLongClick(View v) {
        int k = (int) v.getTag();
        int row = k / 10;
        int column = k % 10;
        int color = ((ColorDrawable)buttons[row][column].getBackground()).getColor();
        if (color == getColor(getApplicationContext(), R.color.purple)) {
            hint.setImageResource(R.drawable.purple);
            return true;
        } else if (color == getColor(getApplicationContext(), R.color.green)) {
            hint.setImageResource(R.drawable.green);
            return true;
        } else if (color == getColor(getApplicationContext(), R.color.orange)) {
            hint.setImageResource(R.drawable.orange);
            return true;
        } else if (color == getColor(getApplicationContext(), R.color.brown)) {
            hint.setImageResource(R.drawable.brown);
            return true;
        } else if (color == getColor(getApplicationContext(), R.color.pink)) {
            hint.setImageResource(R.drawable.pink);
            return true;
        } else if (color == getColor(getApplicationContext(), R.color.light_yellow)) {
            hint.setImageResource(R.drawable.light_yellow);
            return true;
        } else if (color == getColor(getApplicationContext(), R.color.cyan)) {
            hint.setImageResource(R.drawable.cyan);
            return true;
        } else if (color == getColor(getApplicationContext(), R.color.light_orange)) {
            hint.setImageResource(R.drawable.light_orange);
            return true;
        } else if (color == getColor(getApplicationContext(), R.color.light_green)) {
            hint.setImageResource(R.drawable.light_green);
            return true;
        } else if (color == getColor(getApplicationContext(), R.color.light_purple)) {
            hint.setImageResource(R.drawable.light_purple);
            return true;
        }
        return false;
    }

    void saveLevel(int level) {
        sPref = getSharedPreferences("LEVEL", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.clear();
        ed.putInt("level", level);
        ed.commit();
    }

    int loadLevel() {
        sPref = getSharedPreferences("LEVEL", MODE_PRIVATE);
        int level = sPref.getInt("level", 1);
        return level;
    }
    @Override
    protected void onStop() {
        saveLevel(max_level);
        super.onStop();
    }

    boolean isPlayingMusic = true;
    boolean continueBGMusic = false;
    @Override
    protected void onPause() {
        Log.d("lifecycle", "im in onPause, play music: " + playMusic);
        super.onPause();
        saveLevel(max_level);
        doUnbindService();
        playMusic = loadMusic();
        Log.d("MUSIC", "" + playMusic);
        if (!continueBGMusic) {
            mServ.pauseMusic();
            isPlayingMusic = false;
        } else if (playMusic && mServ != null){
            mServ.resumeMusic();
            Log.d("ch", "check");
            isPlayingMusic = true;
        } else if (mServ == null) {
            doBindService();
            Intent music = new Intent();
            music.setClass(this,MusicService.class);
            startService(music);
            isPlayingMusic = true;
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("lifecycle", "im in onResume, playMusic" + playMusic);
        continueBGMusic = false;
        playMusic = loadMusic();
        doBindService();
        if (playMusic && mServ!= null){
            isPlayingMusic = true;
            Log.d("fucking music", "hey, mServ is not null");
            mServ.resumeMusic();
        } else if (!playMusic) {
            isPlayingMusic = false;
        }
    }
    @Override
    protected void onDestroy() {
        saveLevel(max_level);
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        saveLevel(cur_level);
        Dialog_2 dialog_2 = new Dialog_2(this);
        dialog_2.showDialog(this);
    }
}
