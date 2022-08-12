package fpt.aptech.hss.Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import fpt.aptech.hss.API.DataAPI;
import fpt.aptech.hss.Model.ModelString;
import fpt.aptech.hss.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class    QRActivity extends AppCompatActivity {
    String[] data ;
    private CodeScanner mCodeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        getSupportActionBar().hide();

         data =new String[5];
        Intent intent = getIntent();
        data  = intent.getStringArrayExtra("data");




        GetDataFromQR();
    }

    private void GetDataFromQR(){
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
//        mCodeScanner.setCamera(1);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(QRActivity.this, ""+result.getText().toString(), Toast.LENGTH_SHORT).show();

                        DataAPI.api.APICreateAccount(data[1],data[2],result.getText().toString(),data[4],data[0],data[3]).enqueue(new Callback<ModelString>() {
                            @Override
                            public void onResponse(Call<ModelString> call, Response<ModelString> response) {
                                Toast.makeText(QRActivity.this, ""+response.body().getData1(), Toast.LENGTH_SHORT).show();

                                if(response.body().getData1().equals("Done")){

                                    startActivity(new Intent(QRActivity.this, SuccessActivity.class));
                                }


                            }

                            @Override
                            public void onFailure(Call<ModelString> call, Throwable t) {
                                Toast.makeText(QRActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}