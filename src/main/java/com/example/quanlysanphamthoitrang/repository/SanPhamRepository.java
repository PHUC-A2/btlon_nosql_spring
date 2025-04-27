package com.example.quanlysanphamthoitrang.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.quanlysanphamthoitrang.domain.model.SanPham;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SanPhamRepository extends MongoRepository<SanPham, String> {
    @Query("{'$or': [ " +
            "{'ten': {$regex: ?0, $options: 'i'}}, " +
            "{'loai': {$regex: ?0, $options: 'i'}}, " +
            "{'thuongHieu': {$regex: ?0, $options: 'i'}}, " +
            "{'gia': {$regex: ?0, $options: 'i'}}, " +
            "{'mauSac': {$regex: ?0, $options: 'i'}}, " +
            "{'kichThuoc': {$regex: ?0, $options: 'i'}}, " +
            "{'tinhTrang': {$regex: ?0, $options: 'i'}} " +
            "]}")
    List<SanPham> timKiemSanPham(String keyword);
}
