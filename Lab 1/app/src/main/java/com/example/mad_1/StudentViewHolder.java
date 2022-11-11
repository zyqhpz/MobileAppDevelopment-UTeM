package com.example.mad_1;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentViewHolder extends RecyclerView.ViewHolder {
    private final TextView lblFullName, lblStudNo, lblGender, lblBirthdate, lblEmail, lblState;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        this.lblFullName = itemView.findViewById(R.id.lblFullName);
        this.lblStudNo = itemView.findViewById(R.id.lblStudNo);
        this.lblGender = itemView.findViewById(R.id.lblGender);
        this.lblBirthdate = itemView.findViewById(R.id.lblBirthdate);
        this.lblEmail = itemView.findViewById(R.id.lblEmail);
        this.lblState = itemView.findViewById(R.id.lblState);
    }

    public void setStudent(Student student) {
        lblFullName.setText((student.getFullname()));
        lblStudNo.setText(student.getStudNo());
        lblBirthdate.setText(student.getBirthdate());
        lblEmail.setText(student.getEmail());
        lblGender.setText(student.getGender());
        lblState.setText(student.getState());
    }
}
