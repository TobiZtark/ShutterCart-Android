package com.app.shuttercart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.shuttercart.R;
import com.app.shuttercart.model.Navigation;

import java.util.List;

/**
 * Created by Mishael on 3/4/2018.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.ViewHolder> {

    private List<Navigation> navigationList;
    private LayoutInflater layoutInflater;
    private Context context;

    public NavigationAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setNavigation(List<Navigation> navigationList){
        this.navigationList = navigationList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item_navigation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Navigation navigation = navigationList.get(position);

        holder.icon.setImageResource(navigation.getIcon());
        holder.title.setText(navigation.getTitle());
    }

    @Override
    public int getItemCount() {
        return navigationList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
