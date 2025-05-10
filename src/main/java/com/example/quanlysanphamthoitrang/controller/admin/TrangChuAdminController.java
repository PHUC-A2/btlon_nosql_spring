package com.example.quanlysanphamthoitrang.controller.admin;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.quanlysanphamthoitrang.domain.model.NguoiDung;
import com.example.quanlysanphamthoitrang.service.NguoiDungService;

@Controller
public class TrangChuAdminController {

    private final NguoiDungService nguoiDungService;

    public TrangChuAdminController(NguoiDungService nguoiDungService) {
        this.nguoiDungService = nguoiDungService;
    }

    @GetMapping("/admin")
    public String trangChu(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        NguoiDung nguoiDung = this.nguoiDungService.getNguoiDungByEmail(username);
        model.addAttribute("nguoiDung", nguoiDung);
        return "admin/trang_chu_admin";
    }
}
