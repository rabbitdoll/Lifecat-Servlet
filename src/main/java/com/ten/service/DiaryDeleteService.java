package com.ten.service;

import com.ten.service.util.Service;

/**
 * 日记删除
 *
 * @date 2018/5/24
 * @auther ten
 */
public interface DiaryDeleteService extends Service {

    Integer deleteDiary(int diaryId);
}
