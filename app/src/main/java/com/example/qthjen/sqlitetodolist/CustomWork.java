package com.example.qthjen.sqlitetodolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomWork extends BaseAdapter {

    List<Work> mList;
    int mLayout;
    MainActivity mContext;
    int position;

    public CustomWork(MainActivity context, int layout, List<Work> list) {

        mContext = context;
        mLayout = layout;
        mList = list;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {

        TextView tvWork;
        ImageView ivDelete;
        ImageView ivEdit;

    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = new ViewHolder();
        position = i;

        if ( view == null) {

            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(mLayout, null);
            viewHolder.tvWork = (TextView) view.findViewById(R.id.tvWork);
            viewHolder.ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
            viewHolder.ivEdit = (ImageView) view.findViewById(R.id.ivEdit);
            view.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvWork.setText(mList.get(i).getNameWork());

        viewHolder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.EditDialog(mList.get(i).getNameWork(), mList.get(i).getIdWork());
            }
        });

        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.DialogDelete(mList.get(i).getNameWork(), mList.get(i).getIdWork());
            }
        });

        return view;
    }

}
