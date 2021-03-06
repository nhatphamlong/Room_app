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
        showKhach();
        MenuKhach();
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
        //L???y chi???u cao c???a ActionBar
        TypedArray styledAttributes = getTheme().obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        //T???o Drawable m???i b???ng c??ch thu/ph??ng
        Drawable drawable= getResources().getDrawable(R.drawable.house);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable newdrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, actionBarSize,  actionBarSize, true));

        //C??i ?????t ti??u ?????, icon cho actionbar
        getSupportActionBar().setTitle("PH??NG TR???");
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
                ThemKhachHang();
                return true;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void showKhach(){
        Cursor a=MainActivity.db.getAllKhachP(p.getID());
        if(a.getCount() == 0){
            tvSoNguoiO.setText("Ph??ng tr???ng");
            tvDs.setText("Ch??a c?? kh??ch thu?? ph??ng");
        }
        else{
            arrKhach.clear();
            tvSoNguoiO.setText(a.getCount()+" ng?????i ??? tr???");
            while(a.moveToNext()){
                KhachThue b=new KhachThue();
                b.setID(a.getString(0));
                b.setTEN_KH(a.getString(1));
                b.setGIOI_TINH(a.getString(2));
                b.setNAM_SINH(a.getString(3));
                b.setCMND(a.getString(4));
                b.setNGAY_CAP(a.getString(5));
                b.setSDT(a.getString(6));
                b.setPHONG_O(a.getString(7));
                b.setHINH_ANH(a.getString(8));
                arrKhach.add(b);
            }
        }
    }
    public void ThemKhachHang(){
        final Dialog dialog=new Dialog(PhongTroActivity.this);
        dialog.setTitle("Th??m m???i d???ch v???");
        dialog.setContentView(R.layout.them_khach);
        Button btThemKhach=(Button)dialog.findViewById(R.id.btThemKhach);
        Button huy=(Button)dialog.findViewById(R.id.btHuy);
        RadioButton rdNam=(RadioButton)dialog.findViewById(R.id.rdNam);
        RadioButton rdNu=(RadioButton)dialog.findViewById(R.id.rdNu);
        final EditText edTenKhach=(EditText)dialog.findViewById(R.id.edTenKhach);
        final EditText edNamSinh=(EditText)dialog.findViewById(R.id.edNamSinh);
        final EditText edCMND=(EditText)dialog.findViewById(R.id.edCMND);
        final EditText edNgay=(EditText)dialog.findViewById(R.id.edNgay);
        final EditText edThang=(EditText)dialog.findViewById(R.id.edThang);
        final EditText edNam=(EditText)dialog.findViewById(R.id.edNam);
        final EditText edSDT=(EditText)dialog.findViewById(R.id.edSDT);
        final RadioGroup rdgGioiTinh=(RadioGroup)dialog.findViewById(R.id.rdgGioitinh) ;
        btThemKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int a=c.get(Calendar.YEAR);
                if(edTenKhach.getText().toString().equals("")){
                    edTenKhach.setError("T??n kh??ch kh??ng th??? tr???ng");
                    return;
                }
                else if(edNamSinh.getText().toString().equals("")){
                    edNamSinh.setError("N??m sinh kh??ng th??? tr???ng");
                    return;
                }
                else if(edSDT.getText().toString().equals("")){
                    edSoNuoc.setError("S??? ??i???n tho???i kh??ng th??? tr???ng");
                    return;
                }
                String gt="";
                int checkedRadioId = rdgGioiTinh.getCheckedRadioButtonId();
                if(checkedRadioId== R.id.rdNam) {
                    gt="Nam";
                } else if(checkedRadioId== R.id.rdNu ) {
                    gt="N???";
                }
                String NgayCap=edNgay.getText().toString()+"/"+edThang.getText().toString()+"/"+edNam.getText().toString();
                boolean them=MainActivity.db.themKhach(edTenKhach.getText().toString(),gt,edNamSinh.getText().toString(),
                        edCMND.getText().toString(),NgayCap,edSDT.getText().toString(),p.getID(),"");
                if(them==true){
                    Toast.makeText(PhongTroActivity.this,"Th??m kh??ch th??nh c??ng",Toast.LENGTH_LONG).show();
                    tvDs.setText("Danh s??ch kh??ch thu??");
                }
                else
                    Toast.makeText(PhongTroActivity.this,"Kh??ng th??m ???????c",Toast.LENGTH_LONG).show();
                showKhach();
                customAdaper.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void MenuKhach(){
        lvnguoithue.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v,
                                           int pos, long id) {
                final KhachThue pt = (KhachThue) arrKhach.get(pos);
                final Dialog dialog=new Dialog(PhongTroActivity.this);
                dialog.setTitle("Chi ti???t");
                dialog.setContentView(R.layout.them_khach);
                Button btThemKhach=(Button)dialog.findViewById(R.id.btThemKhach);
                Button huy=(Button)dialog.findViewById(R.id.btHuy);
                RadioButton rdNam=(RadioButton)dialog.findViewById(R.id.rdNam);
                RadioButton rdNu=(RadioButton)dialog.findViewById(R.id.rdNu);
                final EditText edTenKhach=(EditText)dialog.findViewById(R.id.edTenKhach);
                final EditText edNamSinh=(EditText)dialog.findViewById(R.id.edNamSinh);
                final EditText edCMND=(EditText)dialog.findViewById(R.id.edCMND);
                final EditText edNgay=(EditText)dialog.findViewById(R.id.edNgay);
                final EditText edThang=(EditText)dialog.findViewById(R.id.edThang);
                final EditText edNam=(EditText)dialog.findViewById(R.id.edNam);
                final EditText edSDT=(EditText)dialog.findViewById(R.id.edSDT);
                TextView tvtitle=(TextView)dialog.findViewById(R.id.tvtitle);
                final RadioGroup rdgGioiTinh=(RadioGroup)dialog.findViewById(R.id.rdgGioitinh) ;
                tvtitle.setText("Chi ti???t kh??ch thu??");
                edTenKhach.setText(pt.getTEN_KH());
                edNamSinh.setText(pt.getNAM_SINH());
                edCMND.setText(pt.getCMND());
                if(pt.getNGAY_CAP().equals("")){
                }
                else {
                    String[] Ngaycap = pt.getNGAY_CAP().split("/");
                    edNgay.setText(Ngaycap[0]);
                    edThang.setText(Ngaycap[1]);
                    edNam.setText(Ngaycap[2]);
                }
                edSDT.setText(pt.getSDT());
                btThemKhach.setText("S???a");
                huy.setText("Xo??");
                if(pt.getGIOI_TINH().equals("Nam"))
                    rdNam.setChecked(true);
                else
                    rdNu.setChecked(true);
                btThemKhach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String gt="";
                        int checkedRadioId = rdgGioiTinh.getCheckedRadioButtonId();
                        if(checkedRadioId== R.id.rdNam) {
                            gt="Nam";
                        } else if(checkedRadioId== R.id.rdNu ) {
                            gt="N???";
                        }
                        Calendar c = Calendar.getInstance();
                        int a=c.get(Calendar.YEAR);
                        if(edTenKhach.getText().toString().equals("")){
                            edTenKhach.setError("T??n kh??ch kh??ng th??? tr???ng");
                            return;
                        }
                        else if(edNamSinh.getText().toString().equals("")){
                            edNamSinh.setError("N??m sinh kh??ng th??? tr???ng");
                            return;
                        }
                        else if(edSDT.getText().toString().equals("")){
                            edSoNuoc.setError("S??? ??i???n tho???i kh??ng th??? tr???ng");
                            return;
                        }
                        String NgayCap="";
                        if(edNgay.getText().toString().equals("")||edThang.getText().toString().equals("")||edNam.getText().toString().equals("")){

                        }
                        else {
                            NgayCap=edNgay.getText().toString()+"/"+edThang.getText().toString()+"/"+edNam.getText().toString();
                        }
                        boolean them=MainActivity.db.capnhatKhach(pt.getID(),edTenKhach.getText().toString(),gt,edNamSinh.getText().toString(),
                                edCMND.getText().toString(),NgayCap,edSDT.getText().toString(),p.getID(),"");
                        if(them==true)
                            Toast.makeText(PhongTroActivity.this,"S???a th??ng tin kh??ch th??nh c??ng",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PhongTroActivity.this,"Kh??ng s???a ???????c",Toast.LENGTH_LONG).show();
                        showKhach();
                        customAdaper.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        new AlertDialog.Builder(PhongTroActivity.this)
                                .setMessage("B???n mu???n xo?? "+pt.getTEN_KH()+"?")
                                .setCancelable(false)
                                .setPositiveButton("Xo??", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        xoaKhach(pt.getID());
                                        arrKhach.remove(pt);
                                        if(arrKhach.size()==0){
                                            tvSoNguoiO.setText("Ph??ng tr???ng");
                                            tvDs.setText("Ch??a c?? kh??ch thu?? ph??ng");
                                        }
                                        else
                                            tvSoNguoiO.setText(arrKhach.size()+" ng?????i ??? tr???");
                                        customAdaper.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("Kh??ng", null)
                                .show();
                    }
                });
                dialog.show();
                return false;
            }
        });
    }
    public void xoaKhach(String id) {
        Integer d=MainActivity.db.xoaKhach(id);
        if(d>0)
            Toast.makeText(this,"Xo?? kh??ch h??ng th??nh c??ng",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this,"Xo?? kh??ch h??ng th???t b???i",Toast.LENGTH_LONG).show();
    }
}
