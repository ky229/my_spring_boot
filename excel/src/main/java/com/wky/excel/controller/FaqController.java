package com.wky.excel.controller;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.wky.excel.dto.FaqExcelModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author wukaiyun
 * @version 1.0
 * @description
 * @date 2019/8/6 0006 11:14
 */
@Controller
@RequestMapping("/excel")
public class FaqController {

    @RequestMapping("/infaq")
    @ResponseBody
    public String writeFaqId() throws IOException {
        try (OutputStream out = new FileOutputStream("faq.xlsx");) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX,true);
            Sheet sheet1 = new Sheet(1, 0, FaqExcelModel.class);
            sheet1.setSheetName("faqId");
            List<FaqExcelModel> data = new ArrayList<>();
            for (int i = 0; i < 816243; i++) {
                FaqExcelModel item = new FaqExcelModel();
                item.setId(UUID.randomUUID().toString());
                data.add(item);
            }
            writer.write(data, sheet1);
            writer.finish();
        }
        return "seccess";
    }

}
