package com.example.room_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.room_app.Base.PhongTro;
import com.example.room_app.Base.PhongTroAdapter;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static DatabaseQLPT db;
    ListView lvphongtro;
    ArrayList<PhongTro> arrPhongTro = new ArrayList<>();
    PhongTroAdapter customAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseQLPT(this);
        lvphongtro = findViewById(R.id.lvphongtro);
        customAdaper = new PhongTroAdapter(this,R.layout.dong_phong_tro,arrPhongTro);
        lvphongtro.setAdapter(customAdaper);
        click();
        registerForContextMenu(lvphongtro);
    }

    //MENU_MAIN
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent intent;
        final int result=1;
        switch (item.getItemId()) {
            case R.id.DVid:
                intent = new Intent(this,DichVuActivity.class);
                startActivityForResult(intent,result);
                break;
            case R.id.HDid:
                intent = new Intent(this,HoaDonActivity.class);
                startActivityForResult(intent,result);
                break;
            case R.id.Themid:
                //ThemPhongTro();
                break;
        }
        return true;
    }

    //CONTEXT_MENU_PHONG
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_phong, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int pos = info.position;
        final PhongTro p = arrPhongTro.get(pos);
        switch (item.getItemId())
        {
            case R.id.menuChitiet:
                //XemChiTiet(p);
                return true;

            case R.id.menuupdel:
                //Updatept(p);
                return true;

            case R.id.menutraphong:
                Cursor a= MainActivity.db.getAllKhachP(p.getID());
                if(a.getCount()!=0){
                    new AlertDialog.Builder(MainActivity.this)
                            .setMessage("Trả phòng "+p.getTEN_PHONG()+"?")
                            .setCancelable(false)
                            .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Integer d= db.xoaAllKhachP(p.getID());
                                    if(d>0)
                                        Toast.makeText(MainActivity.this,"Đã trả phòng",Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(MainActivity.this,"Lỗi, vui lòng thao tác lại",Toast.LENGTH_LONG).show();
                                    customAdaper.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("Không", null)
                            .show();
                }
                else
                    Toast.makeText(this,"Chưa có khách thuê phòng",Toast.LENGTH_LONG).show();
                return true;
            case R.id.menulaphd:
                Cursor b= MainActivity.db.getAllKhachP(p.getID());
                if(b.getCount() == 0){
                    Toast.makeText(this,"Thao tác thất bại, phòng không có khách thuê",Toast.LENGTH_LONG).show();
                }
                else{
                    //ThemHoaDon(p);
                }
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


    //SỰ KIỆN CLICK ITEMS LISTVIEW
    public void click(){
        lvphongtro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, PhongTroActivity.class);
                intent.putExtra("EDIT", (Serializable) customAdaper.getItem(i));
                startActivityForResult(intent,1);
            }
        });

    }
}