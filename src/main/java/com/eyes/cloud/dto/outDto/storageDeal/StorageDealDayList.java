package com.eyes.cloud.dto.outDto.storageDeal;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author dyk
 * @version V1.0.0
 * @description
 * @date 2020/9/25
 * @since 1.0
 */
@Data
public class StorageDealDayList {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate day;
}
