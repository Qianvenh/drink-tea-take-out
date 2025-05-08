package com.drinktea.controller.admin;

import com.drinktea.result.Result;
import com.drinktea.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关接口")
@Slf4j
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 设置营业状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("设置店铺的营业状态")
    public Result<String> setStatus(@PathVariable Integer status) {
        log.info("设置店铺的营业状态为: {}", status == 1 ? "营业中" : "打烊中");
        shopService.setStatus(status);
        return Result.success();
    }

    /**
     * 获取营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取店铺的营业状态")
    public Result<Integer> getStatus() {
        Integer status = shopService.getStatus();
        log.info("获取到的店铺状态为: {}", status == 1 ? "营业中" : "打烊中");
        return Result.success(status);
    }
}
