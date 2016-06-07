package com.bodhi.util;


import java.util.ArrayList;
import java.util.List;

public class SelectBuilderUtil {

    private List<String> columns = new ArrayList<String>();

    private List<String> tables = new ArrayList<String>();

    private List<String> joins = new ArrayList<String>();

    private List<String> leftJoins = new ArrayList<String>();

    private List<String> wheres = new ArrayList<String>();

    private List<String> orderBys = new ArrayList<String>();

    private List<String> groupBys = new ArrayList<String>();

    private List<String> havings = new ArrayList<String>();

    public SelectBuilderUtil() {

    }

    public SelectBuilderUtil(String table) {
        tables.add(table);
    }

    private void appendList(StringBuilder sql, List<String> list, String init,
String sep) {
        boolean first = true;
        for (String s : list) {
            if (first) {
                sql.append(init);
            } else {
                sql.append(sep);
            }
            sql.append(s);
            first = false;
        }
    }

    public SelectBuilderUtil column(String name) {
        columns.add(name);
        return this;
    }

    public SelectBuilderUtil column(String name, boolean groupBy) {
        columns.add(name);
        if (groupBy) {
            groupBys.add(name);
        }
        return this;
    }

    public SelectBuilderUtil from(String table) {
        tables.add(table);
        return this;
    }

    public SelectBuilderUtil groupBy(String expr) {
        groupBys.add(expr);
        return this;
    }

    public SelectBuilderUtil having(String expr) {
        havings.add(expr);
        return this;
    }

    public SelectBuilderUtil join(String join) {
        joins.add(join);
        return this;
    }

    public SelectBuilderUtil leftJoin(String join) {
        leftJoins.add(join);
        return this;
    }

    public SelectBuilderUtil orderBy(String name) {
        orderBys.add(name);
        return this;
    }

    @Override
    public String toString() {

        StringBuilder sql = new StringBuilder("select ");

        if (columns.size() == 0) {
            sql.append("*");
        } else {
            appendList(sql, columns, "", ", ");
        }

        appendList(sql, tables, " from ", ", ");
        appendList(sql, joins, " join ", " join ");
        appendList(sql, leftJoins, " left join ", " left join ");
        appendList(sql, wheres, " where ", " and ");
        appendList(sql, groupBys, " group by ", ", ");
        appendList(sql, havings, " having ", " and ");
        appendList(sql, orderBys, " order by ", ", ");

        return sql.toString();
    }

    public SelectBuilderUtil where(String expr) {
        wheres.add(expr);
        return this;
    }
}