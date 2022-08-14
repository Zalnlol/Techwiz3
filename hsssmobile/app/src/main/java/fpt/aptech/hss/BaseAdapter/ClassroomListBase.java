package fpt.aptech.hss.BaseAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import fpt.aptech.hss.R;
import java.util.List;

import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.Screen.MyclasroomDetail;

public class ClassroomListBase extends BaseAdapter {
    Context context ;
    List<ModelString> list;

    public ClassroomListBase(Context context, List<ModelString> list) {
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
        imageView.setImageResource(Integer.parseInt(modelString.getData1()));

        LinearLayout Classroomitem = view.findViewById(R.id.Classroomitem);
        Classroomitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MyclasroomDetail.class);
                String text = list.get(i).getData2().toString();
                String idSelect = list.get(i).getData3();

                intent.putExtra("data", text);
                intent.putExtra("idSelect", idSelect);
                context.startActivity(intent);

            }
        });


        return view;


    }
}
