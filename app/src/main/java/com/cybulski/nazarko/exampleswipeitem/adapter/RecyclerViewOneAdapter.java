package com.cybulski.nazarko.exampleswipeitem.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cybulski.nazarko.exampleswipeitem.R;
import com.cybulski.nazarko.exampleswipeitem.utils.SizeConverter;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.HashMap;

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
  Button buttonUndo;
  LinearLayout linearLayoutContent;




  public SimpleViewHolder(View itemView) {
    super(itemView);
    swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
    avatarimage = (CircleImageView) itemView.findViewById(R.id.avatar);
    textViewData = (TextView) itemView.findViewById(R.id.text_data);
    textViewChecked  = (TextView)itemView.findViewById(R.id.checked);
    textViewchekin = (TextView) itemView.findViewById(R.id.check_in);
    linearLayoutUndo = (LinearLayout)itemView.findViewById(R.id.swipe_undo);
    buttonUndo = (Button)itemView.findViewById(R.id.undo);
    linearLayoutContent = (LinearLayout)itemView.findViewById(R.id.linearlayoutcontent);


//    itemView.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View view) {
//        Log.d(getClass().getSimpleName(), "onItemSelected: " + textViewData.getText().toString());
//        Toast.makeText(view.getContext(), "onItemSelected: " + textViewData.getText().toString(), Toast.LENGTH_SHORT).show();
//      }
//    });
  }
}

  private Context mContext;
  private ArrayList<String> mDataset;
  private HashMap<String,String> mDataHashMap = new HashMap<>();

  SwipeLayout  previos;

  final int width ;
  final int heigth;

  final View.OnTouchListener  touchListener = new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
      deleteitem();
      return false;
    }
  };


  //protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

  public RecyclerViewOneAdapter(Context context, ArrayList<String> objects) {
    this.mContext = context;
    this.mDataset = objects;
    width = SizeConverter.Dp2px(80,mContext);
    heigth  = SizeConverter.Dp2px(80,mContext);
    for (int i=0;i<mDataset.size();i++){
      mDataHashMap.put(mDataset.get(i),RecyclerViewTwoAdapter.URL_IMAGE_STUB[i % 4]);


    }

  }

  @Override
  public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_one, parent, false);
    return new SimpleViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
    final String item = mDataset.get(position);
    viewHolder.swipeLayout.close();
    viewHolder.textViewchekin.setVisibility(View.VISIBLE);
    viewHolder.swipeLayout.findViewById(R.id.left_swipe).setLayoutParams(new FrameLayout.LayoutParams(width, heigth));
    viewHolder.linearLayoutUndo.setVisibility(View.GONE);
    viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
    viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.swipeLayout.findViewById(R.id.left_swipe));
    viewHolder.swipeLayout.setRightSwipeEnabled(false);



    viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
      @Override
      public void onOpen(SwipeLayout layout) {

        //YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
      }

      @Override
      public void onClose(SwipeLayout layout) {
        viewHolder.textViewchekin.setVisibility(View.VISIBLE);
        viewHolder.swipeLayout.findViewById(R.id.left_swipe).setLayoutParams(new FrameLayout.LayoutParams(width, heigth));
        viewHolder.linearLayoutUndo.setVisibility(View.GONE);
        openPosution = -1;

      }

    });

    viewHolder.buttonUndo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        viewHolder.swipeLayout.close();
      }
    });

    viewHolder.avatarimage.setOnTouchListener(touchListener);
    viewHolder.textViewData.setOnTouchListener(touchListener);
    viewHolder.linearLayoutContent.setOnTouchListener(touchListener);


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
        }, 50);

        mItemManger.removeShownLayouts(viewHolder.swipeLayout);
        openPosution = position;


      }
    });

//    Picasso.with(mContext).load(mDataHashMap.get(item)).into(new Target() {
//      @Override
//      public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//        viewHolder.avatarimage.setImageBitmap(bitmap);
//
//      }
//
//      @Override
//      public void onBitmapFailed(Drawable errorDrawable) {
//
//      }
//
//      @Override
//      public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//      }
//    });

    Log.d("Size","1 "+item +" : "+viewHolder.avatarimage.getWidth()+" : "+viewHolder.avatarimage.getHeight()+" : "+mDataHashMap.get(item));
    Picasso.with(mContext).load(mDataHashMap.get(item)).into(viewHolder.avatarimage, new Callback() {
      @Override
      public void onSuccess() {
        Log.d("Size",item +" : "+viewHolder.avatarimage.getWidth()+" : "+viewHolder.avatarimage.getHeight()+" : "+mDataHashMap.get(item));
        viewHolder.avatarimage.setVisibility(View.VISIBLE);
      }

      @Override
      public void onError() {

      }
    });


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

  int openPosution =-1;


  public  void deleteitem( ){
    if (openPosution!=-1) {
        mDataHashMap.remove(mDataset.get(openPosution));
        mDataset.remove(openPosution);
        notifyItemRemoved(openPosution);
        notifyItemRangeChanged(openPosution, mDataset.size());
        mItemManger.closeAllItems();
        openPosution  = -1;
    }
  }


}
