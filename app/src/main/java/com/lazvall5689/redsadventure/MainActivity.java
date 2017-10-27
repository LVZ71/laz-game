package com.lazvall5689.redsadventure;

import android.graphics.Typeface;
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
    final Button[] buttons = new Button[4];
    private ArrayList<Scene> scenes = new ArrayList<>();   // will hold the scenes from the webpage
    public int currentScene = 0; // will hold the current index in the array
    TextView mainTextView;

    // first method called by android when app first starts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTextView = (TextView)findViewById(R.id.textView);
        setButtonListeners(buttons);
        setFontGlobally();
        // grab scenes from webpage
        grabScenesFromWeb();
    }

    // set the button image when held down and when released
    private void setButtonListeners(final Button[] buttons){
        for (int i = 1; i <= 4; i++) {
            // button1 button2 button3 button4
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            buttons[i - 1] = (Button) findViewById(id);
        }

        // for every button do something
        for (final Button btn : buttons) {
            btn.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            btn.setBackgroundResource(R.drawable.button_pressed);
                            break;
                        case MotionEvent.ACTION_UP:
                            btn.setBackgroundResource(R.drawable.button);
                            break;
                    }
                    return false;
                }
            });

            // get the button clicked and do some logic to change the scene
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = getResources().getResourceEntryName(btn.getId());
                    int clicked = StringUtil.parseInt(name.charAt(name.length()-1)+"")-1;
                    Scene current = scenes.get(currentScene);
                    currentScene = current.getRoutes()[clicked];
                    changeScenes(currentScene);

                }
            });
        }
    }

    // call this after scenes are retrieved from webpage
    public void start(){
        changeScenes(0);
        //printArr(scenes);
    }

    // grey out unused buttons
    private void enableButtons(int num){
        int counter = 0;
        for(Button btn: buttons){
            if(counter<num){
                btn.setEnabled(true);
                counter++;
            }
            else{
                btn.setEnabled(false);
            }
        }
    }

    private void setFontGlobally(){
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/font.ttf");
        mainTextView.setTypeface(type);
        for(Button b: buttons){
            b.setTypeface(type);
        }
    }
    // change scene to scenes( numberInArr )
    private void changeScenes(int numberInArr){
        Scene currentScene = scenes.get(numberInArr);
        mainTextView.setText(currentScene.getContent());
        for(int x = 0; x<currentScene.numRoutes();x++){
            Scene path = scenes.get(currentScene.getRoutes()[x]);
            buttons[x].setText(path.getTitle());
        }
        enableButtons(currentScene.numRoutes());
    }

    // print an array of objects
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