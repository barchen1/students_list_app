package com.example.students_app.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    static final Model _instance = new Model();

    public static Model instance(){
        return _instance;
    }
    private Model(){
        for(int i=0; i<20; i++){
            addStudent(new Student("name"+i, ""+i, null, false));
        }
    }

    List<Student> studentList = new LinkedList<>();
    public List<Student> getAllStudents(){
        return studentList;
    }
    public void addStudent(Student st){
        studentList.add(st);
    }

    public void deleteStudent(int pos){
        studentList.remove(pos);
    }
}
