package com.example.quanlysanphamthoitrang.domain.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "sanpham")
public class SanPham {
    @Id
    private String id;
    //    private String nguoiDungId; // dùng đối với NguoiDung
    private String ten;
    private String loai;
    private String thuongHieu;
    private double gia;
    private String mauSac;
    private String kichThuoc;
    private String tinhTrang;
    private LocalDateTime ngayTao;
    private LocalDateTime ngayCapNhat;
    private String imageUrl;
    private String videoUrl;

}
