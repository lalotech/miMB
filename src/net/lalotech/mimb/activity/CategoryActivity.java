package net.lalotech.mimb.activity;

import java.util.ArrayList;

import net.lalotech.mimb.R;
import net.lalotech.mimb.component.AppUtils;
import net.lalotech.mimb.component.CarouselDataItem;
import net.lalotech.mimb.component.CarouselView;
import net.lalotech.mimb.component.CarouselViewAdapter;
import net.lalotech.mimb.component.Singleton;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CategoryActivity extends Activity implements OnItemSelectedListener,OnItemLongClickListener {
	
	Singleton 				m_Inst 					= Singleton.getInstance();
	CarouselViewAdapter 	m_carouselAdapter		= null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //no keyboard unless requested by user
      	getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
      		
        // compute screen size and scaling
     	Singleton.getInstance().InitGUIFrame(this);
     	
     	int padding = m_Inst.Scale(10);
		// create the interface : full screen container
		RelativeLayout panel  = new RelativeLayout(this);
	    panel.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		panel.setPadding(padding, padding, padding, padding);
	    panel.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, 
	    		new int[]{Color.WHITE, Color.GRAY}));
	    setContentView(panel); 
	    
	 // copy images from assets to sdcard
	   AppUtils.AssetFileCopy(this, "/mnt/sdcard/l1.png", "l1.png", false);
	    AppUtils.AssetFileCopy(this, "/mnt/sdcard/l2.png", "l2.png", false);
	    AppUtils.AssetFileCopy(this, "/mnt/sdcard/l3.png", "l3.png", false);
	    AppUtils.AssetFileCopy(this, "/mnt/sdcard/l4.png", "l4.png", false);
	    
	    //Create carousel view documents
	    ArrayList<CarouselDataItem> Docus = new ArrayList<CarouselDataItem>();
	    Docus.add(new CarouselDataItem("/mnt/sdcard/l2.png", 0, ""));
	    Docus.add(new CarouselDataItem("/mnt/sdcard/l3.png", 0, ""));
	    Docus.add(new CarouselDataItem("/mnt/sdcard/l4.png", 0, ""));	    	    
	    Docus.add(new CarouselDataItem("/mnt/sdcard/l1.png", 0, ""));
	   	   
	    // create the carousel
	    CarouselView coverFlow = new CarouselView(this);
        
	    // create adapter and specify device independent items size (scaling)
	    // for more details see: http://www.pocketmagic.net/2013/04/how-to-scale-an-android-ui-on-multiple-screens/
	    m_carouselAdapter =  new CarouselViewAdapter(this,Docus, m_Inst.Scale(400),m_Inst.Scale(300));
        coverFlow.setAdapter(m_carouselAdapter);        
        coverFlow.setSpacing(-1*m_Inst.Scale(170));
        coverFlow.setSelection(Integer.MAX_VALUE / 2, true);
        coverFlow.setAnimationDuration(500);
        coverFlow.setOnItemSelectedListener((OnItemSelectedListener) this);
        coverFlow.setOnItemLongClickListener(this);

        AppUtils.AddView(panel, coverFlow, LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT, 
        		new int[][]{new int[]{RelativeLayout.CENTER_IN_PARENT}},
        		-1, -1); 
    }	

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		 CarouselDataItem docu =  (CarouselDataItem) m_carouselAdapter.getItem((int) arg3);
		 if (docu!=null)
			 Toast.makeText(this, "You've clicked on:"+docu.getDocText(), Toast.LENGTH_SHORT).show();
	}

	public void onNothingSelected(AdapterView<?> arg0) {}	
	

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
				Toast.makeText(this, "clicked on", Toast.LENGTH_SHORT).show();
		return false;
	}


    
}
