package com.example.demo.dto;

import com.example.demo.enums.AccountType;
import com.example.demo.model.Resume;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private  int id;
    private String accountName;

    @Email
    @NotEmpty
    private String email;

    @JsonIgnore
    private AccountType accountType;


    @Size(min = 10, max = 15)
    private String phoneNumber;

    @NotBlank
    @Size(min = 4, max = 24, message = "Length must be >= 4 and <= 24")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$", message = "Should contain at least one uppercase letter, one number")
    private String password;

    private List<ResumeDto> resumes;

    private List<JobResumeDto> jobResumes;


}
