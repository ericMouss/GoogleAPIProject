package com.example.googleapiproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {

    private final static String TEXT_SAVE = "TEXT_SAVE";

    private TextView tv;
    private int i;
    private String monText;

    public FirstFragment() {
        // Required empty public constructor
        i = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        i++;
        tv = (TextView) view.findViewById(R.id.tv);
        Log.w("TAG", "BlankFragment.onCreateView() : " + i);

        if (savedInstanceState != null) {
            monText = savedInstanceState.getString(TEXT_SAVE);
        }

        return view;
    }
    // methode qui sauvegarde le texte de notre fragment pour les rotations d'écran
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_SAVE, monText);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.w("TAG", "BlankFragment.onResume()");

        tv.setText(monText);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.w("TAG", "BlankFragment.onPause()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.w("TAG", "BlankFragment.onDestroyView()");
    }

    public void rafraichirEcran() {
        tv.setText(monText);
    }

    public void setMonText(String monText) {
        this.monText = monText;
    }
}
