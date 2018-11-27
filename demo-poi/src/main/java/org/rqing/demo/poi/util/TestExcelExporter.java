package org.rqing.demo.poi.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Row;
import org.rqing.demo.poi.entity.DemoEntity;

/**
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/11/18 13:01
 *
 * @since JDK 1.8
 */
public class TestExcelExporter extends ExcelExporter<DemoEntity> {
    
    private static String[] HEADER = new String[] {"名称", "年龄", "编号", "创建时间", "生日"};

    /**
     * @param path
     * @throws FileNotFoundException
     */
    public TestExcelExporter(String path) throws FileNotFoundException {
        super(path, "", HEADER);
    }

    /* (non-Javadoc)
     * @see org.rqing.demo.poi.util.ExcelExporter#write(java.util.List)
     */
    @Override
    public void write(List<DemoEntity> list) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        for (DemoEntity item : list) {
            Row row= sheet.createRow(index++);
            row.createCell(0).setCellValue(item.getName());
            row.createCell(1).setCellValue(item.getAge());
            row.createCell(2).setCellValue(item.getNo());
            row.createCell(3).setCellValue(simpleDateFormat.format(new Date(item.getGmtCreate())));
            row.createCell(4).setCellValue(simpleDateFormat.format(item.getBirthday()));
        }
    }
    
    public static void main(String[] args) throws IOException {
        String path = "/Users/piaoruiqing/eclipse_tmp/" + UUID.randomUUID().toString() + ".xlsx";
        TestExcelExporter exporter = new TestExcelExporter(path);
        List<DemoEntity> list = new ArrayList<>(1000);
        for (int j = 0 ; j < 100 ; j++) {
            for (int i = 0 ; i < 1000 ; i++) {
                list.add(DemoEntity.builder().name("name_" + i).age(i).no(i).gmtCreate(System.currentTimeMillis()).birthday(new Date()).build());
            }
            exporter.write(list);
            list.clear();
        }
        exporter.close();
    }

}
