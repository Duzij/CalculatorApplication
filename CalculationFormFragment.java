package com.example.duzm00.calculatorapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalculationFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalculationFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculationFormFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnCalculationSentListener mListener;

    public CalculationFormFragment() {
    }

    public static CalculationFormFragment newInstance(String param1, String param2) {
        CalculationFormFragment fragment = new CalculationFormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private boolean inputsAreValid(View view) {
        final TextInputLayout firstNumTextLayout = view.findViewById(R.id.firstNumTextLayout);
        EditText textInputNum1 = firstNumTextLayout.getEditText();

        TextInputLayout secondNumTextLayout = view.findViewById(R.id.secondNumTextLayout);
        EditText textInputNum2 = secondNumTextLayout.getEditText();

        if (TextUtils.isEmpty(textInputNum1.getText().toString())) {
            firstNumTextLayout.setError("First number is required");
            return false;
        } else {
            firstNumTextLayout.setErrorEnabled(false);
        }

        if (TextUtils.isEmpty(textInputNum2.getText().toString())) {
            secondNumTextLayout.setError("Second number is required");
            return false;
        } else {
            secondNumTextLayout.setErrorEnabled(false);
        }

        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculation_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.sendBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputsAreValid(view)){
                final String numText = view.<TextInputLayout>findViewById(R.id.firstNumTextLayout).getEditText().getText().toString();
                final String num2Text = view.<TextInputLayout>findViewById(R.id.secondNumTextLayout).getEditText().getText().toString();
                mListener.onCalculationSent(numText, num2Text);
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCalculationSentListener) {
            mListener = (OnCalculationSentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnCalculationSentListener {
        void onCalculationSent(String num1, String num2);
    }
}
