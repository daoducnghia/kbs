package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.NguyenNhan;
import org.springframework.stereotype.Service;

@Service
public interface NguyenNhanInterface extends JpaRepository<NguyenNhan, Integer> {

}
