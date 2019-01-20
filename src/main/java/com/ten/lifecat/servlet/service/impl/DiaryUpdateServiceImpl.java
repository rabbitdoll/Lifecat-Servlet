package com.ten.lifecat.servlet.service.impl;

import com.ten.lifecat.servlet.entity.Diary;
import com.ten.lifecat.servlet.constant.Page;
import com.ten.lifecat.servlet.dao.JdbcDAOFactory;
import com.ten.lifecat.servlet.constant.Page;
import com.ten.lifecat.servlet.service.DiaryUpdateService;
import com.ten.lifecat.servlet.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * 日记内容更新
 *
 * @date 2018/5/24
 * @auther ten
 */
public class DiaryUpdateServiceImpl implements DiaryUpdateService {
    private Logger logger = LoggerFactory.getLogger(DiaryUpdateServiceImpl.class);

    private DiaryDAO dao;

    public DiaryUpdateServiceImpl() {
        dao = (DiaryDAO) JdbcDAOFactory.getDaoByTableName("diary");
    }

    @Override
    public ServiceResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String diaryId = req.getParameter("diaryId");
        String diaryName = req.getParameter("diaryName");
        String diaryText = req.getParameter("diaryText");
        String dateTime = DateTimeUtil.getInstance().getCurrentTime();

        logger.debug("diary id:{} name:{} text:{} date:{}", diaryId, diaryName, diaryText, dateTime);
        assert diaryId != null;

        Diary diary = new Diary();
        diary.setId(Integer.valueOf(diaryId));
        diary.setDiaryName(diaryName);
        diary.setdiaryText(diaryText);
        diary.setdiaryGmtModified(dateTime);

        updateDiary(diary);
        return new ServiceResult.Builder(true).page(Page.PAGE_USERHOME).build();
    }

    @Override
    public void updateDiary(Diary diary) {
        try {
            dao.updateDiary(diary);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
