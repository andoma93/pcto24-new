package com.mariomonaco.pcto2024_droid.ui.segnalazione;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mariomonaco.pcto2024_droid.R;

public class SegnalazioneFragment extends Fragment {

    private SegnalazioneViewModel mViewModel;

    public static SegnalazioneFragment newInstance() {
        return new SegnalazioneFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_segnalazione, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SegnalazioneViewModel.class);
        // TODO: Use the ViewModel
    }

}