package com.example.mad_1.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mad_1.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsDB extends SQLiteOpenHelper {

    public static final String dbName = "dbstudent";
    public static final String tblNameStudents = "students";
    public static final String colStudName = "stud_name";
    public static final String colStudGender = "stud_gender";
    public static final String colStudDob = "stud_dob";
    public static final String colStudNo = "stud_no";
    public static final String colStudState = "stud_state";
    public static final String colStudEmail = "stud_email";

    public static final String strCreateTableStudents = "CREATE TABLE "+ tblNameStudents + " ("+ colStudNo +
            " INTEGER PRIMARY KEY, " + colStudName +" TEXT, " + colStudGender +" TEXT,"+ colStudDob +" DATE,"+ colStudState +" TEXT,"+ colStudEmail +" TEXT)";

    public static  final String strDropTableStudents = "DROP TABLE IF EXISTS "+ tblNameStudents;

    private static int version = 1;

    public StudentsDB(@Nullable Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(strCreateTableStudents);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(strDropTableStudents);
        onCreate(db);
    }

    public float fnInsertStudent(Student student)
    {
        float retResult = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colStudName, student.getFullname());
        values.put(colStudGender, student.getGender());
        values.put(colStudDob, student.getBirthdate());
        values.put(colStudState, student.getState());
        values.put(colStudEmail, student.getEmail());

        retResult = db.insert(tblNameStudents, null, values);
        return  retResult;
    }

    public Student fnGetStudent(int intStudNo)
    {
        Student student = new Student();
        String strSelQry = "Select * from "+ tblNameStudents + " where "+ colStudNo +" = " + intStudNo;
        Cursor cursor = this.getReadableDatabase().rawQuery(strSelQry,null);

        if(cursor != null)
        {
            cursor.moveToFirst();
        }
        student.setFullname(cursor.getString(cursor.getColumnIndex(colStudName)));
        student.setGender(cursor.getString(cursor.getColumnIndex(colStudGender)));
        student.setBirthdate(cursor.getString(cursor.getColumnIndex(colStudDob)));
        student.setState(cursor.getString(cursor.getColumnIndex(colStudState)));
        student.setEmail(cursor.getString(cursor.getColumnIndex(colStudEmail)));

        return student;
    }

    public int fnUpdateStudent(Student student)
    {
        int retResult = 0;
        ContentValues values = new ContentValues();
        values.put(colStudName, student.getFullname());
        values.put(colStudGender, student.getGender());
        values.put(colStudDob, student.getBirthdate());
        values.put(colStudState, student.getState());
        values.put(colStudEmail, student.getEmail());

        String[] argg = {String.valueOf(student.getStudNo())};

        this.getWritableDatabase().update(tblNameStudents, values, colStudNo + " = ?", argg);
        return  retResult;
    }

    public List<Student> fnGetAllStudents()
    {
        List<Student> listStudents = new ArrayList<Student>();

        String strSelAll = "Select * from " + tblNameStudents;

        Cursor cursor = this.getReadableDatabase().rawQuery(strSelAll,null);
        if(cursor.moveToFirst())
        {
            do {
                Student student = new Student();

                student.setStudNo(String.valueOf(cursor.getInt(cursor.getColumnIndex(colStudNo))));
                student.setFullname(cursor.getString(cursor.getColumnIndex(colStudName)));
                student.setGender(cursor.getString(cursor.getColumnIndex(colStudGender)));
                student.setBirthdate(cursor.getString(cursor.getColumnIndex(colStudDob)));
                student.setState(cursor.getString(cursor.getColumnIndex(colStudState)));
                student.setEmail(cursor.getString(cursor.getColumnIndex(colStudEmail)));

                listStudents.add(student);

            }while(cursor.moveToNext());

        }

        return listStudents;
    }

}