package com.mingrisoft.activity;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mingrisoft.dao.InaccountDAO;
import com.mingrisoft.dao.OutaccountDAO;
import com.mingrisoft.model.Tb_inaccount;
import com.mingrisoft.model.Tb_outaccount;
//�����֧������
public class InfoManage extends Activity {
	private static final int DATE_DIALOG_ID = 0;// �������ڶԻ�����
	private TextView tvtitle, textView;// ��������TextView����
	private EditText txtMoney, txtTime, txtHA, txtMark;// ����4��EditText����
	private Spinner spType;// ����Spinner����
	private Button btnEdit, btnDel;// ��������Button����
	private String[] strInfos;// �����ַ�������
	private String strid, strType;// ���������ַ����������ֱ�������¼��Ϣ��ź͹�������

	private int mYear;// ��
	private int mMonth;// ��
	private int mDay;// ��

	private OutaccountDAO outaccountDAO = new OutaccountDAO(InfoManage.this);// ����OutaccountDAO����
	private InaccountDAO inaccountDAO = new InaccountDAO(InfoManage.this);// ����InaccountDAO����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infomanage);// ���ò����ļ�
		tvtitle = (TextView) findViewById(R.id.inouttitle);// ��ȡ�����ǩ����
		textView = (TextView) findViewById(R.id.tvInOut);// ��ȡ�ص�/�����ǩ����
		txtMoney = (EditText) findViewById(R.id.txtInOutMoney);// ��ȡ����ı���
		txtTime = (EditText) findViewById(R.id.txtInOutTime);// ��ȡʱ���ı���
		spType = (Spinner) findViewById(R.id.spInOutType);// ��ȡ��������б�
		txtHA = (EditText) findViewById(R.id.txtInOut);// ��ȡ�ص�/����ı���
		txtMark = (EditText) findViewById(R.id.txtInOutMark);// ��ȡ��ע�ı���
		btnEdit = (Button) findViewById(R.id.btnInOutEdit);// ��ȡ�޸İ�ť
		btnDel = (Button) findViewById(R.id.btnInOutDelete);// ��ȡɾ����ť

		Intent intent = getIntent();// ����Intent����
		Bundle bundle = intent.getExtras();// ��ȡ��������ݣ���ʹ��Bundle��¼
		strInfos = bundle.getStringArray(Showinfo.FLAG);// ��ȡBundle�м�¼����Ϣ
		strid = strInfos[0];// ��¼id
		strType = strInfos[1];// ��¼����
		if (strType.equals("btnoutinfo")) {// ���������btnoutinfo

			tvtitle.setText("֧������");// ���ñ���Ϊ��֧������
			textView.setText("��  �㣺");// ���á��ص�/�������ǩ�ı�Ϊ���� �㣺��
			// ���ݱ�Ų���֧����Ϣ�����洢��Tb_outaccount������
			Tb_outaccount tb_outaccount = outaccountDAO.find(Integer
					.parseInt(strid));
			txtMoney.setText(String.valueOf(tb_outaccount.getMoney()));// ��ʾ���
			txtTime.setText(tb_outaccount.getTime());// ��ʾʱ��
			spType.setPrompt(tb_outaccount.getType());// ��ʾ���
			txtHA.setText(tb_outaccount.getAddress());// ��ʾ�ص�
			txtMark.setText(tb_outaccount.getMark());// ��ʾ��ע
		} else if (strType.equals("btnininfo")) {// ���������btnininfo

			tvtitle.setText("�������");// ���ñ���Ϊ���������
			textView.setText("�����");// ���á��ص�/�������ǩ�ı�Ϊ���������
			// ���ݱ�Ų���������Ϣ�����洢��Tb_outaccount������
			Tb_inaccount tb_inaccount = inaccountDAO.find(Integer
					.parseInt(strid));
			txtMoney.setText(String.valueOf(tb_inaccount.getMoney()));// ��ʾ���
			txtTime.setText(tb_inaccount.getTime());// ��ʾʱ��
			spType.setPrompt(tb_inaccount.getType());// ��ʾ���
			txtHA.setText(tb_inaccount.getHandler());// ��ʾ���
			txtMark.setText(tb_inaccount.getMark());// ��ʾ��ע
		}

		txtTime.setOnClickListener(new OnClickListener() {// Ϊʱ���ı������õ��������¼�
			@Override
			public void onClick(View arg0) {
				showDialog(DATE_DIALOG_ID);// ��ʾ����ѡ��Ի���
			}
		});

		btnEdit.setOnClickListener(new OnClickListener() {// Ϊ�޸İ�ť���ü����¼�
			@Override
			public void onClick(View arg0) {
				if (strType.equals("btnoutinfo")) {// �ж����������btnoutinfo

					Tb_outaccount tb_outaccount = new Tb_outaccount();// ����Tb_outaccount����
					tb_outaccount.setid(Integer.parseInt(strid));// ���ñ��
					tb_outaccount.setMoney(Double.parseDouble(txtMoney
							.getText().toString()));// ���ý��
					tb_outaccount.setTime(txtTime.getText().toString());// ����ʱ��
					tb_outaccount.setType(spType.getSelectedItem().toString());// �������
					tb_outaccount.setAddress(txtHA.getText().toString());// ���õص�
					tb_outaccount.setMark(txtMark.getText().toString());// ���ñ�ע
					outaccountDAO.update(tb_outaccount);// ����֧����Ϣ
				} else if (strType.equals("btnininfo")) {// �ж����������btnininfo

					Tb_inaccount tb_inaccount = new Tb_inaccount();// ����Tb_inaccount����
					tb_inaccount.setid(Integer.parseInt(strid));// ���ñ��
					tb_inaccount.setMoney(Double.parseDouble(txtMoney.getText()
							.toString()));// ���ý��
					tb_inaccount.setTime(txtTime.getText().toString());// ����ʱ��
					tb_inaccount.setType(spType.getSelectedItem().toString());// �������
					tb_inaccount.setHandler(txtHA.getText().toString());// ���ø��
					tb_inaccount.setMark(txtMark.getText().toString());// ���ñ�ע
					inaccountDAO.update(tb_inaccount);// ����������Ϣ
				}
				// ������Ϣ��ʾ
				Toast.makeText(InfoManage.this, "�����ݡ��޸ĳɹ���", Toast.LENGTH_SHORT)
						.show();
			}
		});

		btnDel.setOnClickListener(new OnClickListener() {// Ϊɾ����ť���ü����¼�
			@Override
			public void onClick(View arg0) {
				if (strType.equals("btnoutinfo")) {// �ж����������btnoutinfo

					outaccountDAO.detele(Integer.parseInt(strid));// ���ݱ��ɾ��֧����Ϣ
				} else if (strType.equals("btnininfo")) {// �ж����������btnininfo

					inaccountDAO.detele(Integer.parseInt(strid));// ���ݱ��ɾ��������Ϣ
				}
				Toast.makeText(InfoManage.this, "�����ݡ�ɾ���ɹ���", Toast.LENGTH_SHORT)
						.show();
			}
		});

		final Calendar c = Calendar.getInstance();// ��ȡ��ǰϵͳ����
		mYear = c.get(Calendar.YEAR);// ��ȡ���
		mMonth = c.get(Calendar.MONTH);// ��ȡ�·�
		mDay = c.get(Calendar.DAY_OF_MONTH);// ��ȡ����
		updateDisplay();// ��ʾ��ǰϵͳʱ��
	}

	@Override
	protected Dialog onCreateDialog(int id) {// ��дonCreateDialog����

		switch (id) {
		case DATE_DIALOG_ID:// ��������ѡ��Ի���
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;// Ϊ��ݸ�ֵ
			mMonth = monthOfYear;// Ϊ�·ݸ�ֵ
			mDay = dayOfMonth;// Ϊ�츳ֵ
			updateDisplay();// ��ʾ���õ�����
		}
	};

	private void updateDisplay() {
		// ��ʾ���õ�ʱ��
		txtTime.setText(new StringBuilder().append(mYear).append("-")
				.append(mMonth + 1).append("-").append(mDay));
	}
	@Override
	protected void onPause(){
		super.onPause();
		Log.e("hehe", "infopause");
	}
	@Override
	protected void onStop(){
		super.onStop();
		Log.e("hehe", "infostop");
	}
	@Override
	protected void onDestroy(){
		super.onDestroy();
		Log.e("hehe", "infodes");
	}
}
