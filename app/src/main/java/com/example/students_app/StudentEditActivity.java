package com.example.students_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.students_app.model.Model;
import com.example.students_app.model.Student;

import java.util.List;

public class StudentEditActivity extends AppCompatActivity {
    List<Student> data = Model.instance().getAllStudents();
    EditText name, id, phone, address;
    CheckBox cb;
    Button cancel,save,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);

        Intent thisI = getIntent();
        int pos = (int)thisI.getSerializableExtra("pos");

        Student st = data.get(pos);
        name = findViewById(R.id.student_edit_Pt_name);
        id = findViewById(R.id.student_edit_Pt_id);
        phone=findViewById(R.id.student_edit_Pt_phone);
        address=findViewById(R.id.student_edit_Pt_address);
        cb = findViewById(R.id.student_edit_cb);
        cancel=findViewById(R.id.student_edit_cancel_btn);
        delete=findViewById(R.id.student_edit_delete_btn);
        save=findViewById(R.id.student_edit_save_btn);

        this.bind(st);

        save.setOnClickListener(view -> {
            this.bindBack(pos);
            Intent result = new Intent();
            result.putExtra("result", "save");
            setResult(RESULT_OK, result);
            finish();

        });

        delete.setOnClickListener(view->{
            Model.instance().deleteStudent(pos);
            Intent result = new Intent();
            result.putExtra("result", "delete");
            setResult(RESULT_OK, result);
            finish();
        });

        cancel.setOnClickListener(view ->
        {
            Intent result = new Intent();
            result.putExtra("result", "cancel");
            setResult(RESULT_OK, result);
            finish();
        });

    }

    private void bindBack( int pos) {
        data.get(pos).setName(name.getText().toString());
        data.get(pos).setId(id.getText().toString());
        data.get(pos).setPhone(phone.getText().toString());
        data.get(pos).setAddress(address.getText().toString());
        data.get(pos).setCb(cb.isChecked());
    }

    public void bind(Student st) {
        name.setText(st.getName());
        id.setText(st.getId());
        phone.setText(st.getPhone());
        address.setText(st.getAddress());
        cb.setChecked(st.getCb());

    }
}
