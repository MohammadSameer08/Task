package com.example.taskcheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MainActivity extends AppCompatActivity implements bottomSheet.BottomSheetListener {

    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       sharedPreferences=this.getSharedPreferences("com.example.taskcheck",Context.MODE_PRIVATE);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         toolbar.setNavigationOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view)
             {
                      startActivity(new Intent(getApplicationContext(),tabs.class));
             }
         });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {

        if(item.getItemId()==R.id.setting)
        {
          bottomSheet bs=new bottomSheet();
          bs.show(getSupportFragmentManager(),"bottomSheet");
        }

    return  super.onOptionsItemSelected(item);
    }


    @Override
    public void onButtonClicked(int size)
    {
        sharedPreferences.edit().putInt("size",size).apply();
    }


}
