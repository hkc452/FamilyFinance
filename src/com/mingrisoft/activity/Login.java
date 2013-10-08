package com.mingrisoft.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mingrisoft.dao.PwdDAO;

public class Login extends Activity {
	private EditText txtlogin;// 创建EditText对象
	private Button btnlogin, btnclose;// 创建两个Button对象

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);// 设置布局文件

		txtlogin = (EditText) findViewById(R.id.txtLogin);// 获取密码文本框
		btnlogin = (Button) findViewById(R.id.btnLogin);// 获取登录按钮
		btnclose = (Button) findViewById(R.id.btnClose);// 获取取消按钮

		btnlogin.setOnClickListener(new OnClickListener() {// 为登录按钮设置监听事件
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Login.this, MainActivity.class);// 创建Intent对象
				PwdDAO pwdDAO = new PwdDAO(Login.this);// 创建PwdDAO对象
				// 判断是否有密码及是否输入了密码
				if ((pwdDAO.getCount() == 0 || pwdDAO.find().getPassword()
						.isEmpty())
						&& txtlogin.getText().toString().isEmpty()) {
					startActivity(intent);// 启动主Activity
					Login.this.finish();//把登陆页面给关闭了
				} else {
					// 判断输入的密码是否与数据库中的密码一致
					if (pwdDAO.find().getPassword()
							.equals(txtlogin.getText().toString())) {
						startActivity(intent);// 启动主Activity
					} else {
						// 弹出信息提示
						Toast.makeText(Login.this, "请输入正确的密码！",
								Toast.LENGTH_SHORT).show();
						 txtlogin.requestFocus();//定位到密码框
					}
				}
				txtlogin.setText("");// 清空密码文本框
			}
		});

		btnclose.setOnClickListener(new OnClickListener() {// 为取消按钮设置监听事件
			@Override
			public void onClick(View arg0) {
				finish();// 退出当前程序
			}
		});
	}
}
