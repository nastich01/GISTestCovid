package com.example.testcovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListHospActivity extends AppCompatActivity {

    private ListView hospList;
    ArrayAdapter<Hospital> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hosp);

        hospList = findViewById(R.id.list);

        hospList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Hospital store =arrayAdapter.getItem(position);
                if(store!=null) {
                    Intent intent = new Intent(getApplicationContext(), EditHospActivity.class);
                    intent.putExtra("id", store.getId());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        DataBaseAdapter adapter = new DataBaseAdapter(this);
        adapter.open();

        List<Hospital> stores = adapter.getStores();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stores);
        hospList.setAdapter(arrayAdapter);
        adapter.close();
    }
    // по нажатию на кнопку запускаем UserActivity для добавления данных
    public void add(View view){
        Intent intent = new Intent(this, EditHospActivity.class);
        startActivity(intent);
    }
}