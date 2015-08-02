package com.alienske.nairobiweather;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class MainActivity extends Activity {
    private Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
    }


    @Override
    protected void onStart(){
        super.onStart();

        Button mButtoncloudy = (Button) findViewById(R.id.buttoncloudy);
        Button mButtonrainy = (Button) findViewById(R.id.buttonrainy);
        Button mButtonsunny = (Button) findViewById(R.id.buttonsunny);
        Button mButtonfoggy = (Button) findViewById(R.id.buttonfoggy);
        final TextView mTextCondition = (TextView) findViewById(R.id.textView);
        mRef = new Firebase("https://nairobiweather.firebaseio.com/");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               String newCondition = (String) dataSnapshot.getValue();
                mTextCondition.setText(newCondition);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mButtoncloudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.setValue("It's Cloudy");
            }
        });
        mButtonfoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.setValue("It's Foggy");
            }
        });
        mButtonsunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.setValue("It's Sunny");
            }
        });
        mButtonrainy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.setValue("It's Raining");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
