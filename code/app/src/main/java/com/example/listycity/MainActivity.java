package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter; //bridges between datasource (actual array list) and UI component
    ArrayList<String> dataList; //Declare dataList
    EditText inputText;
    Button ConfirmButton;
    String removing_city_name;
    @Override

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        cityList = findViewById(R.id.city_list); //find the city list id located in activity main
        String []cities = {"Edmonton"};
        dataList = new ArrayList<>(); //Initialize dataList
        dataList.addAll(Arrays.asList(cities)); //fill in dataList given array of cities
        inputText = findViewById(R.id.cityInput);
        ConfirmButton = findViewById(R.id.confirm_button);
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                removing_city_name = (String) cityList.getItemAtPosition(position);

            }
        });
    }

    public void RemoveCity(View view){
        dataList.remove(removing_city_name);
        cityAdapter.notifyDataSetChanged();
    }



    public void AddCity(View view) {
        ConfirmButton.setVisibility(View.VISIBLE);
        inputText.setVisibility(View.VISIBLE);
    }
    public void confirm(View view){
        dataList.add(String.valueOf(inputText.getText()));
        cityAdapter.notifyDataSetChanged();
        inputText.setVisibility(View.GONE);
        ConfirmButton.setVisibility(View.GONE);

    }
}