package fpt.aptech.hss.BaseAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import fpt.aptech.hss.Screen.DetailContactActivity;


public class ContactAdapter extends BaseAdapter {

    Context context ;
    List<ModelString> list;

    public ContactAdapter(Context context, List<ModelString> list, Activity activity){
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

            view = inflater.inflate(R.layout.contectlist,null);

        }

        ModelString modelString = list.get(i);

        Button Mytimeline_Item_Timeline =view.findViewById(R.id.btnTimekeepingDetail);

        Mytimeline_Item_Timeline.setText(modelString.getData2());



        Mytimeline_Item_Timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailContactActivity.class);
                String text = Mytimeline_Item_Timeline.getText().toString();
                String idSelect=list.get(i).getData1();


                intent.putExtra("data", text);
                intent.putExtra("idSelect", idSelect);

                context.startActivity(intent);
            }
        });


        return view;


    }



}
