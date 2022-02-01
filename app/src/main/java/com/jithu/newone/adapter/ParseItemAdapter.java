package com.jithu.newone.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jithu.newone.R;
import com.jithu.newone.model.ParseItemModel;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class ParseItemAdapter extends RecyclerView.Adapter<ParseItemAdapter.ViewHolder> {

    private ArrayList<ParseItemModel> parseItemModelArrayList;
    private Context context;

    public ParseItemAdapter(ArrayList<ParseItemModel> parseItemModelArrayList, Context context) {
        this.parseItemModelArrayList = parseItemModelArrayList;
        this.context = context;
    }


    @NotNull
    @Override
    public ParseItemAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parse_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ParseItemAdapter.ViewHolder holder, int position) {
        ParseItemModel parseItemModel = parseItemModelArrayList.get(position);
        holder.name.setText(parseItemModel.getName());
        holder.rating.setText(parseItemModel.getRating());
        holder.close.setText(parseItemModel.getClose());

        holder.dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = "http://www.google.com" + parseItemModel.getDirection();
                Uri uri = Uri.parse(u);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                view.getContext().startActivity(intent);
            }
        });
        Log.e("TAG", "onBindViewHolder: "+parseItemModelArrayList.toString() );
    }

    @Override
    public int getItemCount() {
        return parseItemModelArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name,rating,distance,close,dir;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            rating=itemView.findViewById(R.id.rate_value);
            close=itemView.findViewById(R.id.close);
            dir=itemView.findViewById(R.id.dir);
        }

        @Override
        public void onClick(View view) {

        }
    }
}

