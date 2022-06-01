package com.example.room_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.room_app.Base.TaiKhoan;

public class DangkyActivity extends AppCompatActivity {
    EditText edtDKTaiKhoan,edtDKMatKhau;
    Button btnDKDangKy,btnDKDangNhap;
    DatabaseQLPT databaseQLPT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        AnhXa();
        databaseQLPT = new DatabaseQLPT(this);

        btnDKDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void AnhXa() {
        edtDKMatKhau = findViewById(R.id.dkMatkhau);
        edtDKTaiKhoan = findViewById(R.id.dkTaikhoan);
        btnDKDangKy = findViewById(R.id.dkDangky);
        btnDKDangNhap = findViewById(R.id.dkDangnhap);

    }
}