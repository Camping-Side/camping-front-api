package com.commerce.song.util.dsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.util.StringUtils;

public class DslUtil {
    public static BooleanExpression cprNumEq(NumberPath<Integer> target, Integer number) {
        return number == null ? null : target.eq(number);
    }
    public static BooleanExpression cprNumEq(NumberPath<Long> target, Long number) {
        return number == null ? null : target.eq(number);
    }
    public static BooleanExpression cprStrEq(StringPath target, String str) {
        return StringUtils.hasText(str) ? target.eq(str) : null;
    }
    public static BooleanExpression cprStrLike(StringPath target, String str) {
        return StringUtils.hasText(str) ? target.like("%" + str + "%") : null;
    }
}
