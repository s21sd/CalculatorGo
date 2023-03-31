package com.example.calculatorgo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_tv,solution_tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv=findViewById(R.id.result_tv);
        solution_tv=findViewById(R.id.solution_tv);

        assignId(R.id.button_c);
        assignId(R.id.openbraket);
        assignId(R.id.closebraket);

        assignId(R.id.button_1);
        assignId(R.id.button_2);
        assignId(R.id.button_3);
        assignId(R.id.button_4);
        assignId(R.id.button_5);
        assignId(R.id.button_6);
        assignId(R.id.button_7);
        assignId(R.id.button_8);
        assignId(R.id.button_9);
        assignId(R.id.zero);

        assignId(R.id.devide);
        assignId(R.id.multiply);
        assignId(R.id.add);
        assignId(R.id.subtract);
        assignId(R.id.equals);
        assignId(R.id.ac);



    }



    void assignId(int id)
    {
        MaterialButton btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText=button.getText().toString();

        String dataCalculate=solution_tv.getText().toString();

        if(buttonText.equals("AC"))
        {
            solution_tv.setText(" ");
            result_tv.setText(" ");
            return;
        }
        if(buttonText.equals("="))
        {
            solution_tv.setText(result_tv.getText());
            return;
        }
        if(buttonText.equals("C"))
        {
            if(dataCalculate.length()>1)
            {
                dataCalculate=dataCalculate.substring(0,dataCalculate.length()-1);
            }
            else if(dataCalculate.length()==1)
            {
                solution_tv.setText(" ");
                result_tv.setText(" ");
                return;

            }



        }
        else{
            dataCalculate=dataCalculate+buttonText;
        }
        solution_tv.setText(dataCalculate);
        String finalresult=getResult(dataCalculate);

        if(!finalresult.equals("Err"))
        {
            result_tv.setText(finalresult);
        }

    }
    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalresult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalresult.endsWith(".0"))
            {
                finalresult=finalresult.replace(".0","");
            }
            return finalresult;
        } catch (Exception e) {
            return "Err";
        }
    }
}