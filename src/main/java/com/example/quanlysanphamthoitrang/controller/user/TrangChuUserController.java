package com.example.quanlysanphamthoitrang.controller.user;

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
public class TrangChuUserController {
    private final SanPhamService sanPhamService;
    private final NguoiDungService nguoiDungService;

    public TrangChuUserController(SanPhamService sanPhamService, NguoiDungService nguoiDungService) {
        this.sanPhamService = sanPhamService;
        this.nguoiDungService = nguoiDungService;
    }

    @GetMapping("/user")
    public String trangChu(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();

        NguoiDung nguoiDung = this.nguoiDungService.getNguoiDungByEmail(username);

        List<SanPham> sanPhams = this.sanPhamService.layTatCaSanPham();
        model.addAttribute("sanPhams", sanPhams);
        model.addAttribute("nguoiDung", nguoiDung);

        return "user/trang_chu_user";
    }

}
