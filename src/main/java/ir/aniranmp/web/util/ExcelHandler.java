package ir.aniranmp.web.util;


import ir.aniranmp.web.model.local.ImportResult;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Number;
import jxl.write.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ExcelHandler {
    private final ExcelHandlerListener listener;


    public ExcelHandler(ExcelHandlerListener listener) {
        this.listener = listener;
    }

    public <DataType> byte[] excelExporter(List<DataType> datas, int type, boolean useHeader) {
        File file = new File(TimeUtil.getNowTime() + ".xlsx");
        try {
            WritableWorkbook workbook = Workbook.createWorkbook(file);

            WritableSheet sheet = workbook.createSheet(listener.getSheetName(type), 0);

            WritableCellFormat headerFormat = new WritableCellFormat();
            WritableFont fontHeader = new WritableFont(WritableFont.createFont("B Koodak"), 14, WritableFont.BOLD);
            fontHeader.setColour(Colour.WHITE);
            headerFormat.setFont(fontHeader);
            headerFormat.setBackground(Colour.BLUE);
            headerFormat.setAlignment(Alignment.CENTRE);
//            headerFormat.setWrap(true);

            Integer[] columns = new Integer[listener.getColumnLength(type)];
            listener.getColumnSize(columns, type);

            for (int i = 0; i < columns.length; i++) {
                sheet.setColumnView(i, columns[i]);
            }

            if (useHeader) {
                String[] titles = new String[columns.length];
                listener.onHeaderTitles(titles, type);
                for (int i = 0; i < columns.length; i++) {
                    Label headerLabel = new Label(i, 0, titles[i], headerFormat);
                    sheet.setRowView(0, 400);
                    sheet.addCell(headerLabel);
                }
            }

            WritableCellFormat cellFormat = new WritableCellFormat();
            WritableFont fontCell = new WritableFont(WritableFont.createFont("B Koodak"), 13, WritableFont.NO_BOLD);
            cellFormat.setFont(fontCell);
//            cellFormat.setWrap(true);

            for (int r = 0; r < datas.size(); r++) {
                Object[] rowData = new Object[columns.length];
                listener.onRowData(rowData, datas.get(r), r, type);
                for (int i = 0; i < rowData.length; i++) {
                    if (useHeader) {
                        sheet.setRowView(r + 1, 350);
                    } else {
                        sheet.setRowView(r, 350);
                    }
                    if (rowData[i] == null) {
                        Label cell = new Label(i, useHeader ? r + 1 : r, "---", cellFormat);
                        sheet.addCell(cell);
                    } else if (rowData[i] instanceof Long) {
                        Number cell = new Number(i, useHeader ? r + 1 : r, (Long) rowData[i], cellFormat);
                        sheet.addCell(cell);
                    } else if (rowData[i] instanceof Integer) {
                        Number cell = new Number(i, useHeader ? r + 1 : r, (Integer) rowData[i], cellFormat);
                        sheet.addCell(cell);
                    } else if (rowData[i] instanceof Short) {
                        Number cell = new Number(i, useHeader ? r + 1 : r, (Short) rowData[i], cellFormat);
                        sheet.addCell(cell);
                    } else if (rowData[i] instanceof Float) {
                        Number cell = new Number(i, useHeader ? r + 1 : r, (Float) rowData[i], cellFormat);
                        sheet.addCell(cell);
                    } else if (rowData[i] instanceof Double) {
                        Number cell = new Number(i, useHeader ? r + 1 : r, (Double) rowData[i], cellFormat);
                        sheet.addCell(cell);
                    } else if (rowData[i] instanceof String) {
                        Label cell = new Label(i, useHeader ? r + 1 : r, (String) rowData[i], cellFormat);
                        sheet.addCell(cell);
                    } else {
                        Label cell = new Label(i, useHeader ? r + 1 : r, rowData[i].toString(), cellFormat);
                        sheet.addCell(cell);
                    }
                }
            }

            workbook.write();
            workbook.close();
            byte[] bytes = Files.readAllBytes(file.toPath());
            file.delete();
            return bytes;
        } catch (Exception e) {
            BeautyLog.println(e);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getLocalizedMessage());
        } finally {
            file.delete();
        }
    }

    public byte[] excelImporter(InputStream file, int type, boolean hasHeader, boolean batchMode) {
        File logFile = null;

        try {
            Workbook workbook = Workbook.getWorkbook(file);
            Sheet sheet = workbook.getSheet(0);
            String[][] sheetData = new String[sheet.getRows()][];
            for (int i = 0; i < sheet.getRows(); i++) {

//                String[] inputRowData = new String[sheet.getColumns()];
//                for (int j = 0; j < sheet.getColumns(); j++) {
//                    inputRowData[j] = sheet.getCell(j, i).getContents();
//                }
                String[] inputRowData = new String[listener.getColumnLength(type)];
                for (int j = 0; j < listener.getColumnLength(type); j++) {
                    inputRowData[j] = sheet.getCell(j, i).getContents();
                }
                sheetData[i] = inputRowData;
            }
            String sheetName = sheet.getName();
            workbook.close();

            logFile = new File(TimeUtil.getNowTime() + ".xlsx");
            WritableWorkbook writeWorkbook = Workbook.createWorkbook(logFile);

            WritableSheet logSheet = writeWorkbook.createSheet(sheetName, 0);

            WritableCellFormat headerFormat = new WritableCellFormat();
            WritableFont fontHeader = new WritableFont(WritableFont.createFont("B Koodak"), 14, WritableFont.BOLD);
            fontHeader.setColour(Colour.WHITE);
            headerFormat.setFont(fontHeader);
            headerFormat.setBackground(Colour.BLUE);
            headerFormat.setAlignment(Alignment.CENTRE);

            WritableFont fontDescription = new WritableFont(WritableFont.createFont("B Koodak"), 13, WritableFont.NO_BOLD);
            fontDescription.setColour(Colour.WHITE);

            WritableCellFormat descriptionFormatSuccess = new WritableCellFormat();
            descriptionFormatSuccess.setFont(fontDescription);
            descriptionFormatSuccess.setBackground(Colour.GREEN);
            descriptionFormatSuccess.setAlignment(Alignment.CENTRE);

            WritableCellFormat descriptionFormatError = new WritableCellFormat();
            descriptionFormatError.setFont(fontDescription);
            descriptionFormatError.setBackground(Colour.RED);
            descriptionFormatError.setAlignment(Alignment.CENTRE);

            Integer[] columns = new Integer[listener.getColumnLength(type)];
            listener.getColumnSize(columns, type);
            for (int i = 0; i < columns.length; i++) {
                logSheet.setColumnView(i * 2, columns[i]);
                logSheet.setColumnView(i * 2 + 1, columns[i]);
            }
            //tanzim arze vaziat nahaii
            logSheet.setColumnView(columns.length * 2, 60);

            if (hasHeader) {
                Label headerLabel = null;
                for (int i = 0; i < columns.length; i++) {
                    headerLabel = new Label(i * 2, 0, sheetData[0][i], headerFormat);
                    logSheet.setRowView(0, 400);
                    logSheet.addCell(headerLabel);
                    headerLabel = new Label((i * 2) + 1, 0, "توضیحات", headerFormat);
                    logSheet.setRowView(0, 400);
                    logSheet.addCell(headerLabel);
                }
                headerLabel = new Label((columns.length) * 2, 0, "وضعیت نهایی", headerFormat);
                logSheet.setRowView(0, 400);
                logSheet.addCell(headerLabel);
            }

            boolean isValidSheet = true;
            for (int i = 0; i < sheetData.length; i++) {
                if (hasHeader && i == 0) {
                    continue;
                }
                String[] row = sheetData[i];
                boolean isValidRow = true;

                for (int j = 0; j < row.length; j++) {

                    try {
                        ImportResult importResult = listener.onRowDataValidation(row[j], j, type);
                        if (!importResult.isResult()) {
                            BeautyLog.println(importResult.getMessage());
                            isValidRow = false;
                        }
                        Label headerLabel = new Label(j * 2, i, row[j], importResult.isResult() ? descriptionFormatSuccess : descriptionFormatError);
                        logSheet.setRowView(0, 400);
                        logSheet.addCell(headerLabel);
                        headerLabel = new Label((j * 2) + 1, i, importResult.getMessage(), importResult.isResult() ? descriptionFormatSuccess : descriptionFormatError);
                        logSheet.setRowView(0, 400);
                        logSheet.addCell(headerLabel);
                    } catch (Exception e) {
                        isValidRow = false;
                        BeautyLog.println(e.getStackTrace());
                        Label headerLabel = new Label(j * 2, i, row[j], descriptionFormatError);
                        logSheet.setRowView(0, 400);
                        logSheet.addCell(headerLabel);
                        headerLabel = new Label((j * 2) + 1, i, e.getLocalizedMessage(), descriptionFormatError);
                        logSheet.setRowView(0, 400);
                        logSheet.addCell(headerLabel);
                    }

                }
                if (isValidRow) {
                    Object object = listener.onReadRow(row, type);
                    if (batchMode) {
                        Label headerLabel = null;
                        try {
                            listener.onFinishRow(object, type);
                            headerLabel = new Label(((columns.length) * 2), i, "با موفقیت ذخیره شد.", descriptionFormatSuccess);
                            logSheet.setRowView(0, 400);
                            logSheet.addCell(headerLabel);
                        } catch (Exception e) {
                            BeautyLog.println(e);
                            headerLabel = new Label(((columns.length) * 2), i, e.getLocalizedMessage(), descriptionFormatError);
                            logSheet.setRowView(0, 400);
                            logSheet.addCell(headerLabel);
                        }
                    }
                }
                if (!isValidRow && !batchMode) {
                    isValidSheet = false;
                }
            }
            if (isValidSheet) {
                listener.onFinishSuccessSheet(new ArrayList<>(), type);
            }
            listener.onFinishSheet(new ArrayList<>(), type);

            writeWorkbook.write();
            writeWorkbook.close();
            byte[] bytes = Files.readAllBytes(logFile.toPath());
            logFile.delete();
            return bytes;
        } catch (Exception e) {
            BeautyLog.println(e);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getLocalizedMessage());
        } finally {
            if (logFile != null) {
                logFile.delete();
            }
        }

    }

}
