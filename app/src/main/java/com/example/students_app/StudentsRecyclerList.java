package com.example.students_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.students_app.model.Model;
import com.example.students_app.model.Student;

import java.util.List;

public class StudentsRecyclerList extends AppCompatActivity {
    List<Student> studentsList;
    Button add;
    StudentRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_recycler_list);

        studentsList = Model.instance().getAllStudents();
        RecyclerView list = findViewById(R.id.StudentsRecyclerList);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudentRecyclerAdapter();
        list.setAdapter(adapter);

        add=findViewById(R.id.studentRecycler_add_btn);

        add.setOnClickListener(view->{
            Intent addStudentIntent = new Intent(this,StudentAddActivity.class);
            startActivity(addStudentIntent);

        });
        adapter.setOnItemClickListener((int pos)-> {
            Intent intent = new Intent(this,StudentDetailsActivity.class);
            intent.putExtra("pos",pos);

            startActivity(intent);
        }
        );


    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }
    class StudentViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView id;
        CheckBox cb;
        public StudentViewHolder(@NonNull View itemView, OnItemClickListener listener){
            super(itemView);

            name = itemView.findViewById(R.id.student_name);
            id = itemView.findViewById(R.id.student_id);
            cb = itemView.findViewById(R.id.checkBox);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) cb.getTag();
                    Student st = studentsList.get(pos);
                    st.setCb(cb.isChecked());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Log.d("TAG", "row click " + pos);
                    listener.onItemClick(pos);
                }
            });
        }
        public void bind(Student st, int pos){
            name.setText(st.getName());
            id.setText(st.getId());
            cb.setChecked(st.getCb());
            cb.setTag(pos);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int pos);
    }

    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder>{
        OnItemClickListener listener;
        void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }
        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent, false);
            return new StudentViewHolder(view, listener);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student st = studentsList.get(position);
            holder.bind(st, position);
        }

        @Override
        public int getItemCount() {
            return studentsList.size();
        }
    }

    }