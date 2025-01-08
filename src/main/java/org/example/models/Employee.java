package org.example.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private int job_Id;

public Employee(String firstName,String lastName,int age,String email,int job_Id){
    this.firstName = firstName;
    this.lastName =lastName;
    this.age = age;
    this.email =email;
    this.job_Id =job_Id;
}

}
