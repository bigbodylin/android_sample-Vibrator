# android_sample-Vibrator

### 1. add user permission in manifest.xml
    <uses-permission android:name="android.permission.VIBRATE"/>
   
### 2. get system service.
    Vibrator mVibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);

### 3. if sdk version >= 26 (android O) function has "VibrationEffect"
    // simple vibrator.
    mVibrator.vibrate(VibrationEffect
                .createOneShot(mSimpleViberatorTime, VibrationEffect.DEFAULT_AMPLITUDE));
    
    // vibrator with audio attribute.
    AudioAttributes mAudioAttributes = 
             new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM).build();
    mVibrator.vibrate(VibrationEffect
            .createOneShot(mSimpleViberatorTime, VibrationEffect.DEFAULT_AMPLITUDE),mAudioAttributes);
            
    // pattern vibrator
    long[] mPatternViberatorTime = {100,2000,300,1000,200};
     // repeat variable
     /*
      -1 這組震動不重複
       0 不斷重複這組震動
     n-1 第一次跑完之後 從[n-1]開始重複循環
     */
    mVibrator.vibrate(VibrationEffect.createWaveform(mPatternViberatorTime,-1));
    



