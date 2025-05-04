package com.drinktea.vo;

import com.drinktea.entity.DishFlavor;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "菜品分页查询的响应数据格式，被PageResult包裹")
public class DishVO implements Serializable {
    private Long id;
    // 菜品名称
    private String name;
    // 菜品分类id
    private Long categoryId;
    // 菜品价格
    private BigDecimal price;
    // 图片
    private String image;
    // 描述信息
    private String description;
    // 0 停售 1 起售
    private Integer status;
    // 更新时间
    private LocalDateTime updateTime;
    // 分类名称
    private String categoryName;
    // 菜品关联的口味
    private List<DishFlavor> flavors = new ArrayList<>();
}
