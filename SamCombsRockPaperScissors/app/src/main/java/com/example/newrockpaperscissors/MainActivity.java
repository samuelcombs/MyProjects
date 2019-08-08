/*
 * CIST2373 JAVA PROGRAMMING III
 * Part 2 Android
 * Author: Joshua Hesseman
 * Date:   7/31/2014
 * 
 * Project 5
 */

package com.example.newrockpaperscissors;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Activity which will be used as main entry point for the application.
 * 
 * @author martin
 */
public class MainActivity extends Activity {
    
    /**
     * Method called on application start.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new Panel(this));
    }
}