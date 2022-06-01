package com.example.room_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.room_app.Base.DichVu;
import com.example.room_app.Base.DichVuAdapter;

import java.util.ArrayList;

public class DichVuActivity extends AppCompatActivity {
    ListView lvdichvu;
    ArrayList<DichVu> arrDichVu = new ArrayList<>();
    DichVuAdapter customAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dich_vu);
        lvdichvu = findViewById(R.id.lvdichvu);
        customAdaper = new DichVuAdapter(this,R.layout.dong_dich_vu, arrDichVu);
        lvdichvu.setAdapter(customAdaper);
        registerForContextMenu(lvdichvu);
        actionBar();
    }

    //ACTION BAR
    public void actionBar(){
        //Cài đặt tiêu đề, icon cho actionbar
        getSupportActionBar().setTitle("DỊCH VỤ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}