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
import fpt.aptech.hss.Screen.AccountActivity;
import fpt.aptech.hss.Screen.MyclasroomDetail;

public class StudentClassAdapter extends RecyclerView.Adapter<StudentClassAdapter.StudentClassHolder> {
    List<ModelString> Studentlist;
    Context context;
    String data_, idSelect1;

    public StudentClassAdapter(List<ModelString> Studentlist, Context context, String data_, String idSelect) {
        this.Studentlist=Studentlist;
        this.context=context;
        this.data_ =data_;
        this.idSelect1 =idSelect;
    }

    @NonNull
    @Override
    public StudentClassAdapter.StudentClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.classroom_list,parent,false);
        return new StudentClassAdapter.StudentClassHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentClassHolder holder, int position) {
        ModelString modelStringsa = Studentlist.get(position);
        holder.tvname.setText(modelStringsa.getData2());
//        holder.image.setImageResource(Integer.parseInt(modelStringsa.getData4()));


        Glide.with(context)
                .load("http://" + ConfigData.IP + ":8080/KSS/"+modelStringsa.getData1())
//                .transform(new RoundedCorners(radius))
//                .transform(new CircleCrop())
                .override(600, 600)
//                .error(R.drawable.icon5)
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return Studentlist.size();
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
                    Intent intent = new Intent(context, AccountActivity.class);
                    ModelString classed =Studentlist.get(getAdapterPosition());
                    String text = classed.getData1().toString();
                    String idSelect = classed.getData4();
                    intent.putExtra("data", data_);
                    intent.putExtra("id", idSelect);
                    intent.putExtra("idSelect1", idSelect1);
                    context.startActivity(intent);

                }
            });


        }
    }
}
