package ip.cynic.beautiful_girl.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ip.cynic.beautiful_girl.R;
import ip.cynic.beautiful_girl.base.BaseApplication;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    @Bind(R.id.splash_text)
    TextView mTvSplash;
    @Bind(R.id.splash_img)
    ImageView mMvSplash;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.NoActionBar);
        //动态显示隐藏状态栏
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        if(BaseApplication.isBackground()) {
            Log.d(TAG,"is background");
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    /**
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        //失去焦点
        if (!hasFocus) {
            return;
        }
        startAnim();

        super.onWindowFocusChanged(hasFocus);
    }

    private void startAnim() {

        ObjectAnimator imgUp = ObjectAnimator.ofFloat(mMvSplash, "translationY", 0f, -250f);
        imgUp.setDuration(700);
        imgUp.setStartDelay(500);
        imgUp.start();

        ObjectAnimator mtvDown = ObjectAnimator.ofFloat(mTvSplash, "translationY", 200f, 500f);
        ObjectAnimator mtvAlpha = ObjectAnimator.ofFloat(mTvSplash, "alpha", 0f, 0.5f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(900);
        animatorSet.setStartDelay(700);
        animatorSet.play(mtvDown).with(mtvAlpha);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorSet.start();
    }
}
