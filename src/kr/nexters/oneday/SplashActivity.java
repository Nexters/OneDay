package kr.nexters.oneday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class SplashActivity extends Activity {
	
	private View[] views = new View[4];
	private View title;
	private TextView cntText;
	private int cnt = 0;
	
	private AlphaAnimation animationTitle;
	private TranslateAnimation[] animations = new TranslateAnimation[4];
	private final long DELAY_TIME = 1200; 
	
	private boolean isAnimationEnd = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_splash);
		initialize();
	}
	
	private void initialize() {
		title = findViewById(R.id.splash_title);
//		cntText = (TextView) findViewById(R.id.splash_cnt);
		views[0] = findViewById(R.id.splash_1);
		views[1] = findViewById(R.id.splash_2);
		views[2] = findViewById(R.id.splash_3);
		views[3] = findViewById(R.id.splash_4);
		
		/* 좌상 */
		int startTime = 100;
		float factor = (DELAY_TIME - startTime) / 1000f;
		animations[0] = new TranslateAnimation(-300, 0, -300, 0);
		animations[0].setInterpolator(new AccelerateInterpolator(1.0f + factor));
		animations[0].setDuration(DELAY_TIME - startTime);
		animations[0].setStartTime(0);
		
		/* 우상 */
		startTime = 600;
		factor = (DELAY_TIME - startTime) / 1000f;
		animations[1] = new TranslateAnimation(300, 0, -300, 0);
		animations[1].setInterpolator(new AccelerateInterpolator(1.0f + factor));
		animations[1].setDuration(DELAY_TIME - startTime);
		animations[1].setStartTime(startTime);
		
		/* 좌하 */
		startTime = 200;
		factor = (DELAY_TIME - startTime) / 1000f;
		animations[2] = new TranslateAnimation(-300, 0, 300, 0);
		animations[2].setInterpolator(new AccelerateInterpolator(1.0f + factor));
		animations[2].setDuration(DELAY_TIME - startTime);
		animations[2].setStartTime(startTime);
		
		/* 우하 */
		startTime = 800;
		factor = (DELAY_TIME - startTime) / 1000f;
		animations[3] = new TranslateAnimation(300, 0, 300, 0); 
		animations[3].setInterpolator(new AccelerateInterpolator(1.0f + factor));
		animations[3].setDuration(DELAY_TIME - startTime);
		animations[3].setStartTime(startTime);
		
		/* title */
		animationTitle = new AlphaAnimation(0, 1);
		animationTitle.setDuration(300);
		animationTitle.setInterpolator(new AccelerateInterpolator(2.0f));
		animationTitle.setStartOffset(DELAY_TIME - 100);
		startImageAnimation();
	}
	
	private void startImageAnimation() {
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < views.length; i++) {
					views[i].startAnimation(animations[i]);
					views[i].setVisibility(View.VISIBLE);
					animations[i].setAnimationListener(animationListener);
				}
			}
		}, 50);
		title.startAnimation(animationTitle);
		
		animationTitle.setAnimationListener(new AnimationListener() {
			
			@Override public void onAnimationStart(Animation animation) {  }
			@Override public void onAnimationRepeat(Animation animation) {  }
			@Override
			public void onAnimationEnd(Animation animation) {
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						isAnimationEnd = true;
						startMainActivity();
					}
				}, 400);
			}
		});
	}
	
	private AnimationListener animationListener = new AnimationListener() {
		
		@Override public void onAnimationStart(Animation animation) {  }
		@Override public void onAnimationRepeat(Animation animation) {  }
		
		@Override
		public void onAnimationEnd(Animation animation) {
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					cnt++;
//					cntText.setText(String.valueOf(cnt));
				}
			});
		}
	};
	
	private void startMainActivity() {
		if(isAnimationEnd) {
			startActivity(new Intent(SplashActivity.this, MainActivity.class));
			finish();
		}
	}
}
