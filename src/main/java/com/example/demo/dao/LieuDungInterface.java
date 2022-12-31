package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.LieuDung;

@Service
public interface LieuDungInterface extends JpaRepository<LieuDung,Integer>{
    @Query(value = "select * from kbs.lieu_dung ld where ld.id_thuoc = ?1 and ld.minage <= ?2 and ld.maxage >= ?2", nativeQuery = true)
    public LieuDung getLieuDungByTuoi(int id_thuoc, int tuoi);
}
