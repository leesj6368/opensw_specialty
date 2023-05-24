package com.cookandroid.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class CenterListActivity extends AppCompatActivity {
    public static ArrayList<center> centerList =new ArrayList<center>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centerlist);

        setUpData();
        setUpList();
        setUpOnClickListener();
        searchCenter();


    }

    private void setUpData(){
        center cheongjue = new center("0", "청주 반려동물보호센터", R.drawable.cheongjue);
        centerList.add(cheongjue);
        center sejong = new center("1", "세종 유기동물보호센터", R.drawable.sejong);
        centerList.add(sejong);
        center busan1 = new center("2", "부산 하얀비둘기", R.drawable.busan1);
        centerList.add(busan1);
        center busan2 = new center("3", "부산 동물보호관리협회", R.drawable.busan2);
        centerList.add(busan2);
        center daejeon = new center("4", "대전 동물보호관리협회", R.drawable.daejeon);
        centerList.add(daejeon);
        center incheon1 = new center("5", "인천광역시 수의사회", R.drawable.incheon1);
        centerList.add(incheon1);
        center incheon2 = new center("6", "인천 가정동물병원", R.drawable.incheon2);
        centerList.add(incheon2);
        center jeju= new center("7", "제주 동물보호센터", R.drawable.jeju);
        centerList.add(jeju);
        center seoul1= new center("8", "서울 C.T종합동물병원", R.drawable.seoul1);
        centerList.add(seoul1);
        center seoul2= new center("9", "서울 동물복지지원센터", R.drawable.seoul2);
        centerList.add(seoul2);
        center seoul3= new center("10", "서울 한국동물구조관리협회", R.drawable.seoul3);
        centerList.add(seoul3);
        center seoul4= new center("11", "서울 서초동물사랑센터", R.drawable.seoul4);
        centerList.add(seoul4);

    }

    private void setUpList(){

        listView = findViewById(R.id.center_listView);

        CenterAdapter adapter = new CenterAdapter(getApplicationContext(),0, centerList);
        listView.setAdapter(adapter);
    }

    private void setUpOnClickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                center selectCenter = (center) listView.getItemAtPosition(position);
                Intent showDetail = new Intent(getApplicationContext(),DetailActivity.class);
                showDetail.putExtra("id", selectCenter.getId());
                startActivity(showDetail);
            }
        });
    }


    private void searchCenter(){
        SearchView searchView=findViewById(R.id.center_search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<center> filterCenter = new ArrayList<>();
                for (int i = 0; i< centerList.size(); i++){
                    center center = centerList.get(i);

                    if(center.getName().toLowerCase().contains(newText.toLowerCase())){
                        filterCenter.add(center);
                    }
                }
                CenterAdapter adapter = new CenterAdapter(getApplicationContext(),0, filterCenter);
                listView.setAdapter(adapter);
                return false;
            }
        });
    }




}