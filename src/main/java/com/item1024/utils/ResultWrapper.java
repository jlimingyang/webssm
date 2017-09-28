package com.item1024.utils;

import java.util.List;

/**
 * 用于包装接口返回
 * <p>
 * 返回形如： {"sta":1,"msg":"Success","data":[{"filename":"Schedule.xlsx","state":"success"},{"filename":"Schedule.xlsx","state":"success"}]}
 * sta：表示访问状态 1为成功 0为失败
 * msg：表示具体错误信息
 * data：表示返回的具体数据
 * @author xiongyu
 * @since 2017/4/19
 */
public class ResultWrapper<T> {
    /**
     * 状态码
     */
    private Integer sta;
    /**
     * 状态信息
     */
    private String msg;
    /**
     * 数据data
     */
    private T data;




    public ResultWrapper OK() {

        this.sta = 1;
        this.msg = "Success";
        return this;
    }

    /**
     *
     * @param data
     * @return
     */
    public ResultWrapper OKWithData(T data) {
        this.OK();
        this.data = data;
        return this;
    }

    public ResultWrapper Error() {

        this.sta = 0;
        this.msg = "Fail";
        return this;
    }

    public ResultWrapper ErrorWithData(T data) {
        this.Error();
        this.data = data;
        return this;
    }

    public ResultWrapper Customsta(Integer sta, String msg, T data) {
        this.sta = sta;
        this.msg = msg;
        this.data = data;
        return this;
    }


    /**
     * 用于滚动数据的返回,举例瀑布流
     *
     * @param <E>
     */
    public static class ScrollData<E> {
        private Integer hasMore;

        private Integer nextSatrt;

        private List<E> objectList;

        public ScrollData(boolean hasMore, Integer nextSatrt, List<E> objectList) {
            this.hasMore = hasMore ? 1 : 0;
            this.nextSatrt = nextSatrt;
            this.objectList = objectList;
        }
    }

    /**
     * 用于分页数据返回
     *
     * @param <E>
     */
    private static class PaginationData<E> {
        private Integer total;
        private Integer limit;
        private List<E> objectList;

        public PaginationData(Integer limit, Integer total, List<E> objectList) {
            this.limit = limit;
            this.total = total;
            this.objectList = objectList;
        }
    }

}
