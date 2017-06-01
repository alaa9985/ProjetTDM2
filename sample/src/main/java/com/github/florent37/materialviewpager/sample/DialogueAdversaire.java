/**
 * Created by alaa eddine on 28/05/2017.
 */

package com.github.florent37.materialviewpager.sample;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;


/**
 * Created by Douaa on 29/04/2017.
 */

public class DialogueAdversaire extends DialogFragment {

    private EditText mNom;
    private EditText mPrenom;
    private EditText mNumeroAssurance;
    private EditText mMatricule;
    private EditText mPermet;

    private Dossier dossier;
    private Adversaire adversaire;

    public Adversaire getAdversaire() {
        return this.adversaire;
    }

    public void setAdversaire(Adversaire adversaire) {
        this.adversaire = adversaire;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialogue_adversaire, null);

        mNom= (EditText) v.findViewById(R.id.etNom);

        mPrenom  = (EditText)v.findViewById(R.id.etPrenom);

        mNumeroAssurance = (EditText)v.findViewById(R.id.etNumeroAssurance);

        mMatricule=(EditText)v.findViewById(R.id.etMatricule);

        mPermet=(EditText)v.findViewById(R.id.etPermet);


        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());

        builder.setView(v);

        builder.setTitle("Installation");

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                adversaire=new Adversaire(mNom.getText().toString(),mPrenom.getText().toString(),mMatricule.getText().toString(),mNumeroAssurance.getText().toString(),mPermet.getText().toString());


            }
        });

        return builder.create();

    }
}

