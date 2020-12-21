package com.smile2coder.common;

import lombok.Builder;
import lombok.Data;

/**
 * @Author LKK
 * Date on 2020/8/17  下午 4:24
 */
@Data
@Builder
public class Page<T> {
    private int page;
    private int limit;
    private int total;
    private T data;
}
