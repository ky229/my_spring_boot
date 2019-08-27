package com.wky.excel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author wukaiyun
 * @version 1.0
 * @description
 * @date 2019/8/6 0006 11:26
 */
@Data
public class FaqExcelModel extends BaseRowModel {
    @ExcelProperty(value = { "id" }, index = 0)
    private String id;

}
