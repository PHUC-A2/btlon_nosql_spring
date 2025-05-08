package com.example.quanlysanphamthoitrang.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrangChuController {

    @GetMapping("/admin")
    public String trangChu() {
        return "admin/trang_chu_admin";
    }
}
