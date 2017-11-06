package com.gmail.christopherkparsons.autosumandroid;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/**
 * Created by chrisparsons on 06/11/2017.
 */

public class NumberData {
    //Stores latest result value
    private double currentResult = 0;
    //Stores values for all the EditText fields
    private double editTextOneValue, editTextTwoValue, editTextThreeValue,
            editTextFourValue, editTextFiveValue, editTextSixValue;


    //Returns the latest result value as a string
    public String getResult(){
        return Double.toString(currentResult);
    }

    //Sets the variable for a text field based on view
    public void setEditTextValue(EditText v){
        //get ID of calling view
        int viewID = v.getId();

        //Cast EditText value to a double (default 0.0)
        Double valueFromView;
        if(!TextUtils.isEmpty(v.getText().toString())){
            valueFromView = Double.parseDouble(v.getText().toString());
        } else {
            valueFromView = 0.0;
        }

        //Switch on view id and update stored variable accordingly
        switch (viewID){
            case R.id.editTextOne: editTextOneValue = valueFromView;break;
            case R.id.editTextTwo: editTextTwoValue = valueFromView;break;
            case R.id.editTextThree: editTextThreeValue = valueFromView;break;
            case R.id.editTextFour: editTextFourValue = valueFromView;break;
            case R.id.editTextFive: editTextFiveValue = valueFromView;break;
            case R.id.editTextSix: editTextSixValue = valueFromView;break;
        }

        computeResult();
    }

    // Function sums values in all the EditText fields and sets currentResult accordingly
    private void computeResult(){
        this.currentResult = this.editTextOneValue + this.editTextTwoValue + this.editTextThreeValue +
                this.editTextFourValue + this.editTextFiveValue + this.editTextSixValue;
    }
}
