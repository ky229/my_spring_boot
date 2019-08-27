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
public class DemoExcelModelDto extends BaseRowModel {
    @ExcelProperty(value = { "表头1" }, index = 0)
    private String p1;

    @ExcelProperty(value = { "表头2", "表头21" }, index = 1)
    private String p2;

    @ExcelProperty(value = { "表头2", "表头22" }, index = 2)
    private String p3;

    @ExcelProperty(value = { "表头4"}, index = 3)
    private String p4;

}
