package com.example.mad_1;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class StudentAdapter extends RecyclerView.Adapter<StudentViewHolder> {
    private final LayoutInflater layoutInflater;
    private final Vector<Student> students;

//    public StudentAdapter(@NonNull View itemView) {
//        super(itemView);
//    }

    public StudentAdapter(LayoutInflater layoutInflater, Vector<Student> students) {
//        super();
        this.layoutInflater = layoutInflater;
        this.students = students;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(layoutInflater.inflate(R.layout.item_student, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.setStudent(students.get(position));
    }

    public int getItemCount() {
        return students.size();
    }
}
