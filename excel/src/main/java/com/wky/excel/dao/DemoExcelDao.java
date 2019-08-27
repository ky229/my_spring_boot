package com.wky.excel.dao;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.wky.excel.dto.DemoExcelModelDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeMath.log;

/**
 * @Description:
 * @Author: 吴开云
 * @Date: 2019/8/27 0027
 * @Version： 1.0
 */
@Slf4j
@Component
public class DemoExcelDao {
    /**
     * 获取所有Excel中所有的list
     * @return
     */
    public List<DemoExcelModelDto> getAllDtoList() {
        List<DemoExcelModelDto> infoDtoList = new ArrayList<>();

        //访问的classes-路径下的，Excel文件名（编译前的resource目录下文件）
        String fileName = "withHead.xlsx";

        //调用easyexcel 访问数据
        InputStream in = null;
        try {
            //获取输入流
            in = this.getClass().getClassLoader().getResourceAsStream(fileName);

            //初始化监听器
            AnalysisEventListener<DemoExcelModelDto> listener = new AnalysisEventListener<DemoExcelModelDto>() {
                //访问，每一行数据
                @Override
                public void invoke(DemoExcelModelDto object, AnalysisContext context) {
                    // System.err.println("Row:" + context.getCurrentRowNum() + "  Data:" + object);
                    infoDtoList.add(object);
                }
                //完成访问所有数据
                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {
                    log.info(fileName + "数据读取完毕..." + "共读取：" + infoDtoList.size() + "条数据");
                }
            };

            if(fileName.indexOf(".xlsx") > 0){
                //读取xlsx后缀的Excel内容
                ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLSX, null, listener);
                // 第一个参数表示sheet页（第几页），第二个参数为表头行数，按照实际设置
                excelReader.read(new Sheet(1, 2, DemoExcelModelDto.class));
            }else{
                //读取xls后缀的Excel内容
                ExcelReader excelReader = new ExcelReader(in, ExcelTypeEnum.XLS, null, listener);
                // 第一个参数表示sheet页（第几页），第二个参数为表头行数，按照实际设置
                excelReader.read(new Sheet(1, 2, DemoExcelModelDto.class));
            }
        } finally {
            try {
                //关闭输入流
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                DemoExcelDao.log.error("找不到 " + fileName);
            }
        }

        return infoDtoList;
    }

    /**
     * 写入DTOlist
     * @param dtoList
     * @return
     */
    public Integer insertDtoList(List<DemoExcelModelDto> dtoList){
        OutputStream out = null;
        String fileName = "withHead.xlsx";
        try {
            //获取文件输出流
            URL url = this.getClass().getClassLoader().getResource(fileName);
            File file = new File(url.getFile());
            out = new FileOutputStream(file, false);
            //设置文件格式
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX,true);

            // 第一个参数表示sheet页（第几页），第二个参数为表头行数，按照实际设置
            Sheet sheet1 = new Sheet(1, 0, DemoExcelModelDto.class);
            sheet1.setSheetName("sheet1");

            //写入Excel
            writer.write(dtoList, sheet1);
            writer.finish();

        }catch (IOException e){
            e.printStackTrace();
            log("找不到文件{}", fileName);
        }finally {
            try {
                //关闭输入流
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return dtoList.size();
    }
}
