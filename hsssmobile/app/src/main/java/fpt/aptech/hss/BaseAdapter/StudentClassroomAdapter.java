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
import fpt.aptech.hss.Screen.MyclasroomDetail;

public class StudentClassroomAdapter extends RecyclerView.Adapter<StudentClassroomAdapter.StudentClassHolder> {
    List<ModelString> classlist;
    Context context;

    public StudentClassroomAdapter(List<ModelString> classlist, Context context) {
        this.classlist=classlist;
        this.context=context;
    }

    @NonNull
    @Override
    public StudentClassroomAdapter.StudentClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.classroom_list,parent,false);
        return new StudentClassroomAdapter.StudentClassHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentClassHolder holder, int position) {
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

    public class StudentClassHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvname;
        LinearLayout layout;
        public StudentClassHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.classroom_image_item);
            tvname=itemView.findViewById(R.id.classroom_text_item);
            layout = itemView.findViewById(R.id.Classroomitem);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MyclasroomDetail.class);
                    ModelString classed =classlist.get(getAdapterPosition());
                    String text = classed.getData1().toString();
                    String idSelect = classed.getData6();
                    intent.putExtra("data", text);
                    intent.putExtra("idSelect", idSelect);
                    context.startActivity(intent);

                }
            });


        }
    }
}
