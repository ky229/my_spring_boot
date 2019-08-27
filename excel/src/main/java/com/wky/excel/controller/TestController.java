package com.wky.excel.controller;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.wky.excel.dto.DemoExcelModel;
import com.wky.excel.utils.ExcelVersionUtil;
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

    @RequestMapping("/test")
    @ResponseBody
    public String writeWithHead() throws IOException {
        try (OutputStream out = new FileOutputStream("withHead.xlsx");) {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX,true);
            Sheet sheet1 = new Sheet(1, 0, DemoExcelModel.class);
            sheet1.setSheetName("sheet1");
            List<DemoExcelModel> data = new ArrayList<>();
            for (int i = 0; i < 2000; i++) {
                DemoExcelModel item = new DemoExcelModel();
                item.setP1( "p1" + i);
                item.setP2( "p2" + i);
                item.setP3("p3" + i);
                item.setP4( "p4" + i);
                data.add(item);
            }
            writer.write(data, sheet1);
            writer.finish();
        }
        return "seccess";
    }

    @RequestMapping("/read")
    @ResponseBody
    public String read() throws Exception {
        try (InputStream in = new FileInputStream("withHead.xlsx");) {
            AnalysisEventListener<DemoExcelModel> listener = new AnalysisEventListener<DemoExcelModel>() {

                @Override
                public void invoke(DemoExcelModel object, AnalysisContext context) {

                    System.err.println("Row:" + context.getCurrentRowNum() + " Data:" + object);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    System.err.println("doAfterAllAnalysed...");
                }
            };
            ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLSX, null, listener);
//            ExcelReader excelReader = ExcelVersionUtil.getExcelReader(in, null, listener);
            // 第二个参数为表头行数，按照实际设置
            excelReader.read(new Sheet(1, 1, DemoExcelModel.class));
        }
        return "seccess read";
    }
}
