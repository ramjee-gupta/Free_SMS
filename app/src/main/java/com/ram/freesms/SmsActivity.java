package com.ram.freesms;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class SmsActivity extends AppCompatActivity {

    private EditText mTo, mBody;
    private Button mSendBtn;
    private OkHttpClient mClient = new OkHttpClient();
    private Context mContext;
    private DatabaseAdapter databaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        String name = getIntent().getStringExtra("number");
        mTo = findViewById(R.id.number);
        mBody = findViewById(R.id.msg);
        mSendBtn = findViewById(R.id.sendSms);
        mContext = getApplicationContext();

        mTo.setText(name);

        databaseAdapter = new DatabaseAdapter(this);


        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    post(mContext.getString(R.string.back_end_url), new  Callback(){

                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //mTo.setText("");
                                    long id = databaseAdapter.insertData(mTo.getText().toString(),mBody.getText().toString());
                                    if(id > 0){
                                        mBody.setText("");
                                        Toast.makeText(getApplicationContext(),"SMS Sent!",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(getApplicationContext(),"Something went wrong!",Toast.LENGTH_SHORT).show();
                                    }



                                }
                            });
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    Call post(String url, Callback callback) throws IOException{

        RequestBody formBody = new FormBody.Builder()
                .add("To",mTo.getText().toString())
                .add("Body",mBody.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call response = mClient.newCall(request);
        response.enqueue(callback);
        return  response;
    }


}
