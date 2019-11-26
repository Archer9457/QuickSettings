package com.android.quick.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by @author :archer
 * on 2019/11/25
 */
public class AppListAdapter extends BaseAdapter {

    private List<App> appList;
    private LayoutInflater mLayoutInflater;

    public AppListAdapter(Context context, List<App> appList) {
        this.appList = appList;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_app, null);
            viewHolder.label = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.img_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.label.setText(appList.get(position).name);
        viewHolder.imgIcon.setImageResource(appList.get(position).drawable);
        return convertView;
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView label;
        ImageView imgIcon;
    }
}
