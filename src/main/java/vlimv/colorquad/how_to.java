package vlimv.colorquad;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Locale;

public class how_to extends Game {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
        doBindService();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    static boolean isPlayingMusic = true;
    static boolean continueBGMusic = false;
    @Override
    protected void onPause() {
        Log.d("lifecycle", "im in onPause, continue music: " + continueBGMusic);
        super.onPause();
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
        Log.d("lifecycle", "im in onResume");
        continueBGMusic = false;
        playMusic = loadMusic();
        doBindService();
        isPlayingMusic = true;
        if (playMusic && mServ!= null){
            isPlayingMusic = true;
            Log.d("fucking music", "hey, mServ is not null");
            mServ.resumeMusic();
        } else if(!playMusic) {
            isPlayingMusic = false;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        continueBGMusic = true;
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class fragment_1 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        private static final String ARG_SECTION_NUMBER = "section_number";

        public fragment_1() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static fragment_1 newInstance(int sectionNumber) {
            fragment_1 fragment = new fragment_1();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        Animation anim [] = new Animation[5];
        TextView text_1, text_2, text_3, text_4, text_greeting;
        ImageView orange, green, purple;
        boolean animated = false;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_how_to_1, container, false);
            Typeface Marske = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Marske.ttf");
            text_1 = (TextView) rootView.findViewById(R.id.text_1);
            text_2 = (TextView) rootView.findViewById(R.id.text_2);
            text_3 = (TextView) rootView.findViewById(R.id.text_3);
            text_4 = (TextView)rootView.findViewById(R.id.text_4);
            text_greeting = (TextView) rootView.findViewById(R.id.text_greeting);
            orange = (ImageView) rootView.findViewById(R.id.orange);
            green = (ImageView) rootView.findViewById(R.id.green);
            purple = (ImageView) rootView.findViewById(R.id.purple);
            animated = false;
            text_1.setTypeface(Marske);
            text_2.setTypeface(Marske);
            text_3.setTypeface(Marske);
            text_4.setTypeface(Marske);
            text_greeting.setTypeface(Marske);
            if (Locale.getDefault().getLanguage().equals("ru")) {
                SpannableString text_1_spannable = new SpannableString(text_1.getText().toString());
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f40000")), 109, 116, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ffff00")), 119, 125, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6600")), 141, 150, 0);
                text_1.setText(text_1_spannable);

                SpannableString text_2_spannable = new SpannableString(text_2.getText().toString());
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f40000")), 8, 15, 0);
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#3772ff")), 18, 23, 0);
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#9900cc")), 28, 39, 0);
                text_2.setText(text_2_spannable);

                SpannableString text_3_spannable = new SpannableString(text_3.getText().toString());
                text_3_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ff00")), 15, 23, 0);
                text_3_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ffff00")), 30, 37, 0);
                text_3_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#3772ff")), 39, 44, 0);
                text_3.setText(text_3_spannable);

                SpannableString text_4_spannable = new SpannableString(text_4.getText().toString());
                text_4_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ccff")), 0,6, 0);
                text_4.setText(text_4_spannable);
            } else {
                SpannableString text_1_spannable = new SpannableString(text_1.getText().toString());
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f40000")), 114, 117, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ffff00")), 122, 128, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6600")), 136, 142, 0);
                text_1.setText(text_1_spannable);

                SpannableString text_2_spannable = new SpannableString(text_2.getText().toString());
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f40000")), 4, 7, 0);
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#3772ff")), 12, 16, 0);
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#9900cc")), 24, 30, 0);
                text_2.setText(text_2_spannable);

                SpannableString text_3_spannable = new SpannableString(text_3.getText().toString());
                text_3_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ff00")), 4, 9, 0);
                text_3_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ffff00")), 14, 21, 0);
                text_3_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#3772ff")), 25, 29, 0);
                text_3.setText(text_3_spannable);

                SpannableString text_4_spannable = new SpannableString(text_4.getText().toString());
                text_4_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ccff")), 6, 11, 0);
                text_4.setText(text_4_spannable);
            }
            RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.layout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < 5; i++) {
                        if (anim[i].hasStarted() && !anim[i].hasEnded()) {
                            anim[i].cancel();
                            anim[i].reset();
                            break;
                        }
                    }

                }
            });
            anim[0] = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.text_anim);
            anim[0].setDuration(1500);
            anim[1] = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.text_anim);
            anim[1].setDuration(4000);
            anim[2] = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.text_anim);
            anim[3] = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.text_anim);
            anim[4] = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.text_anim);
            anim[4].setDuration(4000);
            if (!animated) {
                anim[0].setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        text_greeting.setVisibility(View.VISIBLE);
                        text_1.startAnimation(anim[1]);
                        orange.startAnimation(anim[1]);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                text_greeting.startAnimation(anim[0]);
                animated = true;
                anim[1].setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        text_1.setVisibility(View.VISIBLE);
                        orange.setVisibility(View.VISIBLE);
                        text_2.startAnimation(anim[2]);
                        purple.startAnimation(anim[2]);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                anim[2].setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        text_2.setVisibility(View.VISIBLE);
                        purple.setVisibility(View.VISIBLE);
                        text_3.setVisibility(View.VISIBLE);
                        green.setVisibility(View.VISIBLE);
                        text_3.startAnimation(anim[3]);
                        green.startAnimation(anim[3]);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                anim[3].setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        text_4.setVisibility(View.VISIBLE);
                        text_4.startAnimation(anim[4]);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
            return rootView;
        }
    }

    public static class fragment_2 extends Fragment implements View.OnClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        int states[] = new int[4];
        Animation anim[] = new Animation[3];
        ImageButton buttons[] = new ImageButton[4];
        TextView text_1, text_2, text_3, text_4;
        TableLayout tl;
        boolean animated = false;
        private static final String ARG_SECTION_NUMBER = "section_number";

        public fragment_2() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static fragment_2 newInstance(int sectionNumber) {
            fragment_2 fragment = new fragment_2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_how_to_2, container, false);
            Typeface Marske = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Marske.ttf");

            text_1 = (TextView) rootView.findViewById(R.id.text_1);
            text_2 = (TextView) rootView.findViewById(R.id.text_2);
            text_3 = (TextView) rootView.findViewById(R.id.text_3);
            text_4 = (TextView) rootView.findViewById(R.id.text_4);
            tl = (TableLayout) rootView.findViewById(R.id.table_layout);
            buttons[0] = (ImageButton) rootView.findViewById(R.id.button_top);
            buttons[1] = (ImageButton) rootView.findViewById(R.id.button_left);
            buttons[2] = (ImageButton) rootView.findViewById(R.id.button_right);
            buttons[3] = (ImageButton) rootView.findViewById(R.id.button_bottom);
            for (int i = 0; i < 4; i++) {
                buttons[i].setOnClickListener(this);
                buttons[i].setBackgroundColor(Color.parseColor("#00000000"));
            }
            text_1.setTypeface(Marske);
            text_2.setTypeface(Marske);
            text_3.setTypeface(Marske);
            text_4.setTypeface(Marske);
            if (Locale.getDefault().getLanguage().equals("ru")) {
                SpannableString text_1_spannable = new SpannableString(text_1.getText().toString());
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ff00")), 5, 9, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ccff")), 16, 26, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0066")), 35, 42, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#9900cc")), 50, 53, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#79ff78")), 73, 83, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6600")), 104, 108, 0);
                text_1.setText(text_1_spannable);

                SpannableString text_2_spannable = new SpannableString(text_2.getText().toString());
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f40000")), 9, 16, 0);
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ffff00")), 44, 48, 0);
                text_2.setText(text_2_spannable);

                SpannableString text_3_spannable = new SpannableString(text_3.getText().toString());
                text_3_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#bc8bcc")), 0, 9, 0);
                text_3.setText(text_3_spannable);

                SpannableString text_4_spannable = new SpannableString(text_4.getText().toString());
                text_4_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ccff")), 0, 6, 0);
                text_4.setText(text_4_spannable);
            } else {
                SpannableString text_1_spannable = new SpannableString(text_1.getText().toString());
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ff00")), 5, 9, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ccff")), 16, 21, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0066")), 37, 44, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#9900cc")), 78, 85, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6600")), 86, 93, 0);
                text_1.setText(text_1_spannable);

                SpannableString text_2_spannable = new SpannableString(text_2.getText().toString());
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f40000")), 4, 9, 0);
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ffff00")), 21, 27, 0);
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#3772ff")), 34, 39, 0);
                text_2.setText(text_2_spannable);

                SpannableString text_3_spannable = new SpannableString(text_3.getText().toString());
                text_3_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ffff99")), 8, 13, 0);
                text_3.setText(text_3_spannable);

                SpannableString text_4_spannable = new SpannableString(text_4.getText().toString());
                text_4_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ccff")), 6, 11, 0);
                text_4.setText(text_4_spannable);
            }
            RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.layout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < 3; i++) {
                        if (anim[i].hasStarted() && !anim[i].hasEnded()) {
                            anim[i].cancel();
                            anim[i].reset();
                            break;
                        }
                    }

                }
            });
            return rootView;
        }

        public void onClick(View v) {
            int id = 0;
            if (v.getId() == R.id.button_top)
                id = 0;
            else if (v.getId() == R.id.button_left)
                id = 1;
            else if (v.getId() == R.id.button_right)
                id = 2;
            else if (v.getId() == R.id.button_bottom)
                id = 3;
            if (states[id] == 0) {
                buttons[id].setBackgroundColor(Color.parseColor("#f40000"));
                buttons[id].setImageResource(R.drawable.rectangle_not_solid);
                states[id]++;
            } else if (states[id]== 1) {
                buttons[id].setBackgroundColor(Color.parseColor("#ffff00"));
                buttons[id].setImageResource(R.drawable.rectangle_not_solid);
                states[id]++;
            } else if (states[id] == 2) {
                buttons[id].setBackgroundColor(Color.parseColor("#3772ff"));
                buttons[id].setImageResource(R.drawable.rectangle_not_solid);
                states[id]++;
            } else if (states[id] == 3) {
                buttons[id].setBackgroundColor(Color.parseColor("#00000000"));
                buttons[id].setImageResource(R.drawable.rectangle);
                states[id] = 0;
            }
            if (check()) {
                final Animation anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.text_anim);
                anim.setDuration(4000);
                text_3.setVisibility(View.VISIBLE);
                text_4.setVisibility(View.VISIBLE);
                text_3.startAnimation(anim);
                text_4.startAnimation(anim);
                for (int i = 0; i < 4; i++) {
                    buttons[i].setEnabled(false);
                }
            }
        }
        public boolean check() {
            int transparent = 0, red = 0, yellow = 0;
            for (int i = 0; i < 4; i++) {
                if (states[i] == 0) {
                    transparent++;
                } else if (states[i] == 1) {
                    red++;
                } else if (states[i] == 2) {
                    yellow++;
                }
            }
            if (yellow == 1 && red == 1 && transparent == 2) {
                return true;
            } else if (transparent == 1 || transparent == 0) {
                text_2.setText(R.string.ht_fragment2_t4);
                text_2.setTextColor(Color.parseColor("#f40000"));
            }
                return false;
        }
        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
            if (isVisibleToUser && !animated) {
                anim[0] = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.text_anim);
                anim[0].setDuration(3500);
                anim[1] = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.text_anim);
                anim[2] = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.text_anim);
                anim[0].setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        text_1.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        tl.startAnimation(anim[1]);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                text_1.startAnimation(anim[0]);
                animated = true;
                anim[1].setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        tl.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        text_2.setVisibility(View.VISIBLE);
                        text_2.startAnimation(anim[2]);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }
    }
    public static class fragment_3 extends Fragment implements View.OnClickListener, View.OnLongClickListener{
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        ImageButton buttons[][] = new ImageButton[5][5];
        TextView text_level, text_1, text_2;
        Animation anim [] = new Animation[2];
        ImageView hint;
        int states[][] = new int[5][5];
        TableLayout tl;
        Button btn_start;
        boolean animated = false;
        public fragment_3() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static fragment_3 newInstance(int sectionNumber) {
            fragment_3 fragment = new fragment_3();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_how_to_3, container, false);
            text_1 = (TextView)rootView.findViewById(R.id.text_1);
            text_2 = (TextView)rootView.findViewById(R.id.text_2);
            tl = (TableLayout) rootView.findViewById(R.id.table_l);
            if (Locale.getDefault().getLanguage().equals("ru")) {
                SpannableString text_1_spannable = new SpannableString(text_1.getText().toString());
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ff00")), 11, 17, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ffff00")), 30, 40, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6600")), 69, 73, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ccff")), 90, 98, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#9900cc")), 112, 116, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#bc8bcc")), 141, 150, 0);
                text_1.setText(text_1_spannable);
                SpannableString text_2_spannable = new SpannableString(text_2.getText().toString());
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#79ff78")), 0, 7, 0);
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f40000")), 34, 40, 0);
                text_2.setText(text_2_spannable);
            } else {
                SpannableString text_1_spannable = new SpannableString(text_1.getText().toString());
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ff00")), 7, 12, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ffff00")), 22, 32, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#ff6600")), 35, 38, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#00ccff")), 81, 84, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#9900cc")), 101, 105, 0);
                text_1_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#bc8bcc")), 136, 138, 0);
                text_1.setText(text_1_spannable);
                SpannableString text_2_spannable = new SpannableString(text_2.getText().toString());
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#79ff78")), 0, 4, 0);
                text_2_spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#f40000")), 38, 43, 0);
                text_2.setText(text_2_spannable);
            }
            if (animated) {
                tl.setVisibility(View.VISIBLE);
                text_1.setVisibility(View.VISIBLE);
            }
            Typeface Marske = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Marske.ttf");
            buttons[0][0] = (ImageButton)rootView.findViewById(R.id.button00);
            buttons[0][1] = (ImageButton)rootView.findViewById(R.id.button01);
            buttons[0][2] = (ImageButton)rootView.findViewById(R.id.button02);
            buttons[0][3] = (ImageButton)rootView.findViewById(R.id.button03);
            buttons[0][4] = (ImageButton)rootView.findViewById(R.id.button04);
            buttons[1][0] = (ImageButton)rootView.findViewById(R.id.button10);
            buttons[1][1] = (ImageButton)rootView.findViewById(R.id.button11);
            buttons[1][2] = (ImageButton)rootView.findViewById(R.id.button12);
            buttons[1][3] = (ImageButton)rootView.findViewById(R.id.button13);
            buttons[1][4] = (ImageButton)rootView.findViewById(R.id.button14);
            buttons[2][0] = (ImageButton)rootView.findViewById(R.id.button20);
            buttons[2][1] = (ImageButton)rootView.findViewById(R.id.button21);
            buttons[2][2] = (ImageButton)rootView.findViewById(R.id.button22);
            buttons[2][3] = (ImageButton)rootView.findViewById(R.id.button23);
            buttons[2][4] = (ImageButton)rootView.findViewById(R.id.button24);
            buttons[3][0] = (ImageButton)rootView.findViewById(R.id.button30);
            buttons[3][1] = (ImageButton)rootView.findViewById(R.id.button31);
            buttons[3][2] = (ImageButton)rootView.findViewById(R.id.button32);
            buttons[3][3] = (ImageButton)rootView.findViewById(R.id.button33);
            buttons[3][4] = (ImageButton)rootView.findViewById(R.id.button34);
            buttons[4][0] = (ImageButton)rootView.findViewById(R.id.button40);
            buttons[4][1] = (ImageButton)rootView.findViewById(R.id.button41);
            buttons[4][2] = (ImageButton)rootView.findViewById(R.id.button42);
            buttons[4][3] = (ImageButton)rootView.findViewById(R.id.button43);
            buttons[4][4] = (ImageButton)rootView.findViewById(R.id.button44);
            hint = (ImageView) rootView.findViewById(R.id.hint);
            text_level = (TextView)rootView.findViewById(R.id.text_level);
            text_level.setTypeface(Marske);
            text_1.setTypeface(Marske);
            text_2.setTypeface(Marske);
            btn_start = (Button) rootView.findViewById(R.id.button_start);
            btn_start.setTypeface(Marske);
            btn_start.setEnabled(false);
            btn_start.setOnClickListener(this);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                        buttons[i][j].setOnClickListener(this);
                        buttons[i][j].setTag(i * 10 + j);
                    } else if (i % 2 == 1 && j % 2 == 1) {
                        buttons[i][j].setOnLongClickListener(this);
                    } else {
                        buttons[i][j].setEnabled(false);
                    }
                }
            }
            RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.r_layout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < 2; i++) {
                        if (anim[i].hasStarted() && !anim[i].hasEnded()) {
                            anim[i].cancel();
                            anim[i].reset();
                            break;
                        }
                    }

                }
            });
            return rootView;
        }

        public void onClick (View v) {
            if (v.getId() == R.id.button_start) {
                Intent intent = new Intent(getActivity(), Levels.class);
                Bundle extras = new Bundle();
                extras.putString("from_where", "menu");
                extras.putInt("level", 1);
                intent.putExtras(extras);
                continueBGMusic = true;
                startActivity(intent);
            } else {
                int row, column;
                int tag = (Integer) v.getTag();
                row = tag / 10;
                column = tag % 10;
                if (states[row][column] == 0) {
                    buttons[row][column].setBackgroundColor(Color.parseColor("#f40000"));
                    buttons[row][column].setImageResource(R.drawable.rectangle_not_solid);
                    states[row][column]++;
                } else if (states[row][column]== 1) {
                    buttons[row][column].setBackgroundColor(Color.parseColor("#ffff00"));
                    buttons[row][column].setImageResource(R.drawable.rectangle_not_solid);
                    states[row][column]++;
                } else if (states[row][column] == 2) {
                    buttons[row][column].setBackgroundColor(Color.parseColor("#3772ff"));
                    buttons[row][column].setImageResource(R.drawable.rectangle_not_solid);
                    states[row][column]++;
                } else if (states[row][column] == 3) {
                    buttons[row][column].setBackgroundColor(Color.parseColor("#00000000"));
                    buttons[row][column].setImageResource(R.drawable.rectangle);
                    states[row][column] = 0;
                }
                boolean isWon = check_win();
                if (isWon) {
                    text_2.setVisibility(View.VISIBLE);
                    btn_start.setVisibility(View.VISIBLE);
                    Animation anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.btn_anim);
                    btn_start.startAnimation(anim);
                    btn_start.setEnabled(true);
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            buttons[i][j].setEnabled(false);
                        }
                    }
                }
            }
        }
        public boolean onLongClick(View v)
        {
            switch (v.getId()) {
                case R.id.button11:
                    hint.setImageResource(R.drawable.green);
                    return true;
                case R.id.button13:
                    hint.setImageResource(R.drawable.purple);
                    return true;
                case R.id.button31:
                    hint.setImageResource(R.drawable.orange);
                    return true;
                case R.id.button33:
                    hint.setImageResource(R.drawable.green);
                    return true;
            }
            return false;
        }

        public boolean check_win() {
            int check = 0;
            if (check_result(11))
                check++;
            if (check_result(13))
                check++;
            if (check_result(31))
                check++;
            if (check_result(33))
                check++;
            if (check == 4) return true;
            else return false;
        }
        public boolean check_result(int k) {
            int red = 0, yellow = 0, blue = 0, transparent = 0;
            for (int i = k - 10; i <= k + 10; i = i + 20) {
                int row = i / 10;
                int column = i % 10;
                if (states[row][column] == 0) {
                    transparent++;
                } else if (states[row][column] == 1) {
                    red++;
                } else if (states[row][column] == 2) {
                    yellow++;
                } else if (states[row][column] == 3) {
                    blue++;
                }
            }
            for (int j = k - 1; j <= k + 1; j= j + 2) {
                int row = j / 10;
                int column = j % 10;
                if (states[row][column] == 0) {
                    transparent++;
                } else if (states[row][column] == 1) {
                    red++;
                } else if (states[row][column] == 2) {
                    yellow++;
                } else if (states[row][column] == 3) {
                    blue++;
                }
            }

            if (k == 11 && blue == 1 && yellow == 1 && transparent == 2)
                return true;
            else if (k == 13 && red == 1 && blue == 1 && transparent == 2)
                return true;
            else if (k == 31 && yellow == 1 && red == 1 && transparent == 2)
                return true;
            else if (k == 33 && yellow == 1 && blue == 1 && transparent == 2)
                return true;
            return false;
        }
        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
            if (isVisibleToUser && !animated) {
                anim[0] = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.text_anim);
                anim[0].setDuration(3500);
                anim[1] = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.text_anim);
                anim[0].setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        text_1.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        tl.setVisibility(View.VISIBLE);
                        tl.startAnimation(anim[1]);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                text_1.startAnimation(anim[0]);
                animated = true;
            }
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return fragment_1.newInstance(position + 1);
                case 1:
                    return fragment_2.newInstance(position + 1);
                case 2:
                    return fragment_3.newInstance(position + 1);
                default:
                    break;

            }
            return fragment_1.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
