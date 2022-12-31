package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.CachDung;

@Service
public interface CachDungInterface extends JpaRepository<CachDung, Integer>{
    @Query(value = "select * from kbs.cach_dung cd where cd.id_thuoc = ?1", nativeQuery = true)
    public CachDung getCachDungByThuoc(int id_thuoc);
}

