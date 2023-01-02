package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BenhInterface;
import com.example.demo.dao.CachDungInterface;
import com.example.demo.dao.LieuDungInterface;
import com.example.demo.dao.ThuocInterface;
import com.example.demo.model.Benh;
import com.example.demo.model.CachDung;
import com.example.demo.model.LieuDung;
import com.example.demo.model.Thuoc;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
public class KB3Controller {

    @Autowired
    BenhInterface benhInterface;

    @Autowired
    ThuocInterface thuocInterface;

    @Autowired
    LieuDungInterface lieuDungInterface;

    @Autowired
    CachDungInterface cachDungInterface;

    @GetMapping("/get-all-benh")
    public List<Benh> getAllBenh() {
        List<String> listBenh = new ArrayList<>();

        try {
            List<Benh> list = (List<Benh>) benhInterface.findAll();

            // for (Benh i : list) {
            //     listBenh.add(i.getName());
            // }
            return list;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @PostMapping("/get-all-thuoc")
    public List<Thuoc> getAllThuocByBenh(@RequestBody Benh benh) {
        List<String> listThuoc = new ArrayList<>();

        try {
            List<Thuoc> list = (List<Thuoc>) thuocInterface.getByNameBenh(benh.getName());

            // for (Thuoc i : list) {
            //     listThuoc.add(i.getThuoc());
            // }
            return list;
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    @PostMapping("/get-ld-cd")
    public ThongTin getLdCdByThuoc(@RequestBody ThongTin tt) {
        try {
            // System.out.println(thuocInterface.getCachDungByThuoc(tt.getId_thuoc()));
            tt.setCd(cachDungInterface.getCachDungByThuoc(tt.getId_thuoc()));
            tt.setLd(lieuDungInterface.getLieuDungByTuoi(tt.getId_thuoc(), tt.getTuoi()));
            
            return tt;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}

class ThongTin {
    // id thuoc, tuoi
    private int tuoi;
    private int id_thuoc;
    private LieuDung ld;
    private CachDung cd;

    public ThongTin(int tuoi, int id_thuoc, LieuDung ld, CachDung cd) {
        this.tuoi = tuoi;
        this.id_thuoc = id_thuoc;
        this.ld = ld;
        this.cd = cd;
    }

    public ThongTin() {
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public int getId_thuoc() {
        return id_thuoc;
    }

    public void setId_thuoc(int id_thuoc) {
        this.id_thuoc = id_thuoc;
    }

    public LieuDung getLd() {
        return ld;
    }

    public void setLd(LieuDung ld) {
        this.ld = ld;
    }

    public CachDung getCd() {
        return cd;
    }

    public void setCd(CachDung cd) {
        this.cd = cd;
    }
    
};
