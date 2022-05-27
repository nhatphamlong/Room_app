package com.example.room_app.Base;

public class TaiKhoan {
    private int mId;
    private  String mTenTaiKhoan;
    private  String mMatKhau;

    public TaiKhoan(String mTenTaiKhoan, String mMatKhau) {
        this.mTenTaiKhoan = mTenTaiKhoan;
        this.mMatKhau = mMatKhau;

    }


    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTenTaiKhoan() {
        return mTenTaiKhoan;
    }

    public void setmTenTaiKhoan(String mTenTaiKhoan) {
        this.mTenTaiKhoan = mTenTaiKhoan;
    }

    public String getmMatKhau() {
        return mMatKhau;
    }

    public void setmMatKhau(String mMatKhau) {
        this.mMatKhau = mMatKhau;
    }
}
