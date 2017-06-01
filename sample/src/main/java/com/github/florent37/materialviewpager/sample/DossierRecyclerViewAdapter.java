package com.github.florent37.materialviewpager.sample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import jp.wasabeef.recyclerview.animators.holder.AnimateViewHolder;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class DossierRecyclerViewAdapter extends RecyclerView.Adapter<DossierRecyclerViewAdapter.MyViewHolder> {

    private int expandedPosition = -1;

    private static List<Dossier>  dossiers;



    static final int TYPE_HEADER = 0;

    static final int TYPE_CELL = 1;

    public DossierRecyclerViewAdapter(List<Dossier> dossiers) {
        this.dossiers = dossiers;
    }

    @Override
    public int getItemViewType(int position) {
       // switch (position) {
           // case 0:
                return TYPE_CELL;
           /* default:
                return TYPE_CELL;*/
       // }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                return new MyViewHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                return new MyViewHolder(view) {
                };
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.bind(this.dossiers.get(position));

        View  v = holder.itemView;



        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(v.getContext(),"item "+position+" was clicked ",Toast.LENGTH_SHORT).show();
                InstallationMiseAjourDialog dialog = newInstance(position);

                //FragmentManager manager =

                //android.app.FragmentManager manager = ((Activity) context).getFragmentManager();

                FragmentManager manager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();

                dialog.show(manager,"Dialogs");

            }
        });

    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements AnimateViewHolder {

        private TextView mMontant;

        private TextView mDate;

        private TextView mEtat;

        private Dossier dossier;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.mMontant=(TextView) itemView.findViewById(R.id.montant_id);

            this.mEtat=(TextView) itemView.findViewById(R.id.etat_id);

            this.mDate=(TextView) itemView.findViewById(R.id.date_id);
        }

        public void bind(Dossier dossier)
        {
                this.dossier =  dossier;

                this.mDate.setText("date: "+this.dossier.getDate());

                this.mMontant.setText("montant: "+this.dossier.getMontant());

                this.mEtat.setText("etat: "+this.dossier.getEtat());
        }


        @Override
        public void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {

        }

        @Override
        public void animateRemoveImpl(RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(-itemView.getHeight() * 0.3f)
                    .alpha(0)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }

        @Override
        public void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
            ViewCompat.setTranslationY(itemView, -itemView.getHeight() * 0.3f);
            ViewCompat.setAlpha(itemView, 0);
        }

        @Override
        public void animateAddImpl(RecyclerView.ViewHolder holder, ViewPropertyAnimatorListener listener) {
            ViewCompat.animate(itemView)
                    .translationY(0)
                    .alpha(1)
                    .setDuration(300)
                    .setListener(listener)
                    .start();
        }



    }

    public void remove(int position) {
        dossiers.remove(position);
        notifyItemRemoved(position);
    }

    public void add( Dossier dossier,int position) {
        dossiers.add(position , dossier);
        notifyItemInserted(position);
    }

    public void updateList(List<Dossier> dossiers) {
        if (dossiers.size() != this.dossiers.size() || !this.dossiers.containsAll(dossiers)) {
            this.dossiers = dossiers;
            notifyDataSetChanged();
        }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return this.dossiers.size();
    }

    public static Dossier getItem(int position){
        return dossiers.get(position);
    }


    public InstallationMiseAjourDialog newInstance(int num) {

        InstallationMiseAjourDialog f = new InstallationMiseAjourDialog();

        Bundle args = new Bundle();

        args.putInt("installation", num);

        f.setArguments(args);

        return f;
    }
}