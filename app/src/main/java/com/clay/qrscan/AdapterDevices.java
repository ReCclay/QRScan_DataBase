package com.clay.qrscan;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class AdapterDevices extends BaseAdapter {

    public onItemSwitchClickListener mItemSwitchClickListener;
    private Context mContext;
    private ArrayList<ArrayList<Object>> mList = new ArrayList<ArrayList<Object>>();

    public AdapterDevices(Context context, ArrayList<ArrayList<Object>> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int arg0, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_main, null);
            viewHolder.mConstraintLayout = view.findViewById(R.id.ConstraintLayout1);
            viewHolder.imageView1 = (ImageView) view.findViewById(R.id.imageViewAdapMain1);
            viewHolder.textView1 = (TextView) view.findViewById(R.id.textViewAdapMain1);
            viewHolder.imageView2 = (ImageView) view.findViewById(R.id.imageViewAdapMain2);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textView1.setText((String)mList.get(arg0).get(0));

        if (((String)mList.get(arg0).get(1)).equals("0")) {
            viewHolder.imageView2.setImageResource(R.mipmap.switchoff);
        }
        else {
            viewHolder.imageView2.setImageResource(R.mipmap.switchon);
        }

        viewHolder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mItemSwitchClickListener.onClick(v,arg0,(String)mList.get(arg0).get(0),(String)mList.get(arg0).get(1));
            }
        });
        return view;
    }


    public interface onItemSwitchClickListener{
        void onClick(View imageView, int index, String DeviceName, String isClick);
    }

    public void setonItemSwitchClickListener(onItemSwitchClickListener mItemSwitchClickListener){
        this.mItemSwitchClickListener = mItemSwitchClickListener;
    }

    class  ViewHolder{
        ConstraintLayout mConstraintLayout;
        ImageView imageView1;
        TextView textView1;
        ImageView imageView2;
    }

}
