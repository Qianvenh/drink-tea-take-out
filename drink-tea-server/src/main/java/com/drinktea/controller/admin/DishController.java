package com.drinktea.controller.admin;

import com.drinktea.dto.DishDTO;
import com.drinktea.dto.DishPageQueryDTO;
import com.drinktea.entity.Dish;
import com.drinktea.result.PageResult;
import com.drinktea.result.Result;
import com.drinktea.service.DishService;
import com.drinktea.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "饮品相关接口")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @PostMapping
    @ApiOperation("新增饮品")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增饮品: {}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        String pattern = "dish_" + dishDTO.getCategoryId();
        cleanCache(pattern);
        return Result.success();
    }

    /**
     * 饮品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("饮品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("饮品分页查询: {}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 批量删除饮品
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("批量删除饮品")
    public Result delete(@RequestParam List<Long> ids) {
        log.info("批量删除饮品: {}", ids);
        dishService.deleteBatch(ids);
        cleanCache("dish_*");
        return Result.success();
    }

    /**
     * 根据id查询饮品
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询饮品")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根据id查询饮品, {}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    /**
     * 修改菜品
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品: {}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        cleanCache("dish_*");
        return Result.success();
    }

    /**
     * 饮品起售停售
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("饮品起售停售")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        dishService.startOrStop(status, id);
        cleanCache("dish_*");
        return Result.success();
    }

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId) {
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

    private void cleanCache(String pattern) {
        Set<Object> keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}
