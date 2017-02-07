/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.everlast.qtt.manager.adapter;

import java.util.ArrayList;
import javax.swing.event.TableModelListener;

/**
 *
 * @author xuguobiao
 */
public class RegisterTableModel implements javax.swing.table.TableModel {

    private String[] titles = {};
    private ArrayList<String[]> modelList = new ArrayList<String[]>();

    public RegisterTableModel(String[] titles, ArrayList<String[]> modelList) {
        this.titles = titles;
        this.modelList = modelList;
    }

    //多少行:
    public int getRowCount() {
        return modelList.size();
    }

    //多少列
    public int getColumnCount() {
        return titles.length;
    }

//取得列名   
    public String getColumnName(int columnIndex) {
        return titles[columnIndex];
    }

    //每一列的数据类型:我们这里显示的都是String型
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;

    }

    //指定的单元格是否可从界面上编辑
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;

    }

    //取得指定单元格的值
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowIndex + "--" + columnIndex;

    }

    //从表格界面上改变了某个单元格的值后会调用这个方法
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        String s = "Change at: " + rowIndex + "--- " + columnIndex + " newValue: " + aValue;

        System.out.println(s);

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
