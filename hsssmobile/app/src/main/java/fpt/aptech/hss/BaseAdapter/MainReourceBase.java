
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

public class MainReourceBase extends RecyclerView.Adapter<MainReourceBase.MyViewHolder>{

Context context;

    private List<ModelString> modelString;

    public MainReourceBase(List<ModelString> modelString,Context context) {
        this.modelString = modelString;
        this.context = context;
    }

    @NonNull
    @Override
    public MainReourceBase.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_reource_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainReourceBase.MyViewHolder holder, int position) {

        ModelString modelStringsa = modelString.get(position);
        holder.title.setText(modelStringsa.getData2());

        Glide.with(context)
                .load("http://" + ConfigData.IP + ":8080/KSS/" + modelStringsa.getData1())
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
            title = view.findViewById(R.id.Title);
            image = view.findViewById(R.id.imageView);

        }
    }
}
