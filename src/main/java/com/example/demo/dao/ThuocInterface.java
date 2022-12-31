package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.Thuoc;

@Service
public interface ThuocInterface extends JpaRepository<Thuoc,Integer> {
    @Query(value = "SELECT t.id, t.thuoc, t.description " 
    + "FROM kbs.thuoc_benh tb, kbs.thuoc t, kbs.benh b " 
    + "where tb.id = t.id and tb.id_benh = b.id and tb.id_thuoc = t.id and b.name like ?1", nativeQuery = true)
    public List<Thuoc> getByNameBenh(String tenBenh);

    // @Query(value = "select * from kbs.lieu_dung ld where ld.id_thuoc = ?1 and ld.minage <= ?2 and ld.maxage >= ?2", nativeQuery = true)
    // public LieuDung getLieuDungByTuoi(int id_thuoc, int tuoi);

//     @Query(value = "select * from kbs.cach_dung cd where cd.id_thuoc = ?1", nativeQuery = true)
//     public CachDung getCachDungByThuoc(int id_thuoc);
 }
