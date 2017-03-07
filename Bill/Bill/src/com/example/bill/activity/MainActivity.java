package com.example.bill.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.bill.R;
import com.example.bill.db.MyHelper;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener;
import com.squareup.timessquare.CalendarPickerView.SelectionMode;

@SuppressLint("SimpleDateFormat")
public class MainActivity extends Activity implements OnClickListener {
	private ListView listview;
	private RadioButton look;
	private RadioButton insert;
	private RadioButton update;
	boolean b1;
	boolean b2;
	boolean b3;
	private CalendarPickerView calendar;
	ArrayList<String> list = new ArrayList<String>();
	private AlertDialog.Builder builder;
	private long millis1 = 0;
	private DateFormat format1;
	Date calar_date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ��ʼ���˵�����
		initMenuData();

		format1 = new SimpleDateFormat("yyyy��MM��dd��");

		// ��ʼ�� �ؼ�
		look = (RadioButton) findViewById(R.id.button1);
		insert = (RadioButton) findViewById(R.id.button2);
		update = (RadioButton) findViewById(R.id.button3);
		look.setOnClickListener(this);
		insert.setOnClickListener(this);
		update.setOnClickListener(this);
		listview = (ListView) findViewById(R.id.listView1);

		// ��ʼ�������ؼ�
		final Calendar nextYear = Calendar.getInstance();
		nextYear.add(Calendar.YEAR, 5);
		final Calendar lastYear = Calendar.getInstance();
		lastYear.add(Calendar.YEAR, -5);
		calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
		calendar.init(lastYear.getTime(), nextYear.getTime())
				.inMode(SelectionMode.SINGLE).withSelectedDate(new Date());
		// ��������Ч��
		TranslateAnimation am = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT,
				0, Animation.RELATIVE_TO_PARENT, 0.9f,
				Animation.RELATIVE_TO_PARENT, 0f);
		am.setDuration(1000);
		am.setRepeatCount(0);
		calendar.setAnimation(am);

		// Ĭ�ϲ˵�ѡ��
		look.setChecked(true);

		builder = new AlertDialog.Builder(MainActivity.this);

		// ����ѡ��ĳ��ļ���
		calendar.setOnDateSelectedListener(new OnDateSelectedListener() {

			@Override
			public void onDateUnselected(Date date) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDateSelected(Date date) {

				calar_date = date;

				// ˫�����ж�
				long millis = System.currentTimeMillis();
				if (millis - millis1 < 500) {

					Intent it = new Intent(MainActivity.this,DetailedActivity.class);
					it.putExtra("date", format1.format(date));
					startActivityForResult(it, 0);
					overridePendingTransition(R.anim.scale_translate,
							R.anim.scale_translate);
				}
				millis1 = System.currentTimeMillis();

			}
		});

		// �˵��б�����
		listview.setAdapter(new BaseAdapter() {
			private View view = null;

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					view = View.inflate(getApplicationContext(), R.layout.item,
							null);
				} else {
					view = convertView;
				}
				TextView sele = (TextView) view.findViewById(R.id.sele);
				sele.setText(list.get(position));
				return view;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return list.size();
			}
		});

		// listview�˵����
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				switch (position) {
				// ����
				case 0:

					showDialog();

					break;
				}

			}

		});

		// �������� ��̬�������˵�״̬�ı仯
		listview.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

				if (firstVisibleItem >= 0 && firstVisibleItem < 5) {
					look.setChecked(true);
					insert.setChecked(false);
					update.setChecked(false);
				} else if (firstVisibleItem >= 5 && firstVisibleItem < 10) {
					look.setChecked(false);
					insert.setChecked(true);
					update.setChecked(false);
				} else if (firstVisibleItem >= 10) {
					look.setChecked(false);
					insert.setChecked(false);
					update.setChecked(true);
				}
			}
		});

	}

	/**
	 * ��ʼ���˵�����
	 */
	private void initMenuData() {

		list.add("����");
		list.add("�޸�");
		list.add("ɾ��");
		list.add("��Ŀ�ܽ�");
		list.add("����SD��");
		list.add("������Ŀ");
		list.add("�·��ܽ�");
		list.add("����ܽ�");
		list.add("���˹滮");
		list.add("�����˵�");
		list.add("�������");
		list.add("��Ч����");
		list.add("������");
		list.add("��ʾ����");
		list.add("�汾/����");
	}

	/**
	 * ��ʾ��
	 */
	private void showDialog() {
		builder.setPositiveButton("����", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				Intent intent = new Intent(MainActivity.this,
						Inset_inRMBActivity.class);

				// intent.putExtra("show_date", format1.format(calar_date));

				startActivity(intent);
			}
		});
		builder.setNegativeButton("֧��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				Intent intent = new Intent(MainActivity.this,
						Inset_outRMBActivity.class);
				intent.putExtra("show_date", format1.format(calar_date));
				startActivity(intent);

			}
		});
		builder.show();
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.button1:
			listview.setSelection(0);

			break;
		case R.id.button2:

			listview.setSelection(5);

			break;

		case R.id.button3:

			listview.setSelection(10);

			break;
		}

	}

}