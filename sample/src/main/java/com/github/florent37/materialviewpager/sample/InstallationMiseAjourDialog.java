package com.github.florent37.materialviewpager.sample;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Douaa on 29/04/2017.
 */

public class InstallationMiseAjourDialog extends DialogFragment {

    private EditText nomInstallation;

    private Spinner etatS;

    private EditText dateVisite;

    private DatabaseReference database;

    private Dossier installation;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.install_dialog, null);


        dateVisite = (EditText)v.findViewById(R.id.dateInstallation);

        Bundle bundle = getArguments();

        int position = bundle.getInt("dossier");

        installation = DossierRecyclerViewAdapter.getItem(position);



        dateVisite.setText(installation.getDate());


        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());

        builder.setView(v);

        builder.setTitle("Mise a jour dossier");

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                database = FirebaseDatabase.getInstance().getReference();




                installation.setDate(dateVisite.getText().toString());



                database.child("dossiers").child(installation.getId()).setValue(installation);
            }
        });

        return builder.create();
    }


}
