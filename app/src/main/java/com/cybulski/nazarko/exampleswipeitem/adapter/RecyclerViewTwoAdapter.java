package com.cybulski.nazarko.exampleswipeitem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class RecyclerViewTwoAdapter extends RecyclerSwipeAdapter<RecyclerViewTwoAdapter.SimpleViewHolder> {

  public static class SimpleViewHolder extends RecyclerView.ViewHolder {
    SwipeLayout swipeLayout;
    CircleImageView avatarimage;
    TextView textViewData;

    public SimpleViewHolder(View itemView) {
      super(itemView);
      swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
      avatarimage = (CircleImageView) itemView.findViewById(R.id.avatar);
      textViewData = (TextView) itemView.findViewById(R.id.text_data);

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

  //protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

  public RecyclerViewTwoAdapter(Context context, ArrayList<String> objects) {
    this.mContext = context;
    this.mDataset = objects;
  }

  @Override
  public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_two, parent, false);
    return new SimpleViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
    String item = mDataset.get(position);
    viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
    viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewById(R.id.left_swipe));
    viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.rigth_swipe));

    viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
      @Override
      public void onOpen(SwipeLayout layout) {
        //YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
      }
    });
    viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
      @Override
      public void onDoubleClick(SwipeLayout layout, boolean surface) {
        Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
      }
    });

    Picasso.with(mContext).load(URL_IMAGE_STUB[position%4]).into(viewHolder.avatarimage);

    //viewHolder.textViewPos.setText((position + 1) + ".");
    viewHolder.textViewData.setText(item);
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


  public static final String[] URL_IMAGE_STUB = {
      "https://pbs.twimg.com/profile_images/616076655547682816/6gMRtQyY.jpg",
      "http://gradle.org/wp-content/uploads/2015/08/IOS.jpg",
      "http://cypressnorth.com/wp-content/uploads/2013/07/windows-8-icon.png",
      "http://static1.squarespace.com/static/5005afd7e4b0a6953320bf3f/t/528cfce6e4b0c1452601a558/1384971527352/4651364311_f505cc3cc3_o.jpg"
  };
}
