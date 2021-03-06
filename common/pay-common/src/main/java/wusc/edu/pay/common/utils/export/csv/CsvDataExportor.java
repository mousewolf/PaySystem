package wusc.edu.pay.common.utils.export.csv;

import wusc.edu.pay.common.utils.export.DataField;
import wusc.edu.pay.common.utils.export.ExportDataSource;
import wusc.edu.pay.common.utils.export.txt.TxtDataExportor;

import java.io.OutputStream;


/**
 * 描述: csv格式数据导出工具
 *
 * @author Hill
 */
public class CsvDataExportor<T> extends TxtDataExportor<T> {

    public CsvDataExportor(DataField[] fields, ExportDataSource<T> dataSource, OutputStream os) {
        super(fields, dataSource, os, ",");
    }

}
