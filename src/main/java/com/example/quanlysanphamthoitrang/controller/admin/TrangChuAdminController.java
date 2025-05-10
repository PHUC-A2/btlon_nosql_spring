package com.example.quanlysanphamthoitrang.controller.admin;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.quanlysanphamthoitrang.domain.model.NguoiDung;
import com.example.quanlysanphamthoitrang.domain.model.SanPham;
import com.example.quanlysanphamthoitrang.service.NguoiDungService;
import com.example.quanlysanphamthoitrang.service.SanPhamService;

@Controller
public class TrangChuAdminController {

    private final NguoiDungService nguoiDungService;
    private final SanPhamService sanPhamService;

    public TrangChuAdminController(NguoiDungService nguoiDungService, SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
        this.nguoiDungService = nguoiDungService;
    }

    @GetMapping("/admin")
    public String trangChu(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        NguoiDung nguoiDung = this.nguoiDungService.getNguoiDungByEmail(username);

        List<NguoiDung> nguoiDungs = this.nguoiDungService.layTatCaNguoiDung();
        List<SanPham> sanPhams = this.sanPhamService.layTatCaSanPham();

        long tongSoSanPham = this.sanPhamService.layTongSoSanPham();
        long tongSoNguoiDung = this.nguoiDungService.layTongSoNguoiDung();

        
        model.addAttribute("tongSoNguoiDung", tongSoNguoiDung);
        model.addAttribute("tongSoSanPham", tongSoSanPham);
        model.addAttribute("sanPhams", sanPhams);
        model.addAttribute("nguoiDungs", nguoiDungs);
        model.addAttribute("nguoiDung", nguoiDung);
        return "admin/trang_chu_admin";
    }
}
