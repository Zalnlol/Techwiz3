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
import fpt.aptech.hss.Screen.TestDetailsActivity;

public class StudentTestAdapter extends RecyclerView.Adapter<StudentTestAdapter.StudentTestHolder>{
    List<ModelString> Studentlist;
    Context context;

    public StudentTestAdapter(List<ModelString> Studentlist, Context context) {
        this.Studentlist=Studentlist;
        this.context=context;
    }

    @NonNull
    @Override
    public StudentTestAdapter.StudentTestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.classroom_list,parent,false);
        return new StudentTestAdapter.StudentTestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentTestHolder holder, int position) {
        ModelString modelStringsa = Studentlist.get(position);
        holder.tvname.setText(modelStringsa.getData1());
//        holder.image.setImageResource(Integer.parseInt(modelStringsa.getData4()));


        Glide.with(context)
                .load("http://" + ConfigData.IP + ":7777/"+modelStringsa.getData4())
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

    public class StudentTestHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvname;
        LinearLayout layout;

        public StudentTestHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.classroom_image_item);
            tvname = itemView.findViewById(R.id.classroom_text_item);
            layout = itemView.findViewById(R.id.Classroomitem);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ModelString classed =Studentlist.get(getAdapterPosition());
                    String text = classed.getData1().toString();
                    String idSelect = classed.getData5();
                    Intent intent = new Intent(context, TestDetailsActivity.class);
                    intent.putExtra("idSelect", idSelect);
                    context.startActivity(intent);

                }
            });


        }
    }
}
