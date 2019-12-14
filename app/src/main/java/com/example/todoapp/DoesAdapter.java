package com.example.todoapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DoesAdapter extends RecyclerView.Adapter<DoesAdapter.MyViewHolder> {
    Context context;
    ArrayList<ToDo> toDo;

    public DoesAdapter(Context c, ArrayList<ToDo> p)
    {
        context = c;
        toDo = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_does,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titledoes.setText(toDo.get(position).getTitledoes());
        holder.descdoes.setText(toDo.get(position).getDescdoes());
        holder.datedoes.setText(toDo.get(position).getDatedoes());

        final String getTitleDoes = toDo.get(position).getTitledoes();
        final String getDescDoes = toDo.get(position).getDescdoes();
        final String getDateDoes = toDo.get(position).getDatedoes();
        final String getKeyDoes = toDo.get(position).getKeydoes();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context,EditTaskDesk.class);
                aa.putExtra("titledoes",getTitleDoes);
                aa.putExtra("descdoes",getDescDoes);
                aa.putExtra("datedoes",getDateDoes);
                aa.putExtra("keydoes",getKeyDoes);
                context.startActivity(aa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return toDo.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView titledoes,descdoes,datedoes,keydoes;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titledoes = itemView.findViewById(R.id.titledoes);
            descdoes = itemView.findViewById(R.id.descdoes);
            datedoes = itemView.findViewById(R.id.datedoes);
        }
    }
}
