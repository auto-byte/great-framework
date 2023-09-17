package com.great.common.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 集合流快捷操作
 *
 * @author Y.X
 * @since 2021/9/7
 */
public abstract class StreamUtils {
    /**
     * 集合转换成另一个List
     *
     * @param collection 源集合
     * @param map        转换函数
     * @param <T>        源类型
     * @param <R>        目标类型
     * @return List<R>
     */
    public static <T, R> List<R> mapToList(Collection<T> collection, Function<T, R> map) {
        return collection.stream().map(map).collect(Collectors.toList());
    }

    /**
     * 集合过滤成另一个List
     *
     * @param collection 源集合
     * @param pred       过滤断言
     * @param <T>        源类型
     * @return List<T>
     */
    public static <T> List<T> filterToList(Collection<T> collection, Predicate<T> pred) {
        return collection.stream().filter(pred).collect(Collectors.toList());
    }

    /**
     * 集合过滤并转换成另一个List
     *
     * @param collection 源集合
     * @param pred       过滤断言
     * @param map        转换函数
     * @param <T>        源类型
     * @param <R>        目标类型
     * @return List<T>
     */
    public static <T, R> List<R> filterAndMapToList(Collection<T> collection,
                                                    Predicate<T> pred,
                                                    Function<T, R> map) {
        return collection.stream().filter(pred).map(map).collect(Collectors.toList());
    }

    /**
     * 集合过滤并转换成另一个Set
     *
     * @param collection 源集合
     * @param map        转换函数
     * @param <T>        源类型
     * @param <R>        目标类型
     * @return Set<R>
     */
    public static <T, R> Set<R> mapToSet(Collection<T> collection, Function<T, R> map) {
        return collection.stream().map(map).collect(Collectors.toSet());
    }

    /**
     * 集合过滤成另一个Set
     *
     * @param collection 源集合
     * @param <T>        源类型
     * @return Set<T>
     */
    public static <T> Set<T> filterToSet(Collection<T> collection, Predicate<T> pred) {
        return collection.stream().filter(pred).collect(Collectors.toSet());
    }

    /**
     * @param collection 源集合
     * @param pred       过滤断言
     * @param map        转换函数
     * @param <T>        源类型
     * @param <R>        目标类型
     * @return Set<R>
     */
    public static <T, R> Set<R> filterAndMapToSet(Collection<T> collection,
                                                  Predicate<T> pred,
                                                  Function<T, R> map) {
        return collection.stream().filter(pred).map(map).collect(Collectors.toSet());
    }

    /**
     * 集合转换成Map
     *
     * @param collection  源集合
     * @param keyMapper   Key mapper
     * @param valueMapper Value mapper
     * @param <T>         源类型
     * @param <K>         Key类型
     * @param <V>         Value类型
     * @return Map<K, V>
     */
    public static <T, K, V> Map<K, V> toMap(Collection<T> collection,
                                            Function<? super T, ? extends K> keyMapper,
                                            Function<? super T, ? extends V> valueMapper) {
        return toMap(collection, keyMapper, valueMapper, true);
    }

    /**
     * 集合转换成Map
     *
     * @param collection  源集合
     * @param keyMapper   Key mapper
     * @param valueMapper Value mapper
     * @param rewrite     是否让新值替换旧值
     * @param <T>         源类型
     * @param <K>         Key类型
     * @param <V>         Value类型
     * @return Map<K, V>
     */
    public static <T, K, V> Map<K, V> toMap(Collection<T> collection,
                                            Function<? super T, ? extends K> keyMapper,
                                            Function<? super T, ? extends V> valueMapper,
                                            boolean rewrite) {
        return collection.stream().collect(
                Collectors.toMap(keyMapper, valueMapper, (o, n) -> rewrite ? n : o)
        );
    }
}
