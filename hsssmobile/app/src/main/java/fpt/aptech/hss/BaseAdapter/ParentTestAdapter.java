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
import fpt.aptech.hss.Screen.TestDetailsParentActivity;

public class ParentTestAdapter extends RecyclerView.Adapter<ParentTestAdapter.ChildrenTestHolder>{
    List<ModelString> Testlist;
    Context context;

    public ParentTestAdapter(List<ModelString> classlist, Context context) {
        this.Testlist=classlist;
        this.context=context;
    }

    @NonNull
    @Override
    public ParentTestAdapter.ChildrenTestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_list_test_layout,parent,false);
        return new ParentTestAdapter.ChildrenTestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildrenTestHolder holder, int position) {
        ModelString modelStringsa = Testlist.get(position);
        holder.tvtitle.setText(modelStringsa.getData1());
        holder.tvdate.setText(modelStringsa.getData2());
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
        return Testlist.size();
    }

    public class ChildrenTestHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvtitle,tvdate;
        public ChildrenTestHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            tvtitle=itemView.findViewById(R.id.Title);
            tvdate=itemView.findViewById(R.id.Date);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelString classed =Testlist.get(getAdapterPosition());
                    String text = classed.getData1().toString();
                    String idSelect = classed.getData5();
                    Intent intent = new Intent(context, TestDetailsParentActivity.class);
                    intent.putExtra("idSelect", idSelect);
                    intent.putExtra("data", text);
                    context.startActivity(intent);
                }
            });

        }
    }
}
