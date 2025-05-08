package com.example.quanlysanphamthoitrang.domain.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "sanpham")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SanPham {
    @Id
    private String id;
    private String ten;
    private String loai;
    private String thuongHieu;
    private double gia;
    private String mauSac;
    private String kichThuoc;
    private String tinhTrang;
    @CreatedDate
    private LocalDateTime ngayTao;
    @LastModifiedDate
    private LocalDateTime ngayCapNhat;
    private String moTaHtml; // Mô tả chi tiết có chèn ảnh/video (Jodit Editor)
}
