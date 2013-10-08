package com.mingrisoft.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mingrisoft.model.Tb_pwd;

public class PwdDAO {
    private DBOpenHelper helper;// ����DBOpenHelper����
    private SQLiteDatabase db;// ����SQLiteDatabase����

    public PwdDAO(Context context) {// ���幹�캯��

        helper = new DBOpenHelper(context);// ��ʼ��DBOpenHelper����
        Log.e("xixi", "create");
    }

    /**
     * ���������Ϣ
     * 
     * @param tb_pwd
     */
    public void add(Tb_pwd tb_pwd) {
        db = helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����
        // ִ������������
        db.execSQL("insert into tb_pwd (password) values (?)", new Object[] { tb_pwd.getPassword() });
    }

    /**
     * ����������Ϣ
     * 
     * @param tb_pwd
     */
    public void update(Tb_pwd tb_pwd) {
        db = helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����
        // ִ���޸��������
        db.execSQL("update tb_pwd set password = ?", new Object[] { tb_pwd.getPassword() });
    }

    /**
     * ����������Ϣ
     * 
     * @return
     */
    public Tb_pwd find() {//model������
        db = helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����
        Log.e("xixi","find");
        // �������벢�洢��Cursor����
        Cursor cursor = db.rawQuery("select password from tb_pwd", null);
        if (cursor.moveToNext()) {// �������ҵ���������Ϣ
            // ������洢��Tb_pwd����
            return new Tb_pwd(cursor.getString(cursor.getColumnIndex("password")));
        }
        return null;// ���û����Ϣ���򷵻�null
    }
   /**
    * �Ƿ�������
    * @return
    */
    public long getCount() {
    	Log.e("xixi","have");
        db = helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����
        Cursor cursor = db.rawQuery("select count(password) from tb_pwd", null);// ��ȡ������Ϣ�ļ�¼��
        if (cursor.moveToNext()) {// �ж�Cursor���Ƿ�������
            return cursor.getLong(0);// �����ܼ�¼��
        }
        return 0;// ���û�����ݣ��򷵻�0
    }
}
