package com.cybulski.nazarko.exampleswipeitem.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cybulski.nazarko.exampleswipeitem.R;
import com.cybulski.nazarko.exampleswipeitem.adapter.RecyclerViewOneAdapter;
import com.daimajia.swipe.util.Attributes;
import com.hudomju.swipe.OnItemClickListener;
import com.hudomju.swipe.SwipeToDismissTouchListener;
import com.hudomju.swipe.SwipeableItemClickListener;
import com.hudomju.swipe.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by nazarko on 4/4/16.
 */
public class OneFragment extends Fragment {

  private RecyclerView recyclerView;
  private RecyclerView.Adapter mAdapter;

  private ArrayList<String> mDataSet;


  public OneFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_one, container, false);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    // Layout Managers:
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    // Item Decorator:
    //recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider)));
    //recyclerView.setItemAnimator(new FadeInLeftAnimator());

    // Adapter:
    String[] adapterData = new String[]{"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
    mDataSet = new ArrayList<String>(Arrays.asList(adapterData));
    mAdapter = new RecyclerViewOneAdapter(getActivity(), mDataSet);
    ((RecyclerViewOneAdapter) mAdapter).setMode(Attributes.Mode.Single);
    recyclerView.setAdapter(mAdapter);

        /* Listeners */
    recyclerView.setOnScrollListener(onScrollListener);

//    final SwipeToDismissTouchListener<RecyclerViewAdapter> touchListener =
//        new SwipeToDismissTouchListener<>(
//            new RecyclerViewAdapter(recyclerView),
//            new SwipeToDismissTouchListener.DismissCallbacks<RecyclerViewAdapter>() {
//              @Override
//              public boolean canDismiss(int position) {
//                return true;
//              }
//
//              @Override
//              public void onPendingDismiss(RecyclerViewAdapter recyclerView, int position) {
//
//              }
//
//              @Override
//              public void onDismiss(RecyclerViewAdapter view, int position) {
//                ((RecyclerViewOneAdapter)mAdapter).remove(position);
//              }
//            });
//    touchListener.setDismissDelay(3000);
//    recyclerView.setOnTouchListener(touchListener);
//    // Setting this scroll listener is required to ensure that during ListView scrolling,
//    // we don't look for swipes.
//    recyclerView.setOnScrollListener((RecyclerView.OnScrollListener) touchListener.makeScrollListener());
//    recyclerView.addOnItemTouchListener(new SwipeableItemClickListener(getActivity(),
//        new OnItemClickListener() {
//          @Override
//          public void onItemClick(View view, int position) {
//
//          }
//        }));

    return view;
  }

  RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
      super.onScrollStateChanged(recyclerView, newState);
      Log.e("ListView", "onScrollStateChanged");
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
      super.onScrolled(recyclerView, dx, dy);
      // Could hide open views here if you wanted. //
    }
  };
}
