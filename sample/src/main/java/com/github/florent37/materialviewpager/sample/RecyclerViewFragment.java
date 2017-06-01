package com.github.florent37.materialviewpager.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInRightAnimator;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class RecyclerViewFragment extends Fragment {

    private static final boolean GRID_LAYOUT = false;

    public DossierRecyclerViewAdapter myAdapter;

    @BindView(R.id.recyclerView)

    RecyclerView mRecyclerView;

    List<Dossier> items;

    public static RecyclerViewFragment newInstance(List<Dossier> data) {

        return new RecyclerViewFragment(data);
    }

    public RecyclerViewFragment(List<Dossier> data){
        this.items = data;
    }

    public RecyclerViewFragment(){
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState  ) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setItemAnimator(new ScaleInRightAnimator());

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

        myAdapter = new DossierRecyclerViewAdapter(items);

        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(myAdapter);

        alphaAdapter.setDuration(200);

        alphaAdapter.setFirstOnly(true);

        mRecyclerView.setAdapter(alphaAdapter);

    }

}
