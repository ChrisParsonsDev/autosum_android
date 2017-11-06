package com.gmail.christopherkparsons.autosumandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variable Declaration
    private boolean resultIsFlashing;
    private Animation animation;
    private NumberData numberLogic;
    private TextView resultTextView;
    private EditText editTextOne, editTextTwo, editTextThree, editTextFour, editTextFive, editTextSix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialise NumberData object
        numberLogic = new NumberData();

        //Get variables for all views
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        editTextOne = (EditText) findViewById(R.id.editTextOne);
        editTextTwo = (EditText) findViewById(R.id.editTextTwo);
        editTextThree = (EditText) findViewById(R.id.editTextThree);
        editTextFour = (EditText) findViewById(R.id.editTextFour);
        editTextFive = (EditText) findViewById(R.id.editTextFive);
        editTextSix = (EditText) findViewById(R.id.editTextSix);

        //Set listener for all EditText
        editTextOne.addTextChangedListener(getTextWatcher(editTextOne));
        editTextTwo.addTextChangedListener(getTextWatcher(editTextTwo));
        editTextThree.addTextChangedListener(getTextWatcher(editTextThree));
        editTextFour.addTextChangedListener(getTextWatcher(editTextFour));
        editTextFive.addTextChangedListener(getTextWatcher(editTextFive));
        editTextSix.addTextChangedListener(getTextWatcher(editTextSix));

        //Initialise the flashing state to false
        resultIsFlashing = false;
        //Create animation to show/hide view
        animation = new AlphaAnimation(1, 0);
        animation.setDuration(500); // 500ms duration
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
    }

    // Function used to control the flashing of the resultTextView on click.
    public void flashResult(View v) {
        //If the result is not flashing, flash
        if (!resultIsFlashing) {
            resultIsFlashing = true;
            v.startAnimation(animation);
        } else {
            v.clearAnimation();
            resultIsFlashing = false;
        }
    }

    // Update result view based on new value
    public void updateResult() {
        resultTextView.setText(numberLogic.getResult());
    }

    // Custom TextWatcher for all EditText fields
    // This prevents code bloat from adding/implementing onTextChanged for all EditTexts
    TextWatcher getTextWatcher(final EditText editTextView) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // update value in NumberData based on the current value of text field
                numberLogic.setEditTextValue(editTextView);
                // Update total
                updateResult();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
    }
}
