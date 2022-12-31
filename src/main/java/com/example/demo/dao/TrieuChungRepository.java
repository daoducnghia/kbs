/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.dao;

import com.example.demo.model.TrieuChung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author dovan
 */
@Service
public interface TrieuChungRepository extends JpaRepository<TrieuChung, Integer>{
    @Query(value = "SELECT * FROM kbs.trieu_chung tc WHERE tc.name LIKE ?1", nativeQuery = true)
    public TrieuChung findTrieuChungByName(String name);
}
