package excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Description: 操作excel工具类
 * @author: daimin
 * @date: Create in 17:30 2019/8/21
 */
public class ExcelUtils {
    /**
     * excel表名称
     */
    private String title;
    /**
     * 列名
     */
    private String[] rowName;
    /**
     * 导出内容
     */
    private List<Object[]> dataList;

    public ExcelUtils(String title, String[] rowName, List<Object[]> dataList) {
        this.title = title;
        this.rowName = rowName;
        this.dataList = dataList;
    }

    /**
     * 导出Excel
     */
    public void exportExcel(HSSFWorkbook workbook, HSSFSheet sheet, int x, String excelName) {

        //标题行
        HSSFRow rowm = sheet.createRow(0);
        HSSFCell cellTitle = rowm.createCell(0);
        rowm.setHeight((short) (25 * 35));

        //列头和单元格样式对象
        HSSFCellStyle columnStyle = this.getColumnStyle(workbook);
        HSSFCellStyle cellStyle = this.getCellStyle(workbook);

        //设置列头样式和列头内容
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (rowName.length - 1)));
        cellTitle.setCellStyle(columnStyle);
        cellTitle.setCellValue(title);

        //第二行创建内容
        int columnNum = rowName.length;
        HSSFRow rowContent = sheet.createRow(1);
        rowContent.setHeight((short) (25 * 25));

        //列头设置到sheet单元格中
        for (int i = 0; i < columnNum; i++) {
            HSSFCell cellContent = rowContent.createCell(i);
            cellContent.setCellType(CellType.STRING);
            HSSFRichTextString text = new HSSFRichTextString(rowName[i]);
            cellContent.setCellValue(text);
            cellContent.setCellStyle(columnStyle);
        }

        //数据填充到单元格中
        for (int j = 0; j < dataList.size(); j++) {
            Object[] obj = dataList.get(j);
            HSSFRow contentRow = sheet.createRow(j + 2);
            contentRow.setHeight((short) (25 * 25));
            //填充
            for (int k = 0; k < obj.length; k++) {
                HSSFCell cell;
                cell = contentRow.createCell(k, CellType.STRING);
                cell.setCellValue(obj[k].toString());
                cell.setCellStyle(cellStyle);
            }
        }

        //列宽随着导出的列长自动适应
        for (int colNum = 0; colNum < columnNum; colNum++) {
            int columnWidth = sheet.getColumnWidth(colNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                HSSFRow currentRow;
                //当前行未被使用过
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(colNum) != null) {
                    HSSFCell currentCell = currentRow.getCell(colNum);
                    if (currentCell.getCellType() == currentCell.getCellType()) {
                        int length = currentCell.getStringCellValue().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            if (colNum == 0) {
                sheet.setColumnWidth(colNum, (columnWidth - 2) * 128);
            } else {
                sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
            }
        }

        //导出
        if (workbook != null && x == 1) {
            try {
                //存储Excel的路径
                FileSystemView view = FileSystemView.getFileSystemView();
                File directory = view.getHomeDirectory();
                String path = directory + "\\" + excelName + ".xls";
                FileOutputStream out = new FileOutputStream(path);
                workbook.write(out);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 列头样式
     */
    public HSSFCellStyle getColumnStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short) 11);
        //字体加粗
        font.setBold(true);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle columnStyle = workbook.createCellStyle();
        //设置底边框;
        columnStyle.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;
        columnStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        //设置左边框;
        columnStyle.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色;
        columnStyle.setLeftBorderColor(IndexedColors.BLACK.index);
        //设置右边框;
        columnStyle.setBorderRight(BorderStyle.THIN);
        //设置右边框颜色;
        columnStyle.setRightBorderColor(IndexedColors.BLACK.index);
        //设置顶边框;
        columnStyle.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色;
        columnStyle.setTopBorderColor(IndexedColors.BLACK.index);
        //在样式用应用设置的字体;
        columnStyle.setFont(font);
        //设置自动换行;
        columnStyle.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        columnStyle.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        columnStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //设置单元格背景颜色
        columnStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
        columnStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        return columnStyle;
    }

    /**
     * 单元格样式
     */
    public HSSFCellStyle getCellStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //设置底边框;
        cellStyle.setBorderBottom(BorderStyle.THIN);
        //设置底边框颜色;
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.index);
        //设置左边框;
        cellStyle.setBorderLeft(BorderStyle.THIN);
        //设置左边框颜色;
        cellStyle.setLeftBorderColor(IndexedColors.BLACK.index);
        //设置右边框;
        cellStyle.setBorderRight(BorderStyle.THIN);
        //设置右边框颜色;
        cellStyle.setRightBorderColor(IndexedColors.BLACK.index);
        //设置顶边框;
        cellStyle.setBorderTop(BorderStyle.THIN);
        //设置顶边框颜色;
        cellStyle.setTopBorderColor(IndexedColors.BLACK.index);
        //在样式用应用设置的字体;
        cellStyle.setFont(font);
        //设置自动换行;
        cellStyle.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //设置垂直对齐的样式为居中对齐;
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }
}
