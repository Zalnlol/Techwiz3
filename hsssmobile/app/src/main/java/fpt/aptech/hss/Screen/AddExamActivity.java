package fpt.aptech.hss.Screen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.BaseAdapter.ExamListBase;
import fpt.aptech.hss.Controller.CallNavTeacher;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddExamActivity extends AppCompatActivity {
    String idSelect="";
    String data_="";
    EditText Register_DOBB;
    ListView listView;
    Spinner Choose_Classroom;
    List<ModelString> listdata;
    Button buttonCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_teacher_ubject_detail);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.homecolor)));

        BottomNavigationView bottom_navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        CallNavTeacher callNav = new CallNavTeacher();
        callNav.call(bottom_navigation, R.id.page_2, AddExamActivity.this);

        ScrollView scrollView = findViewById(R.id.scrollView);
        callNav.setDisplay(scrollView, AddExamActivity.this, 0.8);

        androidx.appcompat.widget.AppCompatTextView n = findViewById(R.id.tvTitile);
        Intent intent = getIntent();
        data_  = intent.getStringExtra("data");
        idSelect  = intent.getStringExtra("idSelect");

        n.setText("               New Exam ");
        buttonBack();

        Choose_Classroom = findViewById(R.id.Choose_Classroom);
        Register_DOBB = findViewById(R.id.Register_DOBB);
        buttonCreate = findViewById(R.id.buttonCreate);

        AddClassroom();
        buttonCreate();
        Register_DOBB();
    }

    private void buttonCreate() {
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = findViewById(R.id.ExamName);

                boolean check = true;

                if(name.getText().toString().equals("")){
                    Toast.makeText(AddExamActivity.this, "Please enter exam name", Toast.LENGTH_SHORT).show();
                     check = false;
                }
                if(Choose_Classroom.getSelectedItem().toString().equals("Add Classroom")){
                    Toast.makeText(AddExamActivity.this, "Please add classroom", Toast.LENGTH_SHORT).show();
                    check = false;
                }

                if(Register_DOBB.getText().toString().equals("")){
                    Toast.makeText(AddExamActivity.this, "Please enter start day", Toast.LENGTH_SHORT).show();
                    check = false;
                }

                int id_class=0;

                for (ModelString item : listdata   ) {
                    if(item.getData2().equals(Choose_Classroom.getSelectedItem().toString())){
                        id_class = Integer.parseInt(item.getData1());
                    }
                }




                if(check==true){

                    DataAPI.api.CreateExem(idSelect,name.getText().toString(),String.valueOf(id_class),Register_DOBB.getText().toString()).enqueue(new Callback<ModelString>() {

                        @Override
                        public void onResponse(Call<ModelString> call, Response<ModelString> response) {



                        }

                        @Override
                        public void onFailure(Call<ModelString> call, Throwable t) {
                            Toast.makeText(AddExamActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Intent intent = new Intent(AddExamActivity.this, SubjectDetailActivity.class);
                    intent.putExtra("data", data_);
                    intent.putExtra("idSelect", idSelect);
                    startActivity(intent);


                }


            }
        });
    }

    private void AddClassroom() {

        List<String> list = new ArrayList<>();
        list.add("Add Classroom");

        Toast.makeText(this, ""+idSelect, Toast.LENGTH_SHORT).show();

        DataAPI.api.Getclassroom(idSelect).enqueue(new Callback<List<ModelString>>() {

            @Override
            public void onResponse(Call<List<ModelString>> call, Response<List<ModelString>> response) {
                listdata = response.body();


                for (ModelString item: listdata) {
                    list.add(item.getData2());
                }






            }

            @Override
            public void onFailure(Call<List<ModelString>> call, Throwable t) {
                Toast.makeText(AddExamActivity.this, "Connect error, unable to find classes!", Toast.LENGTH_SHORT).show();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        Choose_Classroom.setAdapter(adapter);



    }


    private void Register_DOBB() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Register_DOBB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddExamActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        if(month<10){
                            //String date = year+"-0"+month+"-"+day;
                            if(day<10){
                                String date = year+"-0"+month+"-0"+day;
                                Register_DOBB.setText(date);
                            }else{
                                String date = year+"-0"+month+"-"+day;
                                Register_DOBB.setText(date);
                            }

                        }else{
                            if(day<10){
                                String date = year+"-"+month+"-0"+day;
                                Register_DOBB.setText(date);
                            }else{
                                String date = year+"-"+month+"-"+day;
                                Register_DOBB.setText(date);
                            }
                        }

                    }

                },year,month,day);
                datePickerDialog.show();
            }

        });

    }

    private void buttonBack() {
        ImageButton button = findViewById(R.id.btn_Work_schedule_back);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddExamActivity.this, SubjectDetailActivity.class);
                intent.putExtra("data", data_);
                intent.putExtra("idSelect", idSelect);
                startActivity(intent);
            }
        });
    }
}