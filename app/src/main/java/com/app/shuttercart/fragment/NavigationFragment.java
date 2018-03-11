package com.app.shuttercart.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.shuttercart.R;
import com.app.shuttercart.adapter.NavigationAdapter;
import com.app.shuttercart.model.Navigation;
import com.app.shuttercart.util.Const;
import com.app.shuttercart.util.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment {

    private NavigationAdapter navigationAdapter;

    private NavigationMenuListener navigationMenuListener;

    public interface NavigationMenuListener{
        void onMenuSelected(String menuItem);
    }

    public NavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        navigationAdapter = new NavigationAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(navigationAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String[] title = getTitles();
                navigationMenuListener.onMenuSelected(title[position]);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        loadNavigationMenu();

        return view;
    }

    private void loadNavigationMenu(){
        navigationAdapter.setNavigation(getNavigationData());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NavigationMenuListener) {
            navigationMenuListener = (NavigationMenuListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement NavigationMenuListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigationMenuListener = null;
    }

    public static List<Navigation> getNavigationData(){
        List<Navigation> navigationList = new ArrayList<>();

        int[] imageIds = getImages();
        String[] titles = getTitles();

        for (int i = 0; i < titles.length; i++){
            Navigation navigation = new Navigation();
            navigation.setTitle(titles[i]);
            navigation.setIcon(imageIds[i]);
            navigationList.add(navigation);
        }
        return navigationList;
    }

    private static String[] getTitles() {
        return new String[] {
                Const.TAG_HOME, Const.TAG_RECEIPTS,
                Const.TAG_REBATE_PRODUCTS, Const.TAG_SURVEY,
                Const.TAG_VIDEOS, Const.TAG_WALLET,
                Const.TAG_PROFILE, Const.TAG_SETTINGS,
                Const.TAG_LOGOUT
        };
    }

    private static int[] getImages() {
        return new int[]{
                R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round,R.mipmap.ic_launcher_round,
                R.mipmap.ic_launcher_round
        };
    }


}
