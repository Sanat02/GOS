package com.example.demo.dao;

import com.example.demo.model.Contacts;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactsDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Contacts> getContactsByResumeId(int id) {
        String sql="select * from contacts where resume_id = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Contacts.class),id);
    }
}
