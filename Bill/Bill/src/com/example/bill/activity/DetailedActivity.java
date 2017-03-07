package com.example.bill.activity;

import java.util.ArrayList;

import com.example.bill.R;
import com.example.bill.db.dao.DataDao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailedActivity extends Activity {

	private TextView show_date;
	private TextView show_person;
	private TextView show_type;
	private TextView show_rmb;
	private TextView show_way;
	private TextView other;
	private TextView show_beizhu;
	private ListView lv_detailed;
	private String date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_detailed);

		initInfo();

		lv_detailed = (ListView) findViewById(R.id.lv_detailed);
		lv_detailed.setAdapter(new MyAdapter());
		Intent intent = getIntent();
		date = intent.getStringExtra("date");
	}

	public class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View view = View.inflate(getApplicationContext(),
					R.layout.detailed_item, null);

			
			show_date = (TextView)view.findViewById(R.id.show_date);
			show_person = (TextView) view.findViewById(R.id.show_person);
			show_type = (TextView) view.findViewById(R.id.show_type);
			show_rmb = (TextView)view. findViewById(R.id.show_rmb);
			show_way = (TextView) view.findViewById(R.id.show_way);
			other = (TextView) view.findViewById(R.id.show_other);
			show_beizhu = (TextView) view.findViewById(R.id.show_beizhu);

			
			ArrayList<String> infos = DataDao.showInfo(getApplicationContext(),
					date);
			if (infos.size() != 0) {
				show_date.setText(infos.get(0));
				show_person.setText(infos.get(1));
				show_type.setText(infos.get(2));
				show_rmb.setText(infos.get(3));
				show_way.setText(infos.get(4));
				other.setText(infos.get(5));
				show_beizhu.setText(infos.get(6));
			} else {
				Toast.makeText(getApplicationContext(), "暂无今日记录", 1).show();
			}
			
			
			
			return view;
		}

	}

	private void initInfo() {

		
	}

}
