package com.example.students_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.students_app.model.Model;
import com.example.students_app.model.Student;

import java.util.List;

public class StudentDetailsActivity extends AppCompatActivity {

    List<Student> studentList = Model.instance().getAllStudents();
    TextView name, id, phone, address;
    CheckBox cb;
    Button back, edit;
    Intent thisI;
    int pos;
    Student st;
    Intent editIntent;
    int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);


        thisI = getIntent();
        pos = (int)thisI.getSerializableExtra("pos");
        st = studentList.get(pos);
        name = findViewById(R.id.student_details_st_name);
        id = findViewById(R.id.student_details_st_ID);
        phone=findViewById(R.id.student_details_st_phone);
        address=findViewById(R.id.student_details_st_address);
        cb = findViewById(R.id.student_details_cb);
        back=findViewById(R.id.student_details_back_btn);
        edit=findViewById(R.id.student_details_edit_btn);

        this.bind(st);
        edit.setOnClickListener(view -> {
            editIntent = new Intent(this, StudentEditActivity.class);
            editIntent.putExtra("pos",pos);
            startActivityForResult(editIntent,REQUEST_CODE);

        });
        back.setOnClickListener(view -> finish());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result = data.getStringExtra("result");
        if(result.equals("delete"))
            finish();


    }

    public void bind(Student st) {
        name.setText(st.getName());
        id.setText(st.getId());
        phone.setText(st.getPhone());
        address.setText(st.getAddress());
        cb.setChecked(st.getCb());

    }


    @Override
    protected void onStart() {
        super.onStart();
        bind(st);
    }


}
