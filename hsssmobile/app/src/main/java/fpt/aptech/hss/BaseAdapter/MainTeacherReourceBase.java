
package fpt.aptech.hss.BaseAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import fpt.aptech.hss.Screen.ResourceTeacherActivity;
import fpt.aptech.hss.Screen.SubjectDetailActivity;

public class MainTeacherReourceBase extends RecyclerView.Adapter<MainTeacherReourceBase.MyViewHolder>{



    private List<ModelString> modelString;
    Context context;
    public MainTeacherReourceBase(List<ModelString> modelString, Context context) {
        this.modelString = modelString;
        this.context = context;
    }

    @NonNull
    @Override
    public MainTeacherReourceBase.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_reource_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainTeacherReourceBase.MyViewHolder holder, int position) {

        ModelString modelStringsa = modelString.get(position);
        holder.title.setText(modelStringsa.getData2());


        Glide.with(context)
                .load("http://" + ConfigData.IP + ":7777/"+modelStringsa.getData1())
                .override(600, 600)
                .into(holder.image);

        holder.linear_reource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SubjectDetailActivity.class);
                intent.putExtra("data", modelStringsa.getData2());
                intent.putExtra("idSelect", modelStringsa.getData4());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelString.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        LinearLayout linear_reource;
        MyViewHolder(View view) {
            super(view);

            linear_reource = view.findViewById(R.id.linear_reource);
            title = view.findViewById(R.id.Title);
            image = view.findViewById(R.id.imageView);

        }
    }
}
