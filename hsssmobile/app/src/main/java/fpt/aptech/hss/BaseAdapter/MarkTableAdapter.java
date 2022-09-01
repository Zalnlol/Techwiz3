package fpt.aptech.hss.BaseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;

public class MarkTableAdapter extends RecyclerView.Adapter<MarkTableAdapter.MarkTableHolder> {
    List<ModelString> marklist;
    Context context;
    public MarkTableAdapter(List<ModelString> marklist,Context context) {
        this.marklist = marklist;
        this.context = context;
    }
    @NonNull
    @Override
    public MarkTableAdapter.MarkTableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.mark_table_item,parent,false);
        return new MarkTableAdapter.MarkTableHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarkTableHolder holder, int position) {
        ModelString mark = marklist.get(position);
        holder.tvname.setText(mark.getData1());
        holder.tvdate.setText(mark.getData2());
        holder.tvMark.setText(mark.getData3());
        holder.tvRemark.setText(mark.getData4());
        holder.tvMarkName2.setText(mark.getData7());
//        Toast.makeText(context, "mark:"+mark.getData7(), Toast.LENGTH_SHORT).show();

    }
    @Override
    public int getItemCount() {
        return marklist.size();
    }
    public class MarkTableHolder extends RecyclerView.ViewHolder {
        TextView tvname,tvdate,tvMark,tvRemark,tvMarkName2;
        LinearLayout linearLayout;
        public MarkTableHolder(@NonNull View itemView) {
            super(itemView);
            tvname=itemView.findViewById(R.id.tvMarkName);
            tvdate=itemView.findViewById(R.id.tvdMarkDate);
            tvMark=itemView.findViewById(R.id.tvMarkScore);
            tvRemark=itemView.findViewById(R.id.tvdMarkRemark);
            linearLayout=itemView.findViewById(R.id.linearnotification);
            tvMarkName2=itemView.findViewById(R.id.tvName1);
        }
    }
}
