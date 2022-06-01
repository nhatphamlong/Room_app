package com.example.room_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.room_app.Base.KhachThue;
import com.example.room_app.Base.KhachThueAdapter;
import com.example.room_app.Base.PhongTro;

import java.util.ArrayList;
import java.util.Calendar;

public class PhongTroActivity extends AppCompatActivity {
    EditText edTenPhong,edSucchua,edDienTich,edGiaThue,edSoDien,edSoNuoc,edTTKhac;
    TextView tvTenPhongTro,tvSoNguoiO,tvDs;
    PhongTro p;
    ListView lvnguoithue;
    ArrayList<KhachThue> arrKhach = new ArrayList<>();
    KhachThueAdapter customAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_tro);
        if (getIntent().getExtras() != null) {
            p = (PhongTro) getIntent().getSerializableExtra("EDIT");
        }
        Anhxa();
        tvTenPhongTro.setText(p.getTEN_PHONG());
        actionBar();
        lvnguoithue = findViewById(R.id.lvnguoithue);
        customAdaper = new KhachThueAdapter(this,R.layout.dong_khach,arrKhach);
        lvnguoithue.setAdapter(customAdaper);
    }

    public void Anhxa(){
        edTenPhong=(EditText)findViewById(R.id.edTenPhong);
        edSucchua=(EditText)findViewById(R.id.edSucchua);
        edDienTich=(EditText)findViewById(R.id.edDienTich);
        edGiaThue=(EditText)findViewById(R.id.edGiaThue);
        edSoDien=(EditText)findViewById(R.id.edSoDien);
        edSoNuoc=(EditText)findViewById(R.id.edSoNuoc);
        edTTKhac=(EditText)findViewById(R.id.edTTKhac);
        tvTenPhongTro=(TextView) findViewById(R.id.tvTenPhongTro);
        tvSoNguoiO=(TextView) findViewById(R.id.tvSoNguoi0);
        tvDs=(TextView) findViewById(R.id.tvDs);
    }

    //ACTIONBAR
    public void actionBar(){
        //Lấy chiều cao của ActionBar
        TypedArray styledAttributes = getTheme().obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        //Tạo Drawable mới bằng cách thu/phóng
        Drawable drawable= getResources().getDrawable(R.drawable.house);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable newdrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, actionBarSize,  actionBarSize, true));

        //Cài đặt tiêu đề, icon cho actionbar
        getSupportActionBar().setTitle("PHÒNG TRỌ");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//      getSupportActionBar().setLogo(newdrawable);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_phu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.Them:
                //ThemKhachHang();
                return true;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

}