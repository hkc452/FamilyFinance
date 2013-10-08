package com.mingrisoft.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mingrisoft.dao.FlagDAO;
import com.mingrisoft.model.Tb_flag;
//������ǩ
public class Accountflag extends Activity {
	private EditText txtFlag;// ����EditText�������
	private Button btnflagSaveButton;// ����Button�������
	private Button btnflagCancelButton;// ����Button�������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accountflag);

		txtFlag = (EditText) findViewById(R.id.txtFlag);// ��ȡ��ǩ�ı���
		btnflagSaveButton = (Button) findViewById(R.id.btnflagSave);// ��ȡ���水ť
		btnflagCancelButton = (Button) findViewById(R.id.btnflagCancel);// ��ȡȡ����ť
		btnflagSaveButton.setOnClickListener(new OnClickListener() {// Ϊ���水ť���ü����¼�
					@Override
					public void onClick(View arg0) {
						String strFlag = txtFlag.getText().toString();// ��ȡ��ǩ�ı����ֵ
						if (!strFlag.isEmpty()) {// �жϻ�ȡ��ֵ��Ϊ��
							FlagDAO flagDAO = new FlagDAO(Accountflag.this);// ����FlagDAO����
							Tb_flag tb_flag = new Tb_flag(
									flagDAO.getMaxId() + 1, strFlag);// ����Tb_flag����
							flagDAO.add(tb_flag);// ��ӱ�ǩ��Ϣ
							// ������Ϣ��ʾ
							Toast.makeText(Accountflag.this, "��������ǩ��������ӳɹ���",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(Accountflag.this, "�������ǩ��",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		btnflagCancelButton.setOnClickListener(new OnClickListener() {// Ϊȡ����ť���ü����¼�
					@Override
					public void onClick(View arg0) {
						txtFlag.setText("");// ��ձ�ǩ�ı���
					}
				});
	}
}
