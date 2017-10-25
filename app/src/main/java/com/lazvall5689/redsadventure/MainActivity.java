package com.lazvall5689.redsadventure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lazvall5689.redsadventure.web.GetHtml;
import com.lazvall5689.redsadventure.web.OnHtmlRetrieved;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "redsadventure";   // helpful for debugging in Log
    private ArrayList<Scene> scenes = new ArrayList<>();   // will hold the scenes from the webpage

    // first method called by android when app first starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button[] buttons = new Button[4];
        setButtonListeners(buttons);
        // grab scenes from webpage
        grabScenesFromWeb();
    }

    // set the button image when held down and when released
    private void setButtonListeners(Button[] buttons){
        for (int i = 1; i <= 4; i++) {
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            buttons[i - 1] = (Button) findViewById(id);
        }

        for (final Button b : buttons)
            b.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            b.setBackgroundResource(R.drawable.button_pressed);
                            break;
                        case MotionEvent.ACTION_UP:
                            b.setBackgroundResource(R.drawable.button);
                            break;
                    }
                    return false;
                }
            });
    }

    // call this after scenes are retrieved from webpage
    public void start(){
        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(scenes.get(2).getContent());
        printArr(scenes);
    }

    private void printArr(ArrayList list) {
        for (Object a : list) {
            Log.i(TAG, a.toString());
        }
    }

    // only call this once please. don't want to be scraping website more than necessary
    public void grabScenesFromWeb() {
        new GetHtml(new OnHtmlRetrieved() {
            @Override
            public void scenesRetrieved(ArrayList<Scene> list) {
                // set the scenes arrayList to the new one made in GetHtml
                scenes = list;

                // all set up, call start
                start();
            }
        }).execute("https://laz-game.herokuapp.com/");
    }
}