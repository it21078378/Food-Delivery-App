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

public class SignIn extends AppCompatActivity {

    EditText editPhone, editPassword;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        editPhone = (EditText)findViewById(R.id.editPhone);
        editPassword = (EditText)findViewById(R.id.editPassword);
        signIn = (Button)findViewById(R.id.signIn);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Please Wait");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(editPhone.getText().toString()).exists()){

                            mDialog.dismiss();
                            User user = dataSnapshot.child(editPhone.getText().toString()).getValue(User.class);
                            if(user.getPassword().equals(editPassword.getText().toString())){
                                Toast.makeText(SignIn.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(SignIn.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else{
                            Toast.makeText(SignIn.this, "User not exists", Toast.LENGTH_SHORT).show();
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