package fpt.aptech.hss.BaseAdapter;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import fpt.aptech.hss.Screen.ExamDetailActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkList1Base extends BaseAdapter {
   public Context context ;
    public  List<ModelString> list;



    public MarkList1Base(Context context, List<ModelString> list ) {
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

            view = inflater.inflate(R.layout.listmarklayout,null);

        }

        ModelString modelString = list.get(i);

        TextView name = view.findViewById(R.id.Name);
        TextView Mark = view.findViewById(R.id.Mark);
        TextView Reviews = view.findViewById(R.id.Reviews);

        name.setText(modelString.getData2());
        Mark.setText(modelString.getData3());
        Reviews.setText(modelString.getData4());

        return view;


    }


}
