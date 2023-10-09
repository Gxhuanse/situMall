package com.gxh.dao;

import java.util.List;

public interface ProductPicDao {
    int insertImgBatch(Integer id, List<String> pics);

    int deleteImgBatch(Integer id);
}
