package com.example.quanlysanphamthoitrang.controller.user;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.quanlysanphamthoitrang.domain.model.SanPham;
import com.example.quanlysanphamthoitrang.service.SanPhamService;

@Controller
public class TrangChuUserController {
    private final SanPhamService sanPhamService;

    public TrangChuUserController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    @GetMapping("/user")
    public String trangChu(Model model) {
        List<SanPham> sanPhams = this.sanPhamService.layTatCaSanPham();
        model.addAttribute("sanPhams", sanPhams);
        return "user/trang_chu_user";
    }

}
