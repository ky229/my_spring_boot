package com.wky.excel.controller;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wky.excel.ExcelApplicationTests;
import com.wky.excel.utils.ExcelVersionUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author wukaiyun
 * @version 1.0
 * @description
 * @date 2019/8/6 0006 11:38
 */
public class TestControllerTest extends ExcelApplicationTests{

    @Autowired
    TestController controller;

    @Autowired
    FaqController faqController;

    @Test
    public void writeWithHead() throws IOException{
        controller.writeWithHead();
    }

    @Test
    public void testFile(){
        String fileName = "withHead.xlsx";
        URL url = this.getClass().getClassLoader().getResource(fileName);
        System.out.println(url.getPath() );
        System.out.println(url.getFile());
        System.out.println(url.getHost());
        System.out.println(url);
    }


    @Test
    public void writeFaqId() throws IOException{
        faqController.writeFaqId();
    }


    @Test
    public void threa(){
        Runnable tast = new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2");
            }
        };
        for (int i = 0; i < 3; i++) {
            new Thread(tast).start();
        }
    }
    @Test
    public void threa1(){

        for (int i = 0; i < 3; i++) {
            new Thread(() -> System.out.println("1111")).start();
        }
    }

    @Test
    public void read() throws Exception{
        controller.read();
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("1");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2");
        }
    }

}