package com.example.quanlysanphamthoitrang.controller.user;

import com.example.quanlysanphamthoitrang.domain.model.NguoiDung;
import com.example.quanlysanphamthoitrang.domain.model.SanPham;
import com.example.quanlysanphamthoitrang.service.NguoiDungService;
import com.example.quanlysanphamthoitrang.service.SanPhamService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/sanpham")
public class SanPhamController {
    private final SanPhamService sanPhamService;
    private final NguoiDungService nguoiDungService;

    public SanPhamController(SanPhamService sanPhamService, NguoiDungService nguoiDungService) {
        this.sanPhamService = sanPhamService;
        this.nguoiDungService = nguoiDungService;
    }

    // tất cả sản phẩm
    @GetMapping("/tatca")
    public String layTatCaSanPham(Model model) {
        List<SanPham> sanPhams = this.sanPhamService.layTatCaSanPham();
        model.addAttribute("sanPhams", sanPhams);
        return "user/sanpham/danhsach_sanpham";
    }

    // Lấy chi tiết sản phẩm
    @GetMapping("/chitiet/{id}")
    public String chiTietSanPham(Model model, @PathVariable String id,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails != null) {
            String username = userDetails.getUsername();
            NguoiDung nguoiDung = this.nguoiDungService.getNguoiDungByEmail(username);
            model.addAttribute("nguoiDung", nguoiDung);
        }

        SanPham sanPham = this.sanPhamService.laySanPhamById(id);
        // NguoiDung nguoiDung = this.nguoiDungService.layNguoiDungByID(id);
        model.addAttribute("sanPham", sanPham);
        // model.addAttribute("nguoiDung", nguoiDung);
        return "user/sanpham/chitiet_sanpham";
    }

    // tìm kiếm sản phẩm
    @GetMapping("/timkiem")
    public String timKiemSanPham(Model model, @RequestParam("keyword") String keyword) {
        List<SanPham> sanPhams = this.sanPhamService.timKiemSanPham(keyword);
        model.addAttribute("sanPhams", sanPhams);
        return "user/sanpham/danhsach_sanpham";
    }
}
