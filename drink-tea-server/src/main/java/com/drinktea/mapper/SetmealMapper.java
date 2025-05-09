package com.drinktea.mapper;

import com.drinktea.annotation.AutoFill;
import com.drinktea.dto.SetmealPageQueryDTO;
import com.drinktea.entity.Setmeal;
import com.drinktea.enumeration.OperationType;
import com.drinktea.vo.DishItemVO;
import com.drinktea.vo.SetmealVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SetmealMapper {
    /**
     * 根据分类id查询套餐的数量
     * @param categoryId
     * @return
     */
    Integer countByCategoryId(@Param("categoryId") Long categoryId);

    /**
     * 向套餐表插入数据
     * @param setmeal
     */
    @AutoFill(OperationType.INSERT)
    void insert(Setmeal setmeal);

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    Setmeal getById(Long id);

    /**
     * 根据id删除数据
     * @param id
     */
    void deleteById(Long id);

    /**
     * 修改套餐表
     * @param setmeal
     */
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemBySetmealId(Long id);

    /**
     * 根据条件统计套餐数量
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
