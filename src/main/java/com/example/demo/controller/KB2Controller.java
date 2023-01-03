package com.example.demo.controller;

import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BenhInterface;
import com.example.demo.dao.CachDieuTrinterface;
import com.example.demo.dao.CauhoiNguyennhanInterface;
import com.example.demo.dao.NguyenNhanInterface;
import com.example.demo.dao.ThuocInterface;
import com.example.demo.model.Benh;
import com.example.demo.model.CachDieuTri;
import com.example.demo.model.CauHoi_NguyenNhan;
import com.example.demo.model.NguyenNhan;

@CrossOrigin
@RestController
public class KB2Controller {

    @Autowired
    BenhInterface benhInterface;

    @Autowired
    NguyenNhanInterface nguyenNhanInterface;

    @Autowired
    ThuocInterface thuocInterface;

    @Autowired
    CauhoiNguyennhanInterface cauhoiNguyennhanInterface;

    @Autowired
    CachDieuTrinterface cachDieuTrinterface;

    @GetMapping("/get-benh")
    public List<Benh> getAllBenh() {
        try {
            List<Benh> list = (List<Benh>) benhInterface.findAll();
            return list;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @GetMapping("/get-cauhoi")
    public List<CauHoi_NguyenNhan> getCauhoiByBenh(@RequestParam int id_benh) {
        try {
            List<CauHoi_NguyenNhan> list = (List<CauHoi_NguyenNhan>) cauhoiNguyennhanInterface
                            .getCHByBenh(id_benh);

            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @PostMapping("/get-cauhoi-po")
    public List<CauHoi_NguyenNhan> getCauhoiByBenhPo(@RequestBody Benh benh) {
        try {
            List<CauHoi_NguyenNhan> list = (List<CauHoi_NguyenNhan>) cauhoiNguyennhanInterface
                            .getCHByBenh(benh.getId());

            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @PostMapping("/get-nguyen-nhan")
    public List<NguyenNhan> getNguyenNhan(@RequestBody List<Integer> listNN) {
        try {
            List<NguyenNhan> listNguyenNhan = new ArrayList<>();
            for (int i : listNN) {
                listNguyenNhan.add(nguyenNhanInterface.findById(i).get());
            }
            return listNguyenNhan;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @PostMapping("/cach-dieu-tri")
    public List<CachDieuTri> getCachDieuTri(@RequestBody List<Integer> listIDNguyenNhan) {
        try {
            List<CachDieuTri> listCachDieuTri = new ArrayList<>();
            for (int i : listIDNguyenNhan) {
                listCachDieuTri.add(cachDieuTrinterface.getCachDieuTriByNN(i));
            }
            return listCachDieuTri;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
};
