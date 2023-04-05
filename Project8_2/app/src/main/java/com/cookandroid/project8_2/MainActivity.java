package com.cookandroid.project8_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class myPictureView extends View {

    // myPictureView 에 보여줄 이미지 파일의 경로 및 파일 이름을 저장할 변수
    String imagePath = null;

    public myPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // imagePath 에 값이 있으면 화면에 그림 파일을 출력함
        if(imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            canvas.drawBitmap(bitmap, 0, 0, null);
            bitmap.recycle();
        }
    }
}
public class MainActivity extends AppCompatActivity {

    private Button btnPrev, btnNext;
    myPictureView myPictureView;
    int num = 1;
    File[] imageFiles;
    String imageFname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지 뷰어");

        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        myPictureView = findViewById(R.id.myPictureView);

        imageFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/Pictures").listFiles();
        imageFname = imageFiles[1].toString();
        myPictureView.imagePath = imageFname;

        btnPrev.setOnClickListener(v -> {
            if(num <= 1){
                Toast.makeText(getApplicationContext(), "첫번째 그림입니다.",
                        Toast.LENGTH_SHORT).show();
            } else {
                num--;
                imageFname = imageFiles[num].toString();
                myPictureView.imagePath = imageFname;
                myPictureView.invalidate();
            }
        });

        btnNext.setOnClickListener(v -> {
            if(num >= imageFiles.length - 1){
                Toast.makeText(getApplicationContext(), "마지막 그림입니다.",
                        Toast.LENGTH_SHORT).show();
            } else {
                num++;
                imageFname = imageFiles[num].toString();
                myPictureView.imagePath = imageFname;
                myPictureView.invalidate();
            }
        });
    }
}