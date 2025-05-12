package com.drinktea.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart implements Serializable {
    Long id;
    String name;
    String image;
    Long userId;
    Long dishId;
    Long setmealId;
    String dishFlavor;
    Integer number;
    BigDecimal amount;
    LocalDateTime createTime;
}
