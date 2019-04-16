package com.example.listtraining.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.listtraining.activities.AnimeActivity;
import com.example.listtraining.model.Anime;
import com.example.listtraining.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Anime> mData;
    private RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Anime> mData) {
        this.mContext = mContext;
        // elle concerne la listes affiché dans Recycler View
        this.mData = mData;
        // request option pour Glide
        option=new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        // pour lire le xml ou il ya la liste
        view=layoutInflater.inflate(R.layout.anim_row_item, viewGroup,false);
        final MyViewHolder viewHolder =new MyViewHolder(view);
        // ici c'est quand on appuie sur un element de la liste il déclenche l'action vers le details de l elerment
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, AnimeActivity.class);
                i.putExtra("anime_title",mData.get(viewHolder.getAdapterPosition()).getTitle());
                i.putExtra("anime_maker",mData.get(viewHolder.getAdapterPosition()).getPrincipalMaker());
                i.putExtra("anime_description",mData.get(viewHolder.getAdapterPosition()).getLongTitle());
                i.putExtra("anime_img",mData.get(viewHolder.getAdapterPosition()).getImage());

                mContext.startActivity(i);

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int i) {

        holder.tv_title.setText(mData.get(i).getTitle());
        holder.tv_PrincipalMaker.setText((mData.get(i).getPrincipalMaker()));

        //charger l'image de l'url et la stocker dans l'imageview grâce à Glide

        Glide.with(mContext).load(mData.get(i).getImage()).apply(option).into(holder.img_thumbnail);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv_title;
            TextView tv_PrincipalMaker;
            ImageView img_thumbnail;
            LinearLayout view_container;


        public MyViewHolder(View itemView){
             super(itemView);
             // on instancie les objets en récupérant les id du fichier xml anim_row_item
             view_container = itemView.findViewById(R.id.containeri);
             tv_title=itemView.findViewById(R.id.animeTitle);
             tv_PrincipalMaker=itemView.findViewById(R.id.maker);
             img_thumbnail=itemView.findViewById(R.id.thumbnail);
        }

    }
}
