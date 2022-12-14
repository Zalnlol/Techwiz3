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
import fpt.aptech.hss.Screen.ExamDetailActivity;
import fpt.aptech.hss.Screen.SubjectDetailActivity;

public class ExamListBase extends BaseAdapter {
    Context context ;
    List<ModelString> list;


    String idSelect="";
    String data_="";

    public ExamListBase(Context context, List<ModelString> list , String data_, String idSelect) {
        this.context = context;
        this.list = list;
        this.data_ = data_;
        this.idSelect = idSelect;
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
                .load("http://" + ConfigData.IP + ":7777/"+modelString.getData1())
                .override(600, 600)
                .into(imageView);




        LinearLayout Classroomitem = view.findViewById(R.id.Classroomitem);
        Classroomitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ExamDetailActivity.class);
                String text = list.get(i).getData2().toString();
                String idExam=list.get(i).getData4();

                intent.putExtra("data", data_);
                intent.putExtra("idExam", idExam);
                intent.putExtra("idSelect", idSelect);
                context.startActivity(intent);

            }
        });


        return view;


    }
}
