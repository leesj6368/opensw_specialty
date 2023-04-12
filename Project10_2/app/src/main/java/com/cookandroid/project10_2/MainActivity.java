package com.cookandroid.project10_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView image[] = new ImageView[9];
    private Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("명화 선호도 투표");

        Integer imageId[] = {R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6,
                R.id.iv7, R.id.iv8, R.id.iv9};
        String imageName[] = {"포룸 광장의 카페 테라스", "아를의 별이 빛나는 밤", "모나리자", "해바라기",
                "키스", "절규", "해돋이","별이 빛나는 밤", "반 고흐의 침실"};
        final int voteCount[] = new int[9];
        for(int i = 0; i < 9; i++){
            voteCount[i] = 0;
        }

        for(int i = 0; i < imageId.length; i++){
            final int index;
            index = i;
            image[index] = (ImageView) findViewById(imageId[index]);
            image[index].setOnClickListener(v -> {
                voteCount[index]++;
                Toast.makeText(getApplicationContext(), imageName[index] + " : 총 "
                        + voteCount[index] + " 표", Toast.LENGTH_SHORT).show();
            });
        }

        btnResult.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("VoteCount", voteCount);
            intent.putExtra("ImageName", imageName);
            startActivity(intent);
        });
    }
}