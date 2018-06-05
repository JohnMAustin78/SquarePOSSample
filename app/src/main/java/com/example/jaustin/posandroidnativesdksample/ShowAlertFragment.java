package com.example.jaustin.posandroidnativesdksample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ShowAlertFragment extends DialogFragment
{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        super.onCreateDialog(savedInstanceState);
        return null;
    }

    public Dialog getDialog(Activity activity, String title, String description) {
        return new AlertDialog.Builder(activity, 0)
                .setTitle(title + ": " + description)
                .setPositiveButton("OK", null)
                .create();

    }
}
