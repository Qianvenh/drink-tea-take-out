package com.drinktea.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishFlavor implements Serializable {
    private static final long serialVersionUID = 1L;
    // 菜品id
    private Long dishId;
    // 口味名称
    private String name;
    // 口味数据list
    private String value;
}
