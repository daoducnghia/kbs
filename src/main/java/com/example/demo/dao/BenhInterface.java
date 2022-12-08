package com.example.demo.dao;

import com.example.demo.model.Benh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface BenhInterface extends JpaRepository<Benh, Integer>{
    
}
