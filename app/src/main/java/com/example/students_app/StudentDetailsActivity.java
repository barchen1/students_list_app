package com.example.students_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.students_app.model.Model;
import com.example.students_app.model.Student;

import java.util.List;

public class StudentDetailsActivity extends AppCompatActivity {

    List<Student> studentList = Model.instance().getAllStudents();
    TextView nameTv, idTv, phoneTv, addressTv;
    CheckBox cb;
    Button back,edit;
    Intent thisI;
    int pos;
    Student st;
    public Intent editI;
    int REQUEST_CODE=1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);


        thisI = getIntent();
        pos = (int)thisI.getSerializableExtra("pos");
        st = studentList.get(pos);
        nameTv = findViewById(R.id.student_details_tv_st_name);
        idTv = findViewById(R.id.student_details_tv_st_ID);
        phoneTv=findViewById(R.id.student_details_tv_st_phone);
        addressTv=findViewById(R.id.student_details_tv_st_address);
        cb = findViewById(R.id.student_details_cb);
        back=findViewById(R.id.student_details_back_btn);
        edit=findViewById(R.id.student_details_edit_btn);

        this.bind(st,pos);

//        edit.setOnClickListener(view -> {
//            editI = new Intent(this, StudentEditActivity.class);
//            editI.putExtra("pos",pos);
//            startActivityForResult(editI,REQUEST_CODE);
//
//        });

        back.setOnClickListener(view -> finish());

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result = data.getStringExtra("result");
        if(result.equals("delete"))
            finish();


    }

    public void bind(Student st, int pos) {
        nameTv.setText(st.getName());
        idTv.setText(st.getId());
        phoneTv.setText(st.getPhone());
        addressTv.setText(st.getAddress());
        cb.setChecked(st.getCb());

    }


    @Override
    protected void onStart() {
        super.onStart();
        bind(st,pos);
    }


}
