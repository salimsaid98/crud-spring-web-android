package com.example.registeredform;

import static com.example.registeredform.retrofit.StudentApi.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.registeredform.model.Student;
import com.example.registeredform.retrofit.RetrofitService;
import com.example.registeredform.retrofit.StudentApi;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }
    private void initializeComponents(){
        TextInputEditText textInputEditText1 = findViewById(R.id.text1);
        TextInputEditText textInputEditText2 = findViewById(R.id.text2);
        TextInputEditText textInputEditText3 = findViewById(R.id.text3);
        TextInputEditText textInputEditText4 = findViewById(R.id.text4);
        TextInputEditText textInputEditText5= findViewById(R.id.text5);
        MaterialButton buttonSave = findViewById(R.id.form_buttonSave);

        RetrofitService retrofitService = new RetrofitService();
        StudentApi studentApi = retrofitService.getRetrofit().create(StudentApi.class);

        buttonSave.setOnClickListener(view -> {
            String fname = String.valueOf(textInputEditText1.getText());
            String scname = String.valueOf(textInputEditText2.getText());
            String lname = String.valueOf(textInputEditText3.getText());
            String gender = String.valueOf(textInputEditText4.getText());
            String age= String.valueOf(textInputEditText5.getText());
            Student student = new Student();

            student.setFname(fname);
            student.setScname(scname);
            student.setLname(lname);
            student.setGender(gender);
            student.setAge(age);

            studentApi.save(student)
                    .enqueue(new Callback<Student>() {
                        @Override
                        public void onResponse(Call<Student> call, Response<Student> response) {
                            Toast.makeText(MainActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Student> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred", t);
                        }
                    });

        });


    }
}