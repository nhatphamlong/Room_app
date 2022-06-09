package com.example.room_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DangNhapActivity extends AppCompatActivity {
    EditText edtTaiKhoan, edtMatKhau;
    Button btnDangNhap, btnDangKy;
    DatabaseQLPT databaseQLPT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        AnhXa();
        databaseQLPT = new DatabaseQLPT(this);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this,DangkyActivity.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tentaikhoan = edtTaiKhoan.getText().toString();
                String matkhau = edtMatKhau.getText().toString();

                Cursor cursor = databaseQLPT.getData();
                while (cursor.moveToNext()){
                    String datatentaikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);

                    if(datatentaikhoan.equals(tentaikhoan) && datamatkhau.equals(matkhau)){
                        int idd = cursor.getInt(0);
                        String tentk = cursor.getString(1);



                        Intent intent = new Intent(DangNhapActivity.this,MainActivity.class);


                        intent.putExtra("idd",idd);
                        intent.putExtra("tentaikhoan",tentk);

                        startActivity(intent);
                        Log.e("Đăng nhập : ","Thành công");
                    }
                    else{
                        Log.e("Đăng nhập : ","Không Thành công");
                    }
                }

                cursor.moveToFirst();
                cursor.close();
            }
        });
    }

    private void AnhXa() {
        edtTaiKhoan = findViewById(R.id.taikhoan);
        edtMatKhau = findViewById(R.id.matkhau);
        btnDangNhap = findViewById(R.id.dangnhap);
        btnDangKy = findViewById(R.id.dangky);
    }
}