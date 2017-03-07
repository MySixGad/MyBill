package com.example.bill.activity;

import com.example.bill.R;
import com.example.bill.db.MyHelper;
import com.example.bill.utils.PreferenceUtils;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.Toast;

public class SplashActivity extends Activity  {

	private RadioButton dog_stu;
	private RadioButton dog_work;
	private RadioButton cat_work;
	private RadioButton has_son;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		// ��ʼ���ؼ�
		dog_stu = (RadioButton) findViewById(R.id.dog_stu); // 1
		dog_work = (RadioButton) findViewById(R.id.dog_work); // 2
		cat_work = (RadioButton) findViewById(R.id.cat_work); // 3
		has_son = (RadioButton) findViewById(R.id.has_son); // 4

		// �ж��Ƿ�ѡ��
		ifSelected();

		// �ж��Ƿ��һ��ʹ�� ��ת��ҳ��
		boolean isFristUse = PreferenceUtils.getBoolean(
				getApplicationContext(),
				com.example.bill.utils.Constants.FRIST_USE, true);
		if (!isFristUse) {
			Intent it = new Intent(this, MainActivity.class);
			startActivity(it);
			finish();
		}
		
		
		//�������ݿ�
		MyHelper hh=new MyHelper(getApplicationContext());
		SQLiteDatabase db = hh.getReadableDatabase();
		
	}

	/**
	 * �ж��Ƿ�ѡ��
	 */
	private boolean ifSelected() {
		if (dog_stu.isChecked()) {
			savaThem(1);

		} else if (dog_work.isChecked()) {
			savaThem(2);

		} else if (cat_work.isChecked()) {
			savaThem(3);

		} else if (has_son.isChecked()) {
			savaThem(4);
		} else {
			return false;
		}
		return true;
	}

	/**
	 * ��������ѡ���Ⱥ��
	 * 
	 * @param i
	 */
	private void savaThem(int i) {

		PreferenceUtils.setInt(getApplicationContext(),
				com.example.bill.utils.Constants.USER_TYPE, i);

	}

	// �������
	public void open(View v) {

		// �ж��ĸ���ûѡ�� ������� ��������ת
		boolean ifSelected = ifSelected();
		if (ifSelected == false) {
			Toast.makeText(getApplicationContext(), "��ѡ�������ڵ�Ⱥ�壡", 1).show();
			return;
		}

		Intent it = new Intent(this, MainActivity.class);
		startActivityForResult(it, 0);
		overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
		finish();
		PreferenceUtils.setBoolean(getApplicationContext(),
				com.example.bill.utils.Constants.FRIST_USE, false);
	}
}