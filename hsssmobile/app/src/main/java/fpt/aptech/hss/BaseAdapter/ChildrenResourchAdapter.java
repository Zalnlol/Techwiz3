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
import fpt.aptech.hss.Screen.TestDetailsActivity;

public class ChildrenResourchAdapter extends RecyclerView.Adapter<ChildrenResourchAdapter.ChildrenResourchHolder> {
    List<ModelString> Testlist;
    Context context;

    public ChildrenResourchAdapter(List<ModelString> classlist, Context context) {
        this.Testlist=classlist;
        this.context=context;
    }

    @NonNull
    @Override
    public ChildrenResourchAdapter.ChildrenResourchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_list_reource_layout,parent,false);
        return new ChildrenResourchAdapter.ChildrenResourchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildrenResourchHolder holder, int position) {
        ModelString modelStringsa = Testlist.get(position);
        holder.tvtitle.setText(modelStringsa.getData5());
//        holder.image.setImageResource(Integer.parseInt(modelStringsa.getData4()));


        Glide.with(context)
                .load("http://" + ConfigData.IP + ":7777/"+modelStringsa.getData1())
//                .transform(new RoundedCorners(radius))
//                .transform(new CircleCrop())
                .override(600, 600)
//                .error(R.drawable.icon5)
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return Testlist.size();
    }

    public class ChildrenResourchHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvtitle;
        public ChildrenResourchHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            tvtitle=itemView.findViewById(R.id.Title);
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    ModelString classed =Testlist.get(getAdapterPosition());
//                    String text = classed.getData1().toString();
//                    String idSelect = classed.getData5();
////                    Intent intent = new Intent(context, TestDetailsActivity.class);
//////                    intent.putExtra("idSelect", idSelect);
////                    context.startActivity(intent);
//                }
//            });

        }
    }
}
