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


                String taikhoan = edtDKTaiKhoan.getText().toString();
                String matkhau = edtDKMatKhau.getText().toString();


                TaiKhoan taikhoan1 = CreateTaiKhoan();
                if(taikhoan.equals("") || matkhau.equals("")){
                    Toast.makeText(DangkyActivity.this,"Bạn chưa nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                    Log.e("Thông báo : ","Bạn chưa nhập đầy đủ thông tin");
                }
                //Nếu đầy đủ thông tin
                else{
                    //Kiểm tra xem trùng tài khoản không để có thể hiển thị thông báo tài khoản trùng

                    databaseQLPT.AddTaiKhoan(taikhoan1);
                    Toast.makeText(DangkyActivity.this,"Đăng ký thành công ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDKDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private TaiKhoan CreateTaiKhoan(){
        String taikhoan = edtDKTaiKhoan.getText().toString();
        String matkhau = edtDKMatKhau.getText().toString();

        TaiKhoan tk = new TaiKhoan(taikhoan,matkhau);

        return tk;
    }

    private void AnhXa() {
        edtDKMatKhau = findViewById(R.id.dkMatkhau);
        edtDKTaiKhoan = findViewById(R.id.dkTaikhoan);
        btnDKDangKy = findViewById(R.id.dkDangky);
        btnDKDangNhap = findViewById(R.id.dkDangnhap);

    }
}