package fpt.aptech.hss.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import fpt.aptech.hss.R;

public class RegisterParentActivity extends AppCompatActivity {
    Button btn_Register;
    EditText Register_DOBB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_parent);
        btn_Register = findViewById(R.id.btn_Register);
        Register_DOBB = findViewById(R.id.Register_Parent_DOBB);
        Register_DOBB();
        btn_Register();

    }
    private void Register_DOBB() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        Register_DOBB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterParentActivity.this, new DatePickerDialog.OnDateSetListener() {
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

    private void btn_Register() {
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText Regist_Name,Regist_Phone,Regist_Mail,Regist_Password,Register_DOBB;
                Regist_Name= findViewById(R.id.Regist_Parent_Name);
                Regist_Phone= findViewById(R.id.Regist_Parent_Phone);
                Regist_Mail= findViewById(R.id.Regist_Parent_Mail);
                Regist_Password= findViewById(R.id.Regist_Parent_Password);
                Register_DOBB= findViewById(R.id.Register_Parent_DOBB);

                Boolean check=true;

                if (Regist_Name.getText().toString().trim().equals("")){
                    check=false;
                    Toast.makeText(RegisterParentActivity.this, "Name canot blank", Toast.LENGTH_SHORT).show();
                }

                if (Regist_Mail.getText().toString().trim().equals("")){
                    check=false;
                    Toast.makeText(RegisterParentActivity.this, "Mail canot blank", Toast.LENGTH_SHORT).show();
                }

                if (Regist_Password.getText().toString().trim().equals("")){
                    check=false;
                    Toast.makeText(RegisterParentActivity.this, "Password canot blank", Toast.LENGTH_SHORT).show();
                }
                if (Register_DOBB.getText().toString().trim().equals("")){
                    check=false;
                    Toast.makeText(RegisterParentActivity.this, "Date of birth canot blank", Toast.LENGTH_SHORT).show();
                }

                String[] modelString =new String[5];
                modelString[0] = Regist_Name.getText().toString();
                modelString[1]=Regist_Mail.getText().toString();
                modelString[2]=Regist_Phone.getText().toString();
                modelString[3]=Regist_Password.getText().toString();
                modelString[4]=Register_DOBB.getText().toString();

                if( check==true){
                    Intent intent = new Intent(RegisterParentActivity.this, QRParentActivity.class);
                    intent.putExtra("data", modelString);
                    startActivity(intent);
                }



            }
        });
    }
}