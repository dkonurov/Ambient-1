package com.opensoftdev.ambient;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView mainList;
	
	private MainListAdapter mainListAdapter;
	
	String data[] = {"8=э", "8==э", "8===э"};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mainList = (ListView) findViewById(R.id.main_list);
        mainListAdapter = new MainListAdapter(this, data);
        mainList.setAdapter(mainListAdapter);
    }
    
}
