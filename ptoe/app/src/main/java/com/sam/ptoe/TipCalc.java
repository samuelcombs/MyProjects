package com.sam.ptoe;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class TipCalc extends Activity implements SeekBar.OnSeekBarChangeListener, TextView.OnEditorActionListener {

    // define variables for the widgets
    EditText billAmountEditText;
    TextView percentTextView;
    TextView tipTextView;
    TextView totalTextView;
    //Button   applyButton, applySeek;
    SeekBar  percentSeek;

    // define the SharedPreferences object
    SharedPreferences savedValues;

    // define instance variables that should be saved
    String billAmountString = "";
    float tipPercent = 15;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tip_calc);

        // get references to the widgets
        billAmountEditText = findViewById(R.id.billAmountEditText);
        percentTextView = findViewById(R.id.percentTextView);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);
        //applyButton = (Button) findViewById(R.id.applyButton);
        //applySeek = (Button) findViewById(R.id.applySeek);
        percentSeek = findViewById(R.id.percentSeek);

        // set the listeners
        billAmountEditText.setOnEditorActionListener(this);
        //applyButton.setOnClickListener(this);
        //applySeek.setOnClickListener(this);
        //percentTextView.setOnEditorActionListener(this);
        percentSeek.setOnSeekBarChangeListener(this);

        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
        percentSeek.setProgress((int)(tipPercent));
    }

    @Override
    public void onPause() {
        // save the instance variables
        Editor editor = savedValues.edit();
        editor.putFloat("tipPercent", tipPercent);
        editor.apply();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // get the instance variables
        tipPercent = savedValues.getFloat("tipPercent", 15f);
        DecimalFormat df = new DecimalFormat("#.##");
        String tipP = df.format(tipPercent);
        percentTextView.setText(tipP);
        // calculate and display
        calculateAndDisplay();
    }

    public void calculateAndDisplay() {
        // get the bill amount
        billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        if (billAmountString.equals("")) {
            billAmount = 0;
        }
        else {
            billAmount = Float.parseFloat(billAmountString);
        }
        // calculate tip and total
        float tipAmount = billAmount * tipPercent/100;
        float totalAmount = billAmount + tipAmount;

        // display the other results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

        //NumberFormat percent = NumberFormat.getPercentInstance();
        //
        //String t = percentTextView.getText().toString();
        //tipPercent = Float.parseFloat(t);
        //tipPercent = Float.valueOf(percentTextView.getText().toString());
        DecimalFormat df = new DecimalFormat("#.##");
        String tipP = df.format(tipPercent);
        percentTextView.setText(tipP);
        percentSeek.setProgress((int)(tipPercent));
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED ||
                event.getAction() == KeyEvent.ACTION_DOWN) {
            calculateAndDisplay();

        }

        return false;
    }


    @Override
    public void onStartTrackingTouch(SeekBar seek){

    }
    @Override
    public void onProgressChanged(SeekBar seek, int progress, boolean fromUser){
        //percentTextView.setText(progress + "%");
        //NumberFormat percent = NumberFormat.getPercentInstance();
        //percentTextView.setText(percent.format(tipPercent));
        tipPercent=(float)progress;
        calculateAndDisplay();
    }
    @Override
    public void onStopTrackingTouch(SeekBar seek){
        calculateAndDisplay();
    }

    /*
    @Override
    //Don't need the apply buttons, it calculates when the seekbar moves
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.applySeek:
                calculateAndDisplay();
                break;
            case R.id.applyButton:
                calculateAndDisplay();
                break;
        }
    }
    */
}