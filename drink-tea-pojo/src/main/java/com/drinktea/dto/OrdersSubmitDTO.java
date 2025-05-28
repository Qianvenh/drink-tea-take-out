package com.drinktea.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrdersSubmitDTO implements Serializable {
    private Long addressBookId;

    private int payMethod;

    // 备注
    private String remark;

    // 预计送达时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryTime;

    // 配送状态
    private Integer deliveryStatus;

    // 餐具数目
    private Integer tablewareNumber;

    // 餐具状态，1为无需餐具；0为需要餐具，可选数量
    private Integer tablewareStatus;

    // 打包费
    private Integer packAmount;

    // 总金额
    private BigDecimal amount;
}
