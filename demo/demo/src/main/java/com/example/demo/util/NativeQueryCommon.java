package com.example.demo.util;

import com.example.demo.model.constant.Constant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;

import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.Map.Entry;

public class NativeQueryCommon {

    private static final String DIRECTION_ASC = "ASC";
    private static final String DIRECTION_DESC = "DESC";
    private static final String AND = " AND ";
    private static final String OR = " OR ";
    private static final String CAST = " CAST ";
    private static final String AS_NUMBER = " AS NUMBER ";
    private static final String TRUNC = " TRUNC ";
    private static final String TO_DATE = " TO_DATE ";
    private static final String FORMAT_DATE = "  ,  'yyyy-mm-dd' ";
    private static final String FORMAT_DATE_TIME = "  ,  'yyyy-mm-dd\"T\"HH24:MI:SS' ";
    private static final String OPEN_ROUND_BRACKETS = " ( ";
    private static final String CLOSE_ROUND_BRACKETS = " ) ";
    private static final String BW = " BETWEEN ";
    private static final String NOT_BW = " NOT BETWEEN ";
    private static final String IS_NULL = " IS NULL ";
    private static final String TRIM = " TRIM ";
    private static final String UPPER = " UPPER ";
    private static final String ESCAPE = " ESCAPE'\\' ";
    private static final String NOT_LIKE = " NOT LIKE ";
    private static final String LIKE = " LIKE ";
    private static final String TRUE_VALUE = " = 1 ";
    private static final String FALSE_VALUE = " = 0 ";
    private static final String THEN = " THEN ";
    private static final String NULL = " null ";
    private static final String LOWER_CASE = " LOWER ";
    private static final String SUB_CURRENT_DATE_SYSDATE = " - (CURRENT_DATE - SYSDATE) ";
    private static final String ADD_CURRENT_DATE_SYSDATE = " + (CURRENT_DATE - SYSDATE) ";
    private static final String ORDER_BY = " ORDER BY ";
    private static final String CASE_WHEN_PRO = " CASE WHEN :pro  = '";
    private static final String END_ASC = " END ASC ";
    private static final String END_DESC = " END DESC ";
    private static final Long MINUTE = 59L;
    private static final String FIRST_NUMBER_PARAM = "firstNumberHHMM";
    private static final String SECOND_NUMBER_PARAM = "secondNumberHHMM";

    public static void setHeaders(HttpHeaders httpHeaders, Page<?> page) {
        httpHeaders.add("Access-Control-Expose-Headers", "X-Total-Count");
        httpHeaders.add("X-Total-Count", Long.toString(page.getTotalElements()));
    }

    /**
     * </k>
     *      set param for native query
     * </k>
     */
    public static void setParams(Query query, Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            Set<Entry<String, Object>> set = params.entrySet();
            for (Entry<String, Object> obj : set) {
                if (obj.getValue() == null) {
                    query.setParameter(obj.getKey(), "");
                } else {
                    query.setParameter(obj.getKey(), obj.getValue());
                }
            }
        }
    }

    /**
     * <k>
     *      set param & pageable for native query
     *      Again set value offset in case offset > total record
     * * </k>
     */
    public static void setParamsWithPageable(@NotNull Query query, Map<String, Object> params, @NotNull Pageable pageable, @NotNull Number total) {
        if (params != null && !params.isEmpty()) {
            Set<Entry<String, Object>> set = params.entrySet();
            for (Entry<String, Object> obj : set) {
                query.setParameter(obj.getKey(), obj.getValue());
            }
        }
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
    }

    /**
     * <k>
     *      set param and pageable for native query
     *      condition: start page = 1
     * </k>
     */
    public static void setParamsWithPageableStartPageOne(@NotNull Query query, Map<String, Object> params, @NotNull Pageable pageable, @NotNull Number total) {
        if (params != null && !params.isEmpty()) {
            Set<Entry<String, Object>> set = params.entrySet();
            for (Entry<String, Object> obj : set) {
                query.setParameter(obj.getKey(), obj.getValue());
            }
        }

        int fist = 0;
        if (pageable.getPageNumber() == 0) {
            query.setFirstResult(fist);
        } else {
            query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        }
        query.setMaxResults(pageable.getPageSize());
    }

    /**
     * <k>
     *      get key of maps
     * </k>
     */
    public static <K, V> K getKeyForSort(Map<K, V> map, V value) {
        for (Entry<K, V> entry : map.entrySet()) {
            if (entry.getKey().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
      * <k>
      *     get list key of maps
      * </k>
     */
    public static <K, V> Set<K> getKeysForSortMulti(Map<K, V> map, V value) {
        Set<K> keys = new HashSet<>();
        for (Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    public static <K, V> V getValueForSort(Map<K, V> map, V value) {
        for (Entry<K, V> entry : map.entrySet()) {
            if (entry.getKey().equals(value)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static StringBuilder orderSortProgram (Pageable pageable, Map<String, String> nameColumnMap, Map<String, Object> params ){
        String nameFieldSort = "";
        Sort sortsSort = pageable.getSort();
        Sort.Order orders = sortsSort.stream().findFirst().orElse(null);
        if (orders != null && !StringUtils.isEmpty(orders.getProperty()) && !"null".equals(orders.getProperty())) {
            if (nameColumnMap.containsKey(orders.getProperty())) {
                nameFieldSort = NativeQueryCommon.getValueForSort(nameColumnMap,orders.getProperty());

            }
        }

        return buildSqlOrder(orders, nameFieldSort, params);
    }

    /**
     * <k>
     *      getProperty() of sort
     * </k>
     */
    public static StringBuilder orderBySort (Pageable pageable, Map<String, String> nameColumnMap, Map<String, Object> params ){
        String nameFieldSort = "";
        Sort sortsSort = pageable.getSort();
        Sort.Order orders = sortsSort.stream().findFirst().orElse(null);
        if (orders != null && !StringUtils.isEmpty(orders.getProperty()) && !"null".equals(orders.getProperty())) {
            if (!nameColumnMap.containsKey(orders.getProperty())) {
                return buildSqlOrder(orders, nameFieldSort, params);
            }
//                sortOrder += " " + orders.getProperty() + " " + orders.getDirection();
            nameFieldSort = NativeQueryCommon.getKeyForSort(nameColumnMap,orders.getProperty());
        }

        return buildSqlOrder(orders, nameFieldSort, params);
    }

    public static StringBuilder multipleOrder(Pageable pageable, Map<String, String> nameColumnMap, Map<String, Object> params ){
        StringBuilder sqlOrderBy = new StringBuilder();
        int index = 0;
        for (Sort.Order order: pageable.getSort()){
            String nameFieldSort = "";
            if(index == 0){
                sqlOrderBy.append(ORDER_BY);
            }
            if (order != null && !StringUtils.isEmpty(order.getProperty()) && !"null".equals(order.getProperty())) {
                if (!nameColumnMap.containsKey(order.getProperty())) {
                     buildSqlOrders(order, nameFieldSort, params, sqlOrderBy);
                }else{
                    nameFieldSort = NativeQueryCommon.getKeyForSort(nameColumnMap,order.getProperty());
                }
            }
            buildSqlOrders(order, nameFieldSort, params, sqlOrderBy, index);
            if(index < pageable.getSort().stream().count() - 1){
                sqlOrderBy.append(" , ");
            }
            index++;
        }
        return sqlOrderBy;
    }

    public static StringBuilder multipleOrder(Pageable pageable, Map<String, String> nameColumnMap, Map<String, Object> params, String sortDefault ){
        StringBuilder sqlOrderBy = new StringBuilder();
        int index = 0;
        Iterator<Sort.Order> orderSize =  pageable.getSort().iterator();
        int sizeSort = 0;
        while (orderSize.hasNext()) {
            sizeSort++;
            orderSize.next();
        }
        if (sizeSort > 1 ){
            for (Sort.Order order: pageable.getSort()){
                String nameFieldSort = "";
                if(index == 0){
                    sqlOrderBy.append(ORDER_BY);
                }
                if (order != null && !StringUtils.isEmpty(order.getProperty()) && !"null".equals(order.getProperty())) {
                    if (!nameColumnMap.containsKey(order.getProperty())) {
                        buildSqlOrders(order, nameFieldSort, params, sqlOrderBy);
                    }else{
                        nameFieldSort = NativeQueryCommon.getKeyForSort(nameColumnMap,order.getProperty());
                    }
                }
                buildSqlOrders(order, nameFieldSort, params, sqlOrderBy, index);
                if(index < pageable.getSort().stream().count() - 1){
                    sqlOrderBy.append(" , ");
                }
                index++;
            }
        }else {
            sqlOrderBy.append(ORDER_BY).append(sortDefault).append(" DESC ");
        }

        return sqlOrderBy;
    }

    public static StringBuilder orderBySortAdvanced(Pageable pageable, Map<String, String> nameColumnMap, Map<String, Object> params ){
        Sort sortsSort = pageable.getSort();
        Sort.Order orders = sortsSort.stream().findFirst().orElse(null);
        if (orders != null && !StringUtils.isEmpty(orders.getProperty()) && !"null".equals(orders.getProperty())
                && nameColumnMap.containsKey(orders.getProperty())) {
            String nameFieldSort = NativeQueryCommon.getKeyForSort(nameColumnMap,orders.getProperty());
            String valueFieldSort = nameColumnMap.get(nameFieldSort);
            return buildSqlOrderAdvanced(orders, nameFieldSort, valueFieldSort, params);
        }
        return new StringBuilder(" ORDER BY NULL ");
    }

    /**
     * <k>
     *     ORDER BY LOWER_CASE
     * </k>
     */
    public static StringBuilder buildSqlOrder(Sort.Order orders, String nameFieldSort, Map<String, Object> params) {
        StringBuilder sqlOrderBy = new StringBuilder(ORDER_BY);
        if ("".equals(nameFieldSort)) {
            return sqlOrderBy.append(NULL);
        }
        String typeDirection = orders.getDirection().name();
        switch (typeDirection){
            case DIRECTION_ASC:
                if (Constant.FIELD_VALUE.equals(nameFieldSort)) {
                    sqlOrderBy.append(CASE_WHEN_PRO).append(nameFieldSort).append("'").append(THEN)
                        .append("to_char(").append(nameFieldSort).append(")").append(END_ASC);
                    params.put("pro", orders.getProperty());
                } else {
                    sqlOrderBy.append(CASE_WHEN_PRO).append(nameFieldSort).append("'").append(THEN).append(nameFieldSort).append(END_ASC);
                    params.put("pro", orders.getProperty());
                }
                break;
            case DIRECTION_DESC:
                if (Constant.FIELD_VALUE.equals(nameFieldSort)) {
                    sqlOrderBy.append(CASE_WHEN_PRO).append(nameFieldSort).append("'").append(THEN)
                        .append("to_char(").append(nameFieldSort).append(")").append(END_DESC);
                    params.put("pro", orders.getProperty());
                } else {
                    sqlOrderBy.append(CASE_WHEN_PRO).append(nameFieldSort).append("'").append(THEN).append(nameFieldSort).append(END_DESC);
                    params.put("pro", orders.getProperty());
                }
                break;
            default:
                sqlOrderBy.append(NULL);
                break;
        }
        return sqlOrderBy;
    }

    public static StringBuilder buildSqlOrders(Sort.Order order, String nameFieldSort, Map<String, Object> params, StringBuilder sqlOrderBy) {
        if ("".equals(nameFieldSort)) {
            return sqlOrderBy.append(NULL);
        }
        String typeDirection = order.getDirection().name();
        switch (typeDirection){
            case DIRECTION_ASC:
                sqlOrderBy.append(CASE_WHEN_PRO).append(nameFieldSort).append("'").append(THEN).append(nameFieldSort).append(END_ASC);
                params.put("pro", order.getProperty() );
                break;
            case DIRECTION_DESC:
                sqlOrderBy.append(CASE_WHEN_PRO).append(nameFieldSort).append("'").append(THEN).append(nameFieldSort).append(END_DESC);
                params.put("pro", order.getProperty() );
                break;
            default:
                sqlOrderBy.append(NULL);
                break;
        }
        return sqlOrderBy;
    }

    public static StringBuilder buildSqlOrderAdvanced(Sort.Order orders, String nameFieldSort, String valueFieldSort, Map<String, Object> params) {
        StringBuilder sqlOrderBy = new StringBuilder(ORDER_BY);
        if ( "".equals(nameFieldSort) || "".equals(valueFieldSort)) {
            return sqlOrderBy.append(NULL);
        }
        String typeDirection = orders.getDirection().name();
        switch (typeDirection){
            case DIRECTION_ASC:
                sqlOrderBy.append(CASE_WHEN_PRO).append(nameFieldSort).append("'").append(THEN).append(valueFieldSort).append(END_ASC);
                params.put("pro", orders.getProperty() );
                break;
            case DIRECTION_DESC:
                sqlOrderBy.append(CASE_WHEN_PRO).append(nameFieldSort).append("'").append(THEN).append(valueFieldSort).append(END_DESC);
                params.put("pro", orders.getProperty() );
                break;
            default:
                sqlOrderBy.append(NULL);
                break;
        }
        return sqlOrderBy;
    }

    public static StringBuilder sortParent(Pageable pageable, Map<String, String> nameColumnMap) {
        StringBuilder sort = new StringBuilder("ORDER BY ");
        Sort.Order orders = pageable.getSort().stream().findFirst().orElse(null);
        if (Objects.nonNull(orders)) {
            if (nameColumnMap.containsKey(orders.getProperty())) {
                sort.append(NativeQueryCommon.getValueForSort(nameColumnMap, orders.getProperty()));
                if (orders.getDirection().isAscending()) {
                    sort.append(" ASC ");
                } else {
                    sort.append(" DESC ");
                }
            } else {
                sort.append(NULL);
            }
        } else {
            sort.append(NULL);
        }
        return sort;
    }

    private static StringBuilder getCastStringDateToLong(String col) {
        return new StringBuilder("CAST(replace(").append(col).append(", '-', '')AS numeric)  ");
    }


    private static StringBuilder getCastStringTimeToLong(String col) {
        return new StringBuilder("CAST(replace(").append(col).append(", ':', '')AS numeric)  ");
    }

    public static StringBuilder buildSqlOrders(Sort.Order order, String nameFieldSort, Map<String, Object> params, StringBuilder sqlOrderBy, Integer count) {
        if ("".equals(nameFieldSort)) {
            return sqlOrderBy.append(NULL);
        }
        String typeDirection = order.getDirection().name();
        switch (typeDirection){
            case DIRECTION_ASC:
                sqlOrderBy.append(" CASE WHEN :pro").append(count).append("  = '").append(nameFieldSort).append("'").append(THEN).append(nameFieldSort).append(END_ASC);
                params.put("pro" + count, order.getProperty() );
                break;
            case DIRECTION_DESC:
                sqlOrderBy.append(" CASE WHEN :pro").append(count).append("  = '").append(nameFieldSort).append("'").append(THEN).append(nameFieldSort).append(END_DESC);
                params.put("pro" + count, order.getProperty() );
                break;
            default:
                sqlOrderBy.append(NULL);
                break;
        }
        return sqlOrderBy;
    }
}


