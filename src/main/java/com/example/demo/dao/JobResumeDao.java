package com.example.demo.dao;

import com.example.demo.model.JobResume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobResumeDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public JobResumeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //поиск резюме по категории
    public List<JobResume> getResumeByCategory(String category){
        String sql="select * from jobresume where category = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(JobResume.class),category);
    }

    //поиск резюме где зарплата <
    public List<JobResume> getResumeByCategory(Integer salary){
        String sql="select * from jobresume where salary < ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(JobResume.class),salary);
    }

    //поиск резюме где опыт >
    public List<JobResume> getResumeByCategory(int year){
        String sql="select * from jobresume where experience > ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(JobResume.class),year);
    }
}