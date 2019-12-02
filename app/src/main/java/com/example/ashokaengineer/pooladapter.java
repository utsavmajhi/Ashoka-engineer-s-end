package com.example.ashokaengineer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class pooladapter extends RecyclerView.Adapter<pooladapter.poolViewHolder> {

    private Context mContext;
    private ArrayList<poolitems> mpoolitemslist;

    public pooladapter(Context context,ArrayList<poolitems> poollist)
    {
        mContext=context;
        mpoolitemslist=poollist;

    }

    @NonNull
    @Override
    public poolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.applist_item,parent,false);
        return new poolViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull poolViewHolder holder, int position) {

    poolitems currentitem=mpoolitemslist.get(position);
    String imageurl=currentitem.getmImageurl();
    String poolname=currentitem.getPoolname();
    String poolarea=currentitem.getArea();
    String poollocation=currentitem.getLocation();


        holder.mpoolname.setText(poolname);
        holder.mlocation.setText(poollocation);
        holder.marea.setText(poolarea);
        //Picasso.with(mContext).load(imageurl).into(holder.mimageView);




    }

    @Override
    public int getItemCount() {
        return mpoolitemslist.size();
    }

    public class poolViewHolder extends RecyclerView.ViewHolder{
        public CircleImageView mimageView;
        public TextView mpoolname;
        public TextView mlocation;
        public TextView marea;


        public poolViewHolder(@NonNull View itemView) {
            super(itemView);
            mimageView=itemView.findViewById(R.id.poolimage);
            mpoolname=itemView.findViewById(R.id.pname);
            mlocation=itemView.findViewById(R.id.pooladdresstxt);
            marea=itemView.findViewById(R.id.poolarea);

        }
    }

}
