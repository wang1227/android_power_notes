package com.vtmer.yann.powernotes;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class NotesListFragment extends ListFragment {
	
	private static final String TAG = "NotesListFragment";

	private ListView lv;
	private ArrayList<Note> mNotes;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.fragment_notes_list, container, false);
		
		//适配器
		mNotes = NoteLab.get(getActivity()).getNotes();
		NoteAdapter adapter = new NoteAdapter(mNotes);
		lv = (ListView) v.findViewById(android.R.id.list);
		lv.setAdapter(adapter);
		
		//设置空视图
		lv.setEmptyView(v.findViewById(android.R.id.empty));
		
		return v;
	}
	
	private class NoteAdapter extends ArrayAdapter<Note> {

		public NoteAdapter(ArrayList<Note> notes) {
			//调用超类的构造方法来绑定Crime对象的数组列表
			//没有使用预定义布局，传入参数0作为作为布局ID参数
			super(getActivity(), 0, notes);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// 检查传入视图是否为复用对象，不是则从定制布局中产生新的视图对象
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.fragment_notes_list_item, null);
			}
			Log.d(TAG, "get the view of" + position);
			Note n = getItem(position);
			
			TextView titleTextView = (TextView) convertView.findViewById(R.id.notes_list_title_textView);
			 		 titleTextView.setText(n.getTitle());
			TextView dateTextView = (TextView) convertView.findViewById(R.id.notes_list_date_textView);
			 		  dateTextView.setText(n.getDate().toString());
			CheckBox solvedCheckBox = (CheckBox) convertView.findViewById(R.id.notes_list_solved_checkBox);
		     		  solvedCheckBox.setChecked(n.isSolved());
			
			return convertView;
		}	
	}
}
