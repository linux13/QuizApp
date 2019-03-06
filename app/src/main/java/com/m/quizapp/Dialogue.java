package com.m.quizapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

public class Dialogue extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("About")
                .setMessage("This app is developed only for learning purposes.\n\n" +
                                "Developed by \n"+
                        "Md Asfak Hussain Shafi\n")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }
}
