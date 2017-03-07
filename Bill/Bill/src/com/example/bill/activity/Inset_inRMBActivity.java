package com.example.bill.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bill.R;

public class Inset_inRMBActivity extends Activity {
	private static final String[] m_sos = { "����", "���", "��Ʊ", "Ͷ��", "����" };
	private static final String[] m_way = { "���п�", "�ֽ�", "֧����", "΢��", "����" };
	private Spinner in_sos;
	private Spinner in_way;
	private ArrayAdapter<String> adapter_sos, adater_way;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inset_in_rmb);

		in_sos = (Spinner) findViewById(R.id.in_sos);
		in_way = (Spinner) findViewById(R.id.in_way);

		adapter_sos = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, m_sos);
		adater_way = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, m_way);

		// ���������б��ķ��
		adapter_sos
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adater_way
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// ��adapter ���ӵ�spinner��
		in_sos.setAdapter(adapter_sos);
		in_way.setAdapter(adater_way);

		// �����¼�Spinner�¼�����
		in_sos.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// Toast.makeText(getApplicationContext(), position, 1).show();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// �����¼�Spinner�¼�����
		in_way.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// Toast.makeText(getApplicationContext(), position, 1).show();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// ����Ĭ��ֵ
		in_sos.setVisibility(View.VISIBLE);
		in_way.setVisibility(View.VISIBLE);

	}
}