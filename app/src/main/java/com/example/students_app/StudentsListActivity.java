package com.example.students_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.students_app.model.Model;
import com.example.students_app.model.Student;

import java.util.List;

public class StudentsListActivity extends AppCompatActivity {
    List<Student> studentsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        studentsList = Model.instance().getAllStudents();
        ListView students_list = findViewById(R.id.students_list);
        students_list.setAdapter(new StudentsListAdapter());
        students_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Log.d("Tag", "row was clicked "+pos);
            }
        });
    }
     class  StudentsListAdapter extends BaseAdapter{

         @Override
         public int getCount() {
             return studentsList.size();
         }

         @Override
         public Object getItem(int i) {
             return null;
         }

         @Override
         public long getItemId(int i) {
             return 0;
         }

         @Override
         public View getView(int pos, View view, ViewGroup viewGroup) {

             if(view == null){
                 view = getLayoutInflater().inflate(R.layout.student_list_row, null);
                 CheckBox cb = view.findViewById(R.id.checkBox);
                 cb.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         int pos = (int) cb.getTag();
                         Student st = studentsList.get(pos);
                         st.setCb(cb.isChecked());
                     }
                 });
             }
             Student st = studentsList.get(pos);

             TextView nameTv = view.findViewById(R.id.student_name);
             TextView idTv = view.findViewById(R.id.student_id);
             CheckBox cb = view.findViewById(R.id.checkBox);

             nameTv.setText(st.getName());
             idTv.setText(st.getId());
             cb.setChecked(st.getCb());
             cb.setTag(pos);
             return view;
         }
     }

}