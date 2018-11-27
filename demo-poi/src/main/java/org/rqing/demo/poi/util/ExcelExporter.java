package org.rqing.demo.poi.util;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * Excel导出
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/11/18 16:12
 *
 * @since JDK 1.8
 */
public abstract class ExcelExporter<T> implements Closeable {
    
    private String title;
    private String[] header ;
    
    protected String path ;
    protected FileOutputStream fileOutputStream ;
    
    protected SXSSFWorkbook workbook ;
    protected Sheet sheet ;
    
    protected int index = 0 ;
    
    /**
     * @throws FileNotFoundException 
     * 
     */
    public ExcelExporter(String path, String title, String[] header) throws FileNotFoundException {
        
        this.title = title;
        this.header = header;
        fileOutputStream = new FileOutputStream(path);
        workbook = new SXSSFWorkbook(SXSSFWorkbook.DEFAULT_WINDOW_SIZE);
        sheet = workbook.createSheet();
        this.title();
    }
    
    /**
     * Excel表头
     * @author piaoruiqing
     * @date: 2018/11/18 14:07
     *
     */
    private void title() {
        
        if (StringUtils.isNotBlank(title)) {
            Row titleRow = sheet.createRow(index++);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, header.length - 1));
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue(title);
            Font font = workbook.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            CellStyle style = workbook.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setFont(font);
            titleCell.setCellStyle(style);
        }
        
        Row row = sheet.createRow(index++);
        for (int i = 0 ; i < header.length ; i++) {
            row.createCell(i).setCellValue(header[i]);
        }
    }
    
    /**
     * 写入数据
     * @author piaoruiqing
     * @date: 2018/11/18 14:08
     * 
     * @param list
     */
    public abstract void write(List<T> list);
    
    /* (non-Javadoc)
     * @see java.io.Closeable#close()
     */
    @Override
    public void close() throws IOException {
        try {
            workbook.write(fileOutputStream);
        } finally {
            IOUtils.closeQuietly(fileOutputStream);
            workbook.dispose();
        }
    }
    
}
