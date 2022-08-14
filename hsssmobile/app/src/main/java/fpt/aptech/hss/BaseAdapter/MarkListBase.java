package fpt.aptech.hss.BaseAdapter;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.Config.ConfigData;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import fpt.aptech.hss.Screen.ExamDetailActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkListBase extends BaseAdapter {
   public Context context ;
    public  List<ModelString> list;


    public MarkListBase() {
    }

    public MarkListBase(Context context, List<ModelString> list ) {
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

            view = inflater.inflate(R.layout.marklayout,null);

        }

        ModelString modelString = list.get(i);

        TextView name = view.findViewById(R.id.name);
        TextView Mark = view.findViewById(R.id.Mark);
        TextView Reviews = view.findViewById(R.id.Reviews);

        Mark.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "10")});

        name.setText(modelString.getData2());
        Mark.setText(modelString.getData3());
        Reviews.setText(modelString.getData4());

        Mark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.get(i).setData3(Mark.getText().toString());

            }
        });

        Reviews.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.get(i).setData4(Reviews.getText().toString());

            }
        });


        return view;


    }

    public void Addata(String idExam_,String idSelect, String data_ ){

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setData5(idExam_);
            list.get(i).setData6(idSelect);
        }
        DataAPI.api.MarkCreatePost(list).enqueue(new Callback<List<ModelString>>() {
            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {

            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {

            }
        });

        Intent intent = new Intent(context, ExamDetailActivity.class);
        intent.putExtra("data", data_);
        intent.putExtra("idExam", idExam_);
        intent.putExtra("idSelect", idSelect);
        context.startActivity(intent);

    }
}
