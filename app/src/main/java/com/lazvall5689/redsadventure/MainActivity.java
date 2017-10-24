package com.lazvall5689.redsadventure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

        // grab scenes from webpage
        grabScenesFromWeb();
    }

    // call this after scenes are retrieved from webpage
    public void start(){
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