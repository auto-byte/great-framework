package com.great.page;

import com.github.pagehelper.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created on 2021/11/16 11:38
 *
 * @author Y.X
 */
public class Pages {
    /**
     * 分页查询数据
     *
     * @param pageNum  页码
     * @param pageSize 分页大小
     * @param supplier 不带分页的查询操作
     * @param <E>      查询返回的结果
     * @return PageInfo
     */
    public static <E> Page<E> page(Integer pageNum, Integer pageSize, Supplier<List<E>> supplier) {
        return page(PageImpl.of(pageNum, pageSize, null), supplier);
    }

    /**
     * @param page     分页参数
     * @param supplier 查询操作
     * @param <E>      元素类型
     * @return PageInfo
     */
    public static <E> Page<E> page(IPage page, Supplier<List<E>> supplier) {
        if (page == null) {
            page = PageImpl.of(null, null, null);
        }
        return PageHelper.startPage(page).doSelectPage(supplier::get);
    }

    /**
     * 将 pageInfo 转成 pageResult
     *
     * @param pageInfo pageInfo
     * @param <E>      元素类型
     * @return pageResult
     */
    public static <E> PageResult<E> pageResult(PageInfo<E> pageInfo) {
        PageResult<E> pageResult = new PageResultImpl<>();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setList(pageInfo.getList());
        return pageResult;
    }

    /**
     * 将page转pageResult
     *
     * @param page 分页结果
     * @param <E>  类型
     * @return pageResult
     */
    public static <E> PageResult<E> pageResult(Page<E> page) {
        PageResult<E> pageResult = new PageResultImpl<>();
        pageResult.setPageNum(page.getPageNum());
        pageResult.setPageSize(page.getPageSize());
        pageResult.setTotal(page.getTotal());
        pageResult.setList(page.getResult());
        return pageResult;
    }

    /**
     * 分页查询并将结果转成pageResult
     *
     * @param pageNum  页码
     * @param pageSize 分页大小
     * @param supplier 查询操作
     * @param <E>      元素类型
     * @return pageResult
     */
    public static <E> PageResult<E> pageResult(Integer pageNum, Integer pageSize, Supplier<List<E>> supplier) {
        return pageResult(page(pageNum, pageSize, supplier));
    }

    /**
     * @param pageNum  页码
     * @param pageSize 分页大小
     * @param supplier 查询操作
     * @param function 元素转换
     * @param <R>      预期的类型
     * @param <E>      原先的类型
     * @return PageResult
     */
    public static <R, E> PageResult<R> pageResult(Integer pageNum, Integer pageSize,
                                                  Supplier<List<E>> supplier, Function<E, R> function) {
        return pageResult(pageNum, pageSize, supplier).map(function);
    }


    /**
     * @param iPage    分页参数
     * @param supplier 查询操作
     * @param <E>      元素类型
     * @return PageResult
     */
    public static <E> PageResult<E> pageResult(IPage iPage, Supplier<List<E>> supplier) {
        return pageResult(page(iPage, supplier));
    }

    /**
     * @param iPage    分页参数
     * @param supplier 查询操作
     * @param function 元素转换
     * @param <R>      预期的类型
     * @param <E>      原先的类型
     * @return PageResult
     */
    public static <R, E> PageResult<R> pageResult(IPage iPage,
                                                  Supplier<List<E>> supplier, Function<E, R> function) {
        return pageResult(iPage, supplier).map(function);
    }

}
