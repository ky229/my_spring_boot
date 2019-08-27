package com.wky.excel.controller;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.wky.excel.dao.DemoExcelDao;
import com.wky.excel.dto.DemoExcelModelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wukaiyun
 * @version 1.0
 * @description
 * @date 2019/8/6 0006 11:14
 */
@Controller
@RequestMapping("/excel")
public class TestController {

    @Autowired
    DemoExcelDao excelDao;

    @RequestMapping("/write")
    @ResponseBody
    public String writeWithHead(){
        List<DemoExcelModelDto> list = new ArrayList<>();
        DemoExcelModelDto dto = new DemoExcelModelDto();
        dto.setP1("p1");
        dto.setP2("p2");
        dto.setP3("p3");
        dto.setP4("p4");
        list.add(dto);
        excelDao.insertDtoList(list);
        return "seccess";
    }

    @RequestMapping("/read")
    @ResponseBody
    public String read() {
        excelDao.getAllDtoList();
        return "seccess read";
    }
}
