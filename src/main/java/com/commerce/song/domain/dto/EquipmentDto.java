package com.commerce.song.domain.dto;

import com.commerce.song.domain.entity.Brand;
import com.commerce.song.domain.entity.Community;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
public class EquipmentDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class equipmentRes {
        private Long equipmentId;

        private Community community;

        private String equipmentName;

        private Brand brand;
    }
}
