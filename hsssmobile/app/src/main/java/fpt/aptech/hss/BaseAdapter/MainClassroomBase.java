package fpt.aptech.hss.BaseAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import fpt.aptech.hss.Screen.MyclasroomDetail;

public class MainClassroomBase   extends RecyclerView.Adapter<MainClassroomBase.MyViewHolder>{


    Context context;

    private List<ModelString> modelString;

    public MainClassroomBase(List<ModelString> modelString, Context context) {
        this.modelString = modelString;
        this.context = context;
    }

    @NonNull
    @Override
    public MainClassroomBase.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_classroom, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainClassroomBase.MyViewHolder holder, int position) {

        ModelString modelStringsa = modelString.get(position);
        holder.title.setText(modelStringsa.getData3());
//        holder.image.setImageResource(Integer.parseInt(modelStringsa.getData4()));


        Glide.with(context)
                .load("http://" + ConfigData.IP + ":7777/"+modelStringsa.getData4())
//                .transform(new RoundedCorners(radius))
//                .transform(new CircleCrop())
                .override(600, 600)
//                .error(R.drawable.icon5)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return modelString.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.classroom_text_item);
            image = view.findViewById(R.id.classroom_image_item);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MyclasroomDetail.class);
                    ModelString classed =modelString.get(getAdapterPosition());
                    String text = classed.getData3().toString();
                    String idSelect = classed.getData2();
                    intent.putExtra("data", text);
                    intent.putExtra("idSelect", idSelect);
                    context.startActivity(intent);
                }
            });
        }
    }
}
