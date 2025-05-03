package com.drinktea.service;

import com.drinktea.dto.DishDTO;

public interface DishService {
    /**
     * 新增饮品
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);
}
