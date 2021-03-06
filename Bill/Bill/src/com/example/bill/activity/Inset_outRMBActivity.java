package com.example.bill.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bill.R;
import com.example.bill.db.dao.DataDao;

public class Inset_outRMBActivity extends Activity {
	private static final String[] m_sos = { "社会（保险等）", "房租", "吃饭", "购物",
			"孝顺老人", "学费", "投资", "其它" };
	private static final String[] m_way = { "刷卡", "现金", "支付宝", "微信", "其它" };
	private Spinner in_sos;
	private Spinner in_way;
	private ArrayAdapter<String> adapter_sos, adater_way;
	private EditText pay_date;
	private EditText pay_person;
	private EditText pay_rmb;
	private EditText pay_detail;
	private EditText other;
	private String pay_way_s;
	private String pay_type_s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inset_out_rmb);

		in_sos = (Spinner) findViewById(R.id.in_sos);
		in_way = (Spinner) findViewById(R.id.in_way);

		pay_date = (EditText) findViewById(R.id.pay_date);
		pay_person = (EditText) findViewById(R.id.pay_person);
		pay_rmb = (EditText) findViewById(R.id.pay_rmb);
		pay_detail = (EditText) findViewById(R.id.pay_detail);
		other = (EditText) findViewById(R.id.other);

		adapter_sos = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, m_sos);
		adater_way = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, m_way);

		// 设置下拉列表的风格
		adapter_sos
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adater_way
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// 将adapter 添加到spinner中
		in_sos.setAdapter(adapter_sos);
		in_way.setAdapter(adater_way);

		
		Intent intent = getIntent();	
		String show_date = intent.getStringExtra("show_date");
		pay_date.setHint(show_date);
		
		// 添加事件Spinner事件监听
		in_sos.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				pay_type_s = m_sos[position];

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// 添加事件Spinner事件监听
		in_way.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				pay_way_s = m_way[position];

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// 设置默认值
		in_sos.setVisibility(View.VISIBLE);
		in_way.setVisibility(View.VISIBLE);

	}


	
	public void pay_commit(View v) {
	
		String pay_date_s = pay_date.getHint().toString();
		String pay_person_s = pay_person.getHint().toString();
		String pay_detail_s = pay_detail.getText().toString();
		String pay_rmb_s = pay_rmb.getText().toString();
		String other_s = other.getText().toString();

		System.out.println(pay_date_s+pay_person_s);
		String[] data = new String[10];
		data[0] = pay_date_s;
		data[1] = pay_person_s;
		data[2] = pay_type_s;
		data[3] = pay_detail_s;
		data[4] = pay_rmb_s;
		data[5] = pay_way_s;
		data[6] = other_s;

		boolean success = DataDao.insertInfo(getApplicationContext(), data);
		if (success) {
			startActivity(new Intent(Inset_outRMBActivity.this,MainActivity.class));
			Toast.makeText(getApplicationContext(), "添加成功", 0).show();
		}else{
			startActivity(new Intent(Inset_outRMBActivity.this,MainActivity.class));
			Toast.makeText(getApplicationContext(), "数据库错误，添加失败", 0).show();
		}
	}

}
