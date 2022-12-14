/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.dao;

import com.example.demo.model.TrieuChung_Benh;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author dovan
 */
@Service
public interface TrieuChung_BenhRepository extends JpaRepository<TrieuChung_Benh, Integer>{
    @Query(value = "SELECT * FROM kbs.trieu_chung_benh tcb WHERE tcb.id_benh = ?1", nativeQuery = true)
    public List<TrieuChung_Benh> findAllTrieuChungBenhByBenh(int idBenh);
}
