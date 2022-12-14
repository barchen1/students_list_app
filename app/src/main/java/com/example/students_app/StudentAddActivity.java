package com.example.students_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.students_app.model.Model;
import com.example.students_app.model.Student;

import java.util.List;

public class StudentAddActivity extends AppCompatActivity {
    List<Student> studentList = Model.instance().getAllStudents();
    EditText name, id, phone, address;
    CheckBox cb;
    Button cancel, save;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add);

        name = (EditText)findViewById(R.id.student_add_st_Pt_name);
        id = findViewById(R.id.student_add_st_Pt_id);
        phone=findViewById(R.id.student_add_st_Pt_phone);
        address=findViewById(R.id.student_add_st_Pt_address);
        cb = findViewById(R.id.student_add_st_cb);
        cancel=findViewById(R.id.student_add_st_cancel_btn);
        save=findViewById(R.id.student_add_st_save_btn);


        cancel.setOnClickListener(view -> finish());
        save.setOnClickListener((view)->{
            Student st=new Student(name.getText().toString(), id.getText().toString(), cb.isChecked(), phone.getText().toString(), address.getText().toString());
            studentList.add(st);
            finish();
        });

    }
}