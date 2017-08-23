package vlimv.colorquad;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class ChooseLevel extends Game {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
        doBindService();
        max_level = loadLevel();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    int loadLevel() {
        sPref = getSharedPreferences("LEVEL", MODE_PRIVATE);
        int level = sPref.getInt("level", 1);
        return level;
    }

    public static void disable(int l, Button[][] levels) {
        for (int i = 7; i >= 0; i--) {
            for (int j = 4; j >= 0; j--) {
                int k = (Integer)levels[i][j].getTag();
                if (k > l) {
                    levels[i][j].setEnabled(false);
                    levels[i][j].setTextColor(Color.GRAY);
                }
            }
        }
    }
    public static void setBG(int l, Button[][] levels, int f) {
        int one = R.drawable.rectangle_one_star_blue;
        int two = R.drawable.rectangle_two_stars_blue;
        int three = R.drawable.rectangle_three_stars_blue;
        if (f == 2) {
            one = R.drawable.rectangle_one_star_yellow;
            two = R.drawable.rectangle_two_stars_yellow;
            three = R.drawable.rectangle_three_stars_yellow;
        } else if (f == 3) {
            one = R.drawable.rectangle_one_star_pink;
            two = R.drawable.rectangle_two_stars_pink;
            three = R.drawable.rectangle_three_stars_pink;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                int k = (Integer)levels[i][j].getTag();
                if (k < l) {
                    levels[i][j].setTextColor(Color.parseColor("#000000"));
                    if (numOfStars[k - 1] == 1) {
                        levels[i][j].setBackgroundResource(one);
                    } else if (numOfStars[k - 1] == 2) {
                        levels[i][j].setBackgroundResource(two);
                    } else if (numOfStars[k - 1] == 3) {
                        levels[i][j].setBackgroundResource(three);
                    }
                }
            }
        }
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class fragment_1 extends Fragment implements View.OnClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        Button levels[][]= new Button[8][5];
        ImageButton back_button;

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

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_choose_level_1, container, false);
            back_button = (ImageButton) rootView.findViewById(R.id.back_button);
            back_button.setOnClickListener(this);
            Typeface Marske = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Marske.ttf");
            levels[0][0] = (Button) rootView.findViewById (R.id.level_1);
            levels[0][1] = (Button) rootView.findViewById (R.id.level_2);
            levels[0][2] = (Button) rootView.findViewById (R.id.level_3);
            levels[0][3] = (Button) rootView.findViewById (R.id.level_4);
            levels[0][4] = (Button) rootView.findViewById (R.id.level_5);
            levels[1][0] = (Button) rootView.findViewById (R.id.level_6);
            levels[1][1] = (Button) rootView.findViewById (R.id.level_7);
            levels[1][2] = (Button) rootView.findViewById (R.id.level_8);
            levels[1][3] = (Button) rootView.findViewById (R.id.level_9);
            levels[1][4] = (Button) rootView.findViewById (R.id.level_10);
            levels[2][0] = (Button) rootView.findViewById (R.id.level_11);
            levels[2][1] = (Button) rootView.findViewById (R.id.level_12);
            levels[2][2] = (Button) rootView.findViewById (R.id.level_13);
            levels[2][3] = (Button) rootView.findViewById (R.id.level_14);
            levels[2][4] = (Button) rootView.findViewById (R.id.level_15);
            levels[3][0] = (Button) rootView.findViewById (R.id.level_16);
            levels[3][1] = (Button) rootView.findViewById (R.id.level_17);
            levels[3][2] = (Button) rootView.findViewById (R.id.level_18);
            levels[3][3] = (Button) rootView.findViewById (R.id.level_19);
            levels[3][4] = (Button) rootView.findViewById (R.id.level_20);
            levels[4][0] = (Button) rootView.findViewById (R.id.level_21);
            levels[4][1] = (Button) rootView.findViewById (R.id.level_22);
            levels[4][2] = (Button) rootView.findViewById (R.id.level_23);
            levels[4][3] = (Button) rootView.findViewById (R.id.level_24);
            levels[4][4] = (Button) rootView.findViewById (R.id.level_25);
            levels[5][0] = (Button) rootView.findViewById (R.id.level_26);
            levels[5][1] = (Button) rootView.findViewById (R.id.level_27);
            levels[5][2] = (Button) rootView.findViewById (R.id.level_28);
            levels[5][3] = (Button) rootView.findViewById (R.id.level_29);
            levels[5][4] = (Button) rootView.findViewById (R.id.level_30);
            levels[6][0] = (Button) rootView.findViewById (R.id.level_31);
            levels[6][1] = (Button) rootView.findViewById (R.id.level_32);
            levels[6][2] = (Button) rootView.findViewById (R.id.level_33);
            levels[6][3] = (Button) rootView.findViewById (R.id.level_34);
            levels[6][4] = (Button) rootView.findViewById (R.id.level_35);
            levels[7][0] = (Button) rootView.findViewById (R.id.level_36);
            levels[7][1] = (Button) rootView.findViewById (R.id.level_37);
            levels[7][2] = (Button) rootView.findViewById (R.id.level_38);
            levels[7][3] = (Button) rootView.findViewById (R.id.level_39);
            levels[7][4] = (Button) rootView.findViewById (R.id.level_40);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 5; j++) {
                    levels[i][j].setTag(i*5 + j + 1);
                    levels[i][j].setOnClickListener(this);
                    levels[i][j].setTypeface(Marske);
                }
            }
            disable(max_level, levels);
            setBG(max_level, levels, 1);
            return rootView;
        }

        public void onClick(View v) {
            if (v.getId() == R.id.back_button) {
                Intent home_intent = new Intent(getActivity(), MainMenu.class);
                continueBGMusic = true;
                startActivity(home_intent);
            }
            else {
                int l = (Integer) v.getTag();
                Intent level_intent = new Intent(getActivity(), Levels.class);
                continueBGMusic = true;
                Bundle extras = new Bundle();
                extras.putString("from_where", "choose_level");
                extras.putInt("level", l);
                level_intent.putExtras(extras);
                startActivity(level_intent);
            }
        }
    }

    public static class fragment_2 extends Fragment implements View.OnClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        private static final String ARG_SECTION_NUMBER = "section_number";

        Button levels[][]= new Button[8][5];
        ImageButton back_button;

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
            View rootView = inflater.inflate(R.layout.fragment_choose_level_2, container, false);

            back_button = (ImageButton) rootView.findViewById(R.id.button_back);
            back_button.setOnClickListener(this);
            Typeface Marske = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Marske.ttf");

            levels[0][0] = (Button) rootView.findViewById (R.id.level_41);
            levels[0][1] = (Button) rootView.findViewById (R.id.level_42);
            levels[0][2] = (Button) rootView.findViewById (R.id.level_43);
            levels[0][3] = (Button) rootView.findViewById (R.id.level_44);
            levels[0][4] = (Button) rootView.findViewById (R.id.level_45);
            levels[1][0] = (Button) rootView.findViewById (R.id.level_46);
            levels[1][1] = (Button) rootView.findViewById (R.id.level_47);
            levels[1][2] = (Button) rootView.findViewById (R.id.level_48);
            levels[1][3] = (Button) rootView.findViewById (R.id.level_49);
            levels[1][4] = (Button) rootView.findViewById (R.id.level_50);
            levels[2][0] = (Button) rootView.findViewById (R.id.level_51);
            levels[2][1] = (Button) rootView.findViewById (R.id.level_52);
            levels[2][2] = (Button) rootView.findViewById (R.id.level_53);
            levels[2][3] = (Button) rootView.findViewById (R.id.level_54);
            levels[2][4] = (Button) rootView.findViewById (R.id.level_55);
            levels[3][0] = (Button) rootView.findViewById (R.id.level_56);
            levels[3][1] = (Button) rootView.findViewById (R.id.level_57);
            levels[3][2] = (Button) rootView.findViewById (R.id.level_58);
            levels[3][3] = (Button) rootView.findViewById (R.id.level_59);
            levels[3][4] = (Button) rootView.findViewById (R.id.level_60);
            levels[4][0] = (Button) rootView.findViewById (R.id.level_61);
            levels[4][1] = (Button) rootView.findViewById (R.id.level_62);
            levels[4][2] = (Button) rootView.findViewById (R.id.level_63);
            levels[4][3] = (Button) rootView.findViewById (R.id.level_64);
            levels[4][4] = (Button) rootView.findViewById (R.id.level_65);
            levels[5][0] = (Button) rootView.findViewById (R.id.level_66);
            levels[5][1] = (Button) rootView.findViewById (R.id.level_67);
            levels[5][2] = (Button) rootView.findViewById (R.id.level_68);
            levels[5][3] = (Button) rootView.findViewById (R.id.level_69);
            levels[5][4] = (Button) rootView.findViewById (R.id.level_70);
            levels[6][0] = (Button) rootView.findViewById (R.id.level_71);
            levels[6][1] = (Button) rootView.findViewById (R.id.level_72);
            levels[6][2] = (Button) rootView.findViewById (R.id.level_73);
            levels[6][3] = (Button) rootView.findViewById (R.id.level_74);
            levels[6][4] = (Button) rootView.findViewById (R.id.level_75);
            levels[7][0] = (Button) rootView.findViewById (R.id.level_76);
            levels[7][1] = (Button) rootView.findViewById (R.id.level_77);
            levels[7][2] = (Button) rootView.findViewById (R.id.level_78);
            levels[7][3] = (Button) rootView.findViewById (R.id.level_79);
            levels[7][4] = (Button) rootView.findViewById (R.id.level_80);
            for (int i = 0; i < 8 ; i++) {
                for (int j = 0; j < 5; j++) {
                    levels[i][j].setTag(i*5 + j + 41);
                    levels[i][j].setOnClickListener(this);
                    levels[i][j].setTypeface(Marske);
                }
            }
            disable(max_level, levels);
            setBG(max_level, levels, 2);
            return rootView;
        }
        public void onClick(View v) {
            if (v.getId() == R.id.button_back) {
                Intent home_intent = new Intent(getActivity(), MainMenu.class);
                continueBGMusic = true;
                startActivity(home_intent);
            }
            else {
                int l = (Integer) v.getTag();
                Intent level_intent = new Intent(getActivity(), Levels.class);
                continueBGMusic = true;
                Bundle extras = new Bundle();
                extras.putString("from_where", "choose_level");
                extras.putInt("level", l);
                level_intent.putExtras(extras);
                startActivity(level_intent);
            }
        }
    }
    public static class fragment_3 extends Fragment implements View.OnClickListener{
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        Button levels[][]= new Button[8][5];
        ImageButton back_button;

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
            View rootView = inflater.inflate(R.layout.fragment_choose_level_3, container, false);

            back_button = (ImageButton) rootView.findViewById(R.id.button_back);
            back_button.setOnClickListener(this);
            Typeface Marske = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Marske.ttf");

            levels[0][0] = (Button) rootView.findViewById (R.id.level_81);
            levels[0][1] = (Button) rootView.findViewById (R.id.level_82);
            levels[0][2] = (Button) rootView.findViewById (R.id.level_83);
            levels[0][3] = (Button) rootView.findViewById (R.id.level_84);
            levels[0][4] = (Button) rootView.findViewById (R.id.level_85);
            levels[1][0] = (Button) rootView.findViewById (R.id.level_86);
            levels[1][1] = (Button) rootView.findViewById (R.id.level_87);
            levels[1][2] = (Button) rootView.findViewById (R.id.level_88);
            levels[1][3] = (Button) rootView.findViewById (R.id.level_89);
            levels[1][4] = (Button) rootView.findViewById (R.id.level_90);
            levels[2][0] = (Button) rootView.findViewById (R.id.level_91);
            levels[2][1] = (Button) rootView.findViewById (R.id.level_92);
            levels[2][2] = (Button) rootView.findViewById (R.id.level_93);
            levels[2][3] = (Button) rootView.findViewById (R.id.level_94);
            levels[2][4] = (Button) rootView.findViewById (R.id.level_95);
            levels[3][0] = (Button) rootView.findViewById (R.id.level_96);
            levels[3][1] = (Button) rootView.findViewById (R.id.level_97);
            levels[3][2] = (Button) rootView.findViewById (R.id.level_98);
            levels[3][3] = (Button) rootView.findViewById (R.id.level_99);
            levels[3][4] = (Button) rootView.findViewById (R.id.level_100);
            levels[4][0] = (Button) rootView.findViewById (R.id.level_101);
            levels[4][1] = (Button) rootView.findViewById (R.id.level_102);
            levels[4][2] = (Button) rootView.findViewById (R.id.level_103);
            levels[4][3] = (Button) rootView.findViewById (R.id.level_104);
            levels[4][4] = (Button) rootView.findViewById (R.id.level_105);
            levels[5][0] = (Button) rootView.findViewById (R.id.level_106);
            levels[5][1] = (Button) rootView.findViewById (R.id.level_107);
            levels[5][2] = (Button) rootView.findViewById (R.id.level_108);
            levels[5][3] = (Button) rootView.findViewById (R.id.level_109);
            levels[5][4] = (Button) rootView.findViewById (R.id.level_110);
            levels[6][0] = (Button) rootView.findViewById (R.id.level_111);
            levels[6][1] = (Button) rootView.findViewById (R.id.level_112);
            levels[6][2] = (Button) rootView.findViewById (R.id.level_113);
            levels[6][3] = (Button) rootView.findViewById (R.id.level_114);
            levels[6][4] = (Button) rootView.findViewById (R.id.level_115);
            levels[7][0] = (Button) rootView.findViewById (R.id.level_116);
            levels[7][1] = (Button) rootView.findViewById (R.id.level_117);
            levels[7][2] = (Button) rootView.findViewById (R.id.level_118);
            levels[7][3] = (Button) rootView.findViewById (R.id.level_119);
            levels[7][4] = (Button) rootView.findViewById (R.id.level_120);
            for (int i = 0; i < 8 ; i++) {
                for (int j = 0; j < 5; j++) {
                    levels[i][j].setTag(i*5 + j + 81);
                    levels[i][j].setOnClickListener(this);
                    levels[i][j].setTypeface(Marske);
                }
            }
            disable(max_level, levels);
            setBG(max_level, levels, 3);
            return rootView;
        }

        public void onClick(View v) {
            if (v.getId() == R.id.button_back) {
                Intent home_intent = new Intent(getActivity(), MainMenu.class);
                continueBGMusic = true;
                startActivity(home_intent);
            }
            else {
                int l = (Integer) v.getTag();
                Intent level_intent = new Intent(getActivity(), Levels.class);
                continueBGMusic = true;
                Bundle extras = new Bundle();
                extras.putString("from_where", "choose_level");
                extras.putInt("level", l);
                level_intent.putExtras(extras);
                startActivity(level_intent);
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
    @Override
    public void onBackPressed() {
        Intent home_intent = new Intent(this, MainMenu.class);
        continueBGMusic = true;
        startActivity(home_intent);
    }
}
