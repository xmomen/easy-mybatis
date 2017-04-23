package com.xmomen.framework.mybatis.support;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jengt_000 on 2014/12/27.
 */
public class MybatisType {

    // 类型处理器 - Java 类型 - JDBC 类型
    // BooleanTypeHandler - Boolean，boolean - 任何兼容的布尔值
    // ByteTypeHandler - Byte，byte - 任何兼容的数字或字节类型
    // ShortTypeHandler - Short，short - 任何兼容的数字或短整型
    // IntegerTypeHandler - Integer，int - 任何兼容的数字和整型
    // LongTypeHandler - Long，long - 任何兼容的数字或长整型
    // FloatTypeHandler - Float，float - 任何兼容的数字或单精度浮点型
    // DoubleTypeHandler - Double，double - 任何兼容的数字或双精度浮点型
    // BigDecimalTypeHandler BigDecimal - 任何兼容的数字或十进制小数类型
    // StringTypeHandler - String - CHAR 和VARCHAR 类型
    // ClobTypeHandler - String - CLOB 和LONGVARCHAR 类型
    // NStringTypeHandler - String - NVARCHAR 和NCHAR 类型
    // NClobTypeHandler - String - NCLOB 类型
    // ByteArrayTypeHandler byte[] - 任何兼容的字节流类型
    // BlobTypeHandler - byte[] - BLOB 和LONGVARBINARY 类型
    // DateTypeHandler - Date （java.util ） - TIMESTAMP 类型
    // DateOnlyTypeHandler Date （java.util ） - DATE 类型
    // TimeOnlyTypeHandler Date （java.util ） - TIME 类型
    // SqlTimestampTypeHandler Timestamp （java.sql） - TIMESTAMP 类型
    // SqlDateTypeHandler - Date （java.sql ） - DATE 类型
    // SqlTimeTypeHandler - Time （java.sql ） - TIME 类型
    // ObjectTypeHandler - Any - 其他或未指定类型
    // EnumTypeHandler - Enumeration 类型 - VARCHAR任何兼容的字符串类型，作为代码存储（而不是索引）

    public static final Map<String, String> javaTypeMap = new HashMap<String, String>();

    static {
        javaTypeMap.put("String", "VARCHAR");
        javaTypeMap.put("Boolean", "BIT");
        javaTypeMap.put("Date", "TIMESTAMP");
        javaTypeMap.put("Long", "DECIMAL");
        javaTypeMap.put("Integer", "DECIMAL");
        javaTypeMap.put("Double", "DECIMAL");
        javaTypeMap.put("BigDecimal", "DECIMAL");
        javaTypeMap.put("byte[]", "BLOB");
    }
}
