package com.bigbodylin.sample.vibratortestsample;

import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {


    private Vibrator mVibrator;

    private Button mBtnSimpleVibrator, mBtnAudioAttributeVibrator, mBtnPatternVibrator;

    private long mSimpleViberatorTime = 2000;

    private long[] mPatternViberatorTime = {100,2000,300,1000,200};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        initLayout();
        setOnClicklistener();


    }


    private void initLayout() {
        mBtnSimpleVibrator = findViewById(R.id.btn_for_simple_viberator);
        mBtnAudioAttributeVibrator = findViewById(R.id.btn_for_audio_attribute_viberator);
        mBtnPatternVibrator = findViewById(R.id.btn_for_pattern_viberator);
    }


    private void setOnClicklistener() {
        mBtnSimpleVibrator.setOnClickListener(mSimpleVibratorListener);
        mBtnAudioAttributeVibrator.setOnClickListener(mAudioAttributeVibratorListener);
        mBtnPatternVibrator.setOnClickListener(mPatternVibratorListener);

    }


    private View.OnClickListener mSimpleVibratorListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mVibrator.hasVibrator()) {
                mVibrator.cancel();
                if (VERSION.SDK_INT >= VERSION_CODES.O) {
                    mVibrator.vibrate(VibrationEffect
                        .createOneShot(mSimpleViberatorTime, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    mVibrator.vibrate(mSimpleViberatorTime);
                }

            }
        }
    };

    private View.OnClickListener mAudioAttributeVibratorListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mVibrator.hasVibrator()) {
                mVibrator.cancel();
                if (VERSION.SDK_INT >= VERSION_CODES.O) {
                    AudioAttributes mAudioAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_ALARM).build();
                    mVibrator.vibrate(VibrationEffect
                            .createOneShot(mSimpleViberatorTime, VibrationEffect.DEFAULT_AMPLITUDE),
                        mAudioAttributes);
                } else {
                    if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
                        AudioAttributes mAudioAttributes = new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA).build();
                        mVibrator.vibrate(mSimpleViberatorTime, mAudioAttributes);

                    }
                }

            }
        }
    };

    private View.OnClickListener mPatternVibratorListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mVibrator.hasVibrator()) {
                mVibrator.cancel();
                if (VERSION.SDK_INT >= VERSION_CODES.O) {
                    // repeat variable
                    /*
                     -1 這組震動不重複
                      0 不斷重複這組震動
                      n-1 第一次跑完之後 從[n-1]開始重複循環
                     */
                    mVibrator.vibrate(VibrationEffect.createWaveform(mPatternViberatorTime,-1));
                } else {
                    mVibrator.vibrate(mPatternViberatorTime,-1);
                }

            }
        }
    };
}
