package com.cookandroid.project8_1;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

@SuppressWarnings("deprecation")

public class MainActivity extends AppCompatActivity {
    DatePicker dp;
    Button button1;
    EditText edtDiary;
    String FileName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장");

        dp = (DatePicker) findViewById(R.id.datePicker1);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        button1 = (Button) findViewById(R.id.button1);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker,
                                      int i, int i1, int i2) {

                FileName = Integer.toString(i) + "_" +
                        Integer.toString(i1 + 1) + "_" +
                        Integer.toString(i2) + ".txt";

                String str = readDiary(FileName);
                edtDiary.setText(str);
                button1.setEnabled(true);
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    FileOutputStream outFs = openFileOutput(FileName,
                            Context.MODE_PRIVATE);
                    String str = edtDiary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();

                    Toast.makeText(getApplicationContext(), FileName +
                            "이 저장됨", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                }
            }
        });
    }

    String readDiary(String fName) {
        String diaryStr=null;
        FileInputStream inFs;
        try {
            inFs=openFileInput(fName);
            byte[] txt= new byte[500];
            inFs.read(txt);
            inFs.close();

            diaryStr=(new String(txt)).trim();
            button1.setText("수정하기");
        }
        catch(IOException e){

            edtDiary.setHint("일기 없음");
            button1.setText("새로 저장");
        }

        return diaryStr;
    }
}