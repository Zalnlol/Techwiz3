package fpt.aptech.hss.BaseAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;

public class MainTestBase extends RecyclerView.Adapter<MainTestBase.MyViewHolder>{



    private List<ModelString> modelString;

    public MainTestBase(List<ModelString> modelString) {
        this.modelString = modelString;
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
        holder.image.setImageResource(Integer.parseInt(modelStringsa.getData1()));
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
