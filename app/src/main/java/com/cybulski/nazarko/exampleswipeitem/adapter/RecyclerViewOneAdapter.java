package com.cybulski.nazarko.exampleswipeitem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cybulski.nazarko.exampleswipeitem.R;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nazarko on 4/4/16.
 */
public class RecyclerViewOneAdapter extends RecyclerSwipeAdapter<RecyclerViewOneAdapter.SimpleViewHolder> {


public static class SimpleViewHolder extends RecyclerView.ViewHolder {
  SwipeLayout swipeLayout;
  CircleImageView avatarimage;
  TextView textViewData;
  TextView textViewChecked;
  LinearLayout linearLayoutUndo;
  TextView textViewchekin;




  public SimpleViewHolder(View itemView) {
    super(itemView);
    swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
    avatarimage = (CircleImageView) itemView.findViewById(R.id.avatar);
    textViewData = (TextView) itemView.findViewById(R.id.text_data);
    textViewChecked  = (TextView)itemView.findViewById(R.id.checked);
    textViewchekin = (TextView) itemView.findViewById(R.id.check_in);
    linearLayoutUndo = (LinearLayout)itemView.findViewById(R.id.swipe_undo);

    itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Log.d(getClass().getSimpleName(), "onItemSelected: " + textViewData.getText().toString());
        Toast.makeText(view.getContext(), "onItemSelected: " + textViewData.getText().toString(), Toast.LENGTH_SHORT).show();
      }
    });
  }
}

private Context mContext;
private ArrayList<String> mDataset;

  SwipeLayout  previos;


  //protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

  public RecyclerViewOneAdapter(Context context, ArrayList<String> objects) {
    this.mContext = context;
    this.mDataset = objects;
  }

  @Override
  public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_one, parent, false);
    return new SimpleViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
    String item = mDataset.get(position);
    viewHolder.textViewchekin.setVisibility(View.VISIBLE);
    viewHolder.swipeLayout.findViewById(R.id.left_swipe).setLayoutParams(new FrameLayout.LayoutParams(160, 80));
    viewHolder.linearLayoutUndo.setVisibility(View.GONE);
    viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
    viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewById(R.id.left_swipe));
    viewHolder.swipeLayout.setRightSwipeEnabled(false);


    viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
      @Override
      public void onOpen(SwipeLayout layout) {
//        if (previos!=layout){
//          if (previos!=null){
//            previos.close();
//            previos = layout;
//          }
//
//        }


        //YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
      }

      @Override
      public void onClose(SwipeLayout layout) {
        viewHolder.textViewchekin.setVisibility(View.VISIBLE);
        viewHolder.swipeLayout.findViewById(R.id.left_swipe).setLayoutParams(new FrameLayout.LayoutParams(160, 80));
        viewHolder.linearLayoutUndo.setVisibility(View.GONE);

      }

    });


    viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
      @Override
      public void onDoubleClick(SwipeLayout layout, boolean surface) {
        Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
      }
    });

    viewHolder.swipeLayout.findViewById(R.id.left_swipe).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        viewHolder.linearLayoutUndo.setVisibility(View.VISIBLE);
        viewHolder.textViewchekin.setVisibility(View.GONE);
        viewHolder.swipeLayout.findViewById(R.id.left_swipe).setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 80));

        viewHolder.swipeLayout.postDelayed(new Runnable() {
          @Override
          public void run() {
            viewHolder.swipeLayout.open(SwipeLayout.DragEdge.Left);
          }
        },50);
      }
    });

    Picasso.with(mContext).load(RecyclerViewTwoAdapter.URL_IMAGE_STUB[position % 5]).into(viewHolder.avatarimage);
    viewHolder.textViewData.setText(item);
    viewHolder.textViewChecked.setText(item);
    mItemManger.bindView(viewHolder.itemView, position);
  }

  @Override
  public int getItemCount() {
    return mDataset.size();
  }

  @Override
  public int getSwipeLayoutResourceId(int position) {
    return R.id.swipe;
  }
}
