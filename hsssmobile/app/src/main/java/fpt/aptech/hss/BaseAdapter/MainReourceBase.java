
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

public class MainReourceBase extends RecyclerView.Adapter<MainReourceBase.MyViewHolder>{



    private List<ModelString> modelString;

    public MainReourceBase(List<ModelString> modelString) {
        this.modelString = modelString;
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
        holder.image.setImageResource(Integer.parseInt(modelStringsa.getData1()));
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
