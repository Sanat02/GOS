package com.example.demo.dao;

import com.example.demo.model.JobList;
import com.example.demo.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobsListDao {
    private final JdbcTemplate jdbcTemplate;


    public JobsListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Выборка всех вакансий.
    public List<JobList> getAllJobs() {

        String sql = "select * from job_listings";
        List<User> listUsers = getAllUsers();


        var list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JobList.class));
        list.forEach(jobList -> {
            User publisher = listUsers.stream().filter(user -> user.getId() == jobList.getPublisher_id())
                    .findFirst().orElse(null);
            jobList.setPublisher(publisher);
        });
        return list;
    }


    public List<User> getAllUsers() {
        String sql = "select * from users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    //Выборка вакансий по категориям.
    public List<JobList> getJobByCategory(String category){
        String sql="select * from job_listings where category = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(JobList.class),category);
    }
}