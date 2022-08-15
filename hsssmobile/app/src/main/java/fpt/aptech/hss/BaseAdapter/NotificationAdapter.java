package fpt.aptech.hss.BaseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotifacationHolder>{
    List<ModelString> notifactionlist;
    Context context;
    public NotificationAdapter(List<ModelString> notifactionlist,Context context) {
        this.notifactionlist = notifactionlist;
        this.context = context;
    }
    @NonNull
    @Override
    public NotifacationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.notification_item,parent,false);
        return new NotifacationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifacationHolder holder, int position) {
        ModelString notification = notifactionlist.get(position);
        holder.tvtitle.setText(notification.getData1());
        holder.tvcontent.setText(notification.getData2());
        if(Integer.valueOf(notification.getData4())==0){
            holder.tvdatesend.setText("Today");
        }else{
            holder.tvdatesend.setText(notification.getData4() + " Days before");
        }
        boolean check = Boolean.valueOf(notifactionlist.get(position).getData7());
        boolean isExpandable = check;
        holder.relativeLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return notifactionlist.size();
    }
    public class NotifacationHolder extends RecyclerView.ViewHolder {
        TextView tvtitle,tvdatesend,tvcontent;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;
        public NotifacationHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle=itemView.findViewById(R.id.tvTitle);
            tvdatesend=itemView.findViewById(R.id.tvdDateSend);
            tvcontent=itemView.findViewById(R.id.tvContent);
            linearLayout=itemView.findViewById(R.id.linearnotification);
            relativeLayout=itemView.findViewById(R.id.relatenotification);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ModelString accountNotification =notifactionlist.get(getAdapterPosition());
                    Boolean check = Boolean.valueOf(accountNotification.getData7());
                    Boolean fn = !check;
                    accountNotification.setData7(fn.toString());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
