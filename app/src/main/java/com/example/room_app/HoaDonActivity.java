package com.example.room_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.room_app.Base.DichVu;
import com.example.room_app.Base.HoaDonThang;
import com.example.room_app.Base.HoaDonThangAdapter;
import com.example.room_app.Base.PhongTro;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HoaDonActivity extends AppCompatActivity {
    int thang;
    TextView tvDs;
    RadioGroup rdgLoai;
    RadioButton rdDa,rdChua;
    public ListView lvhoadon;
    public int tiendien,tiennuoc;
    Calendar c = Calendar.getInstance();
    ArrayList<HoaDonThang> arrHoaDonDa = new ArrayList<>();
    HoaDonThangAdapter customAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        Anhxa();
        showHoaDon(1);

        customAdaper = new HoaDonThangAdapter(this, R.layout.dong_hoa_don, arrHoaDonDa);
        lvhoadon.setAdapter(customAdaper);

        actionBar();
    }

    public void Anhxa(){
        tvDs=(TextView) findViewById(R.id.tvDs);
        rdDa=(RadioButton)findViewById(R.id.rdDa);
        rdChua=(RadioButton)findViewById(R.id.rdChua);
        rdgLoai=(RadioGroup)findViewById(R.id.rdgLoai);
        lvhoadon = (ListView) findViewById(R.id.lvhoadon);
    }

    //ACTION BAR
    public void actionBar(){
        //Lấy chiều cao của ActionBar
        TypedArray styledAttributes = getTheme().obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        //Tạo Drawable mới bằng cách thu/phóng
        Drawable drawable= getResources().getDrawable(R.drawable.icon_hoa_don);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable newdrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, actionBarSize,  actionBarSize, true));

        //Cài đặt tiêu đề, icon cho actionbar
        getSupportActionBar().setTitle("HOÁ ĐƠN");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
    public void showHoaDon(int loai){
        Cursor a=MainActivity.db.getAllHoaDon();
        if(a.getCount() == 0){
        }
        else{
            arrHoaDonDa.clear();
            while(a.moveToNext()){
                HoaDonThang b=new HoaDonThang();
                b.setID(a.getString(0));
                b.setTHANG(a.getString(1));
                b.setPHONGID(a.getString(2));
                b.setSO_DIEN(a.getString(3));
                b.setSO_NUOC(a.getString(4));
                b.setCHI_PHI_KHAC(a.getString(5));
                b.setTHANH_TIEN(a.getString(6));
                b.setNGAY_LAP(a.getString(7));
                b.setTINH_TRANG(a.getString(8));
                if(loai==0&& a.getString(8).equals("Đã thanh toán"))
                    arrHoaDonDa.add(b);
                else if(loai==1&&a.getString(8).equals("Chưa thanh toán"))
                    arrHoaDonDa.add(b);
            }
        }
    }


}