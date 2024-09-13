package com.example.javahelloworld;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    int Col = 0;
    String SName = "0";
    String SGroup = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView helloMain = findViewById(R.id.HelloMain);
        Button PlusB = findViewById(R.id.PlusMain);
        TextView VName = findViewById(R.id.NameV);
        TextView VGroup = findViewById(R.id.GroupV);
        EditText editName = findViewById(R.id.editTextName);
        EditText editGroup = findViewById(R.id.editTextGroup);

        SharedPreferences SaveData = getSharedPreferences("Table", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = SaveData.edit();

        Col = SaveData.getInt("Col",0);
        helloMain.setText("Hello World" + " " + String.valueOf(Col));

        SName = SaveData.getString("SName", "0");
        SGroup = SaveData.getString("SGroup" , "0");
        VName.setText(SName);
        VGroup.setText(SGroup);


        PlusB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Col++;
                helloMain.setText("Hello World" + " " + String.valueOf(Col));
                SName = String.valueOf(editName.getText());
                VName.setText(SName);
                SGroup = String.valueOf(editGroup.getText());
                VGroup.setText(SGroup);
                FSaveData(Col,SName,SGroup);
            }

            public void FSaveData(int res, String NameRes, String GroupRes)
            {
                editor.putInt("Col",res);
                editor.putString("SName", NameRes);
                editor.putString("SGroup",GroupRes);
                editor.commit();
            };
        });

    }
}