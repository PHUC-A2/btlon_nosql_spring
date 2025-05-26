package com.example.quanlysanphamthoitrang.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.quanlysanphamthoitrang.domain.model.SanPham;
import com.example.quanlysanphamthoitrang.service.SanPhamService;

@Controller
public class TrangChuController {
    private final SanPhamService sanPhamService;

    public TrangChuController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    @GetMapping("/")
    public String trangChu(Model model) {
        List<SanPham> sanPhams = this.sanPhamService.layTatCaSanPham();
        model.addAttribute("sanPhams", sanPhams);
        return "trang_chu";
    }
}
