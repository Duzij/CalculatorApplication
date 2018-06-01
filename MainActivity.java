package com.example.duzm00.calculatorapplication;

import android.app.FragmentManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements CalculationFormFragment.OnCalculationSentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.mainFragmentLayout, new CalculationFormFragment())
                .commit();
    }


    @Override
    public void onCalculationSent(String num1, String num2) {

        ResultFragment f = new ResultFragment();
        Bundle b = new Bundle();
        b.putString(ResultFragment.NUM_1, num1);
        b.putString(ResultFragment.NUM_2, num2);
        f.setArguments(b);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.mainFragmentLayout, f)
                .commit();
    }
}
