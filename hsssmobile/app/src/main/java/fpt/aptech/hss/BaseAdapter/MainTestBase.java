package fpt.aptech.hss.BaseAdapter;

import android.content.Context;
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

public class MainTestBase extends RecyclerView.Adapter<MainTestBase.MyViewHolder>{



    private List<ModelString> modelString;
    Context context;

    public MainTestBase(List<ModelString> modelString, Context context) {
        this.modelString = modelString;
        this.context = context;
    }

    @NonNull
    @Override
    public MainTestBase.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_test_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainTestBase.MyViewHolder holder, int position) {

        ModelString modelStringsa = modelString.get(position);
        holder.title.setText(modelStringsa.getData2());
        holder.date.setText(modelStringsa.getData3());

        Glide.with(context)
                .load("http://" + ConfigData.IP + ":7777/" + modelStringsa.getData1())
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
        TextView title,date;
        ImageView image;
        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.Title);
            date = view.findViewById(R.id.Date);
            image = view.findViewById(R.id.imageView);

        }
    }
}
