package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import com.example.demo.model.CachDieuTri;

import com.example.demo.model.CachDieuTri_Benh;

import antlr.collections.List;

@Service
public interface CachDieuTriBenhInterface extends JpaRepository<CachDieuTri_Benh, Integer> {
    // @Query(value = "select c.id, c.name from kbs.cach_dieu_tri_benh cb, kbs.benh
    // b, kbs.cach_dieu_tri c where b.id = cb.id_benh and c.id = cb.id_cachdieutri
    // and b.name like ?", nativeQuery = true)
    // public List<CachDieuTri> findAllCDTByBenh(String tenBenh);
}
