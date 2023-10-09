package com.gxh.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChartBarBean {
    private List<Object> categories=new ArrayList<>();
    private List<Object> data=new ArrayList<>();

}
