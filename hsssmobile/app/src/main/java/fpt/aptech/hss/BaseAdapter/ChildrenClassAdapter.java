package fpt.aptech.hss.BaseAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import fpt.aptech.hss.Screen.MainParentActivity;
import fpt.aptech.hss.Screen.MyclasroomDetail;

public class ChildrenClassAdapter extends RecyclerView.Adapter<ChildrenClassAdapter.ChildrenClassHolder>{
    List<ModelString> classlist;
    Context context;

    public ChildrenClassAdapter(List<ModelString> classlist, Context context) {
        this.classlist=classlist;
        this.context=context;
    }

    @NonNull
    @Override
    public ChildrenClassAdapter.ChildrenClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_list_classroom,parent,false);
        return new ChildrenClassAdapter.ChildrenClassHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildrenClassAdapter.ChildrenClassHolder holder, int position) {
        ModelString modelStringsa = classlist.get(position);
        holder.tvname.setText(modelStringsa.getData1());
//        holder.image.setImageResource(Integer.parseInt(modelStringsa.getData4()));


        Glide.with(context)
                .load("http://" + ConfigData.IP + ":8080/KSS/"+modelStringsa.getData5())
//                .transform(new RoundedCorners(radius))
//                .transform(new CircleCrop())
                .override(600, 600)
//                .error(R.drawable.icon5)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return classlist.size();
    }

    public class ChildrenClassHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvname;
        public ChildrenClassHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.classroom_image_item);
            tvname=itemView.findViewById(R.id.classroom_text_item);



        }
    }
}
