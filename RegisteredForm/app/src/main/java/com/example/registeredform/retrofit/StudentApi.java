package com.example.registeredform.retrofit;

import com.example.registeredform.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StudentApi {
    @GET("/")
    Call<List<Student>> getAllStudent();

    @POST("/api/student/")
    Call<Student> save(@Body Student student) ;
}
