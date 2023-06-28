package com.asli.rummy._CoinFlip.cointoss;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.asli.rummy.R;

import java.util.Random;

public class Cointoss {
    Context context;
    private Random r;
    private int coinSide;
    private int userSelectedcoinSide;
    private MediaPlayer mp;
    private int curSide = R.drawable.heads;
    ImageView coinImage;

    public Cointoss(Context context,ImageView coinImage,int coinSide,int userSelectedcoinSide) {
        this.context = context;
        this.coinImage = coinImage;
        this.coinSide = coinSide;
        this.userSelectedcoinSide = userSelectedcoinSide;
        flipCoin();
    }

    private long animateCoin(boolean stayTheSame) {

        Rotate3dAnimation animation;

        if (curSide == R.drawable.heads) {
            animation = new Rotate3dAnimation(coinImage, R.drawable.heads, R.drawable.tails, 0, 180, 0, 0, 0, 0);
        } else {
            animation = new Rotate3dAnimation(coinImage, R.drawable.tails, R.drawable.heads, 0, 180, 0, 0, 0, 0);
        }
        if (stayTheSame) {
            animation.setRepeatCount(5); // must be odd (5+1 = 6 flips so the side will stay the same)
        } else {
            animation.setRepeatCount(6); // must be even (6+1 = 7 flips so the side will not stay the same)
        }

        animation.setDuration(110);
        animation.setInterpolator(new LinearInterpolator());



        coinImage.startAnimation(animation);


        return animation.getDuration() * (animation.getRepeatCount() + 1);
    }

    public void flipCoin() {

        stopPlaying();
        mp = MediaPlayer.create(context, R.raw.coin_flip);
        mp.start();

        if (coinSide == 0) {  // We have Tails

            boolean stayTheSame = (curSide == R.drawable.heads);
            long timeOfAnimation = animateCoin(stayTheSame);
            curSide = R.drawable.heads;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    if (userSelectedcoinSide != coinSide) {  // User guessed Heads (WRONG)


                    } else {  // User guessed Tails (CORRECT)


                    }

                }


            }, timeOfAnimation + 100);


        } else {  // We have Heads

            boolean stayTheSame = (curSide == R.drawable.tails);
            long timeOfAnimation = animateCoin(stayTheSame);
            curSide = R.drawable.tails;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {


                    if (userSelectedcoinSide != coinSide) {  // User guessed Tails (WRONG)



                    } else {  // User guessed Heads (CORRECT)


                    }


                }

            }, timeOfAnimation + 100);

        }

    }

    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }


}
