package fpt.aptech.hss.BaseAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import fpt.aptech.hss.Screen.MyclasroomDetail;
import fpt.aptech.hss.Screen.ScheduleDetailActivity;

public class ScheduleListBase extends BaseAdapter {
    Context context ;
    List<ModelString> list;

    public ScheduleListBase(Context context, List<ModelString> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        Mytimeline_Item_Timeline

        if(view ==null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.classroom_list,null);

        }

        ModelString modelString = list.get(i);

        TextView Title = view.findViewById(R.id.classroom_text_item);
        ImageView imageView = view.findViewById(R.id.classroom_image_item);

        Title.setText(modelString.getData2());

        Glide.with(context)
                .load("http://" + ConfigData.IP + ":8080/KSS/"+modelString.getData1())
//                .transform(new RoundedCorners(radius))
//                .transform(new CircleCrop())
                .override(600, 600)
//                .error(R.drawable.icon5)
                .into(imageView);



        LinearLayout Classroomitem = view.findViewById(R.id.Classroomitem);
        Classroomitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ScheduleDetailActivity.class);
                String text = list.get(i).getData2().toString();
                String idSelect=modelString.getData3();

                intent.putExtra("data", text);
                intent.putExtra("idSelect", idSelect);
                context.startActivity(intent);

            }
        });


        return view;


    }
}
