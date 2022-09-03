package com.example.fooddeliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fooddeliveryapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText editPhone, editName, editPassword;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editPhone = (EditText)findViewById(R.id.editPhone);
        editName = (EditText)findViewById(R.id.editName);
        editPassword = (EditText)findViewById(R.id.editPassword);

        signUp = (Button)findViewById(R.id.signUp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please Wait");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(editPhone.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "Phone number already registered", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            mDialog.dismiss();
                            User user = new User(editName.getText().toString(), editPassword.getText().toString());
                            table_user.child(editPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}