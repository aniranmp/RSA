package ir.aniranmp.web.util;



import ir.aniranmp.web.model.local.ImportResult;

import java.util.List;

public interface ExcelHandlerListener {
    String getSheetName(int type);


    int getColumnLength(int type);

    void getColumnSize(Integer[] columns, int type);

    void onHeaderTitles(String[] titles, int type);

    <DataType> void onRowData(Object[] rowData, DataType o, int r, int type);

    ImportResult onRowDataValidation(String data, int col, int type);
    Object onReadRow(String[] data, int type);

    void onFinishRow(Object data, int type);

    void onFinishSuccessSheet(List<Object> datas, int type);
    void onFinishSheet(List<Object> datas, int type);
}
