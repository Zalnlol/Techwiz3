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
import fpt.aptech.hss.Screen.ExamDetailActivity;

public class MainTeacherTestBase extends RecyclerView.Adapter<MainTeacherTestBase.MyViewHolder> {

    Context context;

    private List<ModelString> modelString;

    public MainTeacherTestBase(List<ModelString> modelString, Context context) {
        this.modelString = modelString;
        this.context = context;
    }

    @NonNull
    @Override
    public MainTeacherTestBase.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_test_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainTeacherTestBase.MyViewHolder holder, int position) {

        ModelString modelStringsa = modelString.get(position);
        holder.title.setText(modelStringsa.getData2());
        holder.date.setText(modelStringsa.getData3());

        Glide.with(context)
                .load("http://" + ConfigData.IP + ":8080/KSS/" + modelStringsa.getData1())
//                .transform(new RoundedCorners(radius))
//                .transform(new CircleCrop())
                .override(600, 600)
//                .error(R.drawable.icon5)
                .into(holder.image);


        holder.itemTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExamDetailActivity.class);

                intent.putExtra("data", modelStringsa.getData5());
                intent.putExtra("idExam", modelStringsa.getData4());
                intent.putExtra("idSelect", modelStringsa.getData6());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelString.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, date;
        ImageView image;
        LinearLayout itemTest;

        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.Title);
            date = view.findViewById(R.id.Date);
            image = view.findViewById(R.id.imageView);
            itemTest = view.findViewById(R.id.itemTest);

        }
    }
}
