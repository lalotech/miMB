package net.lalotech.mimb.activity;

import net.lalotech.mimb.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ViewFlipper;

public class SplashOne extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 2000;
	ViewFlipper flipper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.flipper);
		flipper = (ViewFlipper) findViewById(R.id.flipper);		

        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                /*Intent mainIntent = new Intent(SplashOne.this,StartActivity.class);
                SplashOne.this.startActivity(mainIntent);
                SplashOne.this.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
                SplashOne.this.finish();*/
            	flipper.setInAnimation(net.lalotech.lib.animation.AnimationUtil.inFromRightAnimation());            	
            	flipper.setOutAnimation(net.lalotech.lib.animation.AnimationUtil.outToLeftAnimation());
            	flipper.showNext();            	
            }
        }, SPLASH_DISPLAY_LENGHT);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() { 
            	startActivity(new Intent(SplashOne.this, CategoryActivity.class));
            }
        }, SPLASH_DISPLAY_LENGHT*2);
	}
public void onClickButton(View v){
    	
    	/*Intent i = new Intent(SplashOne.this, TypeListActivity.class);
    	switch (v.getId()) {
		case R.id.btCat1:
			Util.CATEGORY_SELECTED = 1;			  
			startActivity(i);
			break;
		case R.id.btCat2:
			Util.CATEGORY_SELECTED = 2;			  
			startActivity(i);
			break;
		case R.id.btCat3:
			Util.CATEGORY_SELECTED = 3;			  
			startActivity(i);
			break;
    	}	*/
    }
}
