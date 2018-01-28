package com.frozencore.realtime.realtimedatabase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.frozencore.realtime.realtimedatabase.R;

import java.util.ArrayList;

/**
 * This Adapter is to show the text of the messages in a ListView
 */

public class TextAdapter extends BaseAdapter {
    ArrayList<String> arrayList;
    LayoutInflater layoutInflater;
    public TextAdapter(Context context, ArrayList<String> textList)
    {
        arrayList = textList;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ContenedorView contenedorView = null;
        if(view == null)
        {
            view = layoutInflater.inflate(R.layout.adapter_text,null);
            contenedorView = new ContenedorView();
            contenedorView.tvText = view.findViewById(R.id.etMessage);
            contenedorView.tvText.setText(arrayList.get(i));
            return view;
        }
        return view;
    }
    private class ContenedorView{
        TextView tvText;
    }
}
