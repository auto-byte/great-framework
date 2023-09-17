package com.great.cache;

/**
 * 缓存明及失效时间
 * e -> expire
 * h -> hour
 * d -> day
 * m -> month
 * y -> year
 *
 * @author Y.X
 * @since 2021/9/12
 */
public enum CacheExpire {
    HOUR_ONE(CacheNameCons.HOUR_ONE, 60L * 60),
    HOUR_TEN(CacheNameCons.HOUR_TEN, 60L * 60 * 10),

    DAY_ONE(CacheNameCons.DAY_ONE, 60L * 60 * 24),
    DAY_SEVEN(CacheNameCons.DAY_SEVEN, 60L * 60 * 24 * 7),
    DAY_FIFTY(CacheNameCons.DAY_FIFTY, 60L * 60 * 24 * 15),

    MONTH_ONE(CacheNameCons.MONTH_ONE, 60L * 60 * 24 * 30),
    MONTH_THREE(CacheNameCons.MONTH_THREE, 60L * 60 * 24 * 30 * 3),
    MONTH_SIX(CacheNameCons.MONTH_SIX, 60L * 60 * 24 * 30 * 6),

    YEAR_ONE(CacheNameCons.YEAR_ONE, 60L * 60 * 24 * 30 * 12);

    /**
     * 缓存明，redis缓存时会自动添加明作为前缀
     */
    private final String name;

    /**
     * 过期时间 秒
     */
    private final long expire;

    CacheExpire(String name, long expire) {
        this.name = name;
        this.expire = expire;
    }

    public String getName() {
        return name;
    }

    public long getExpire() {
        return expire;
    }
}
