package com.wang.service.impl;

import com.wang.bean.doo.DiaryDO;
import com.wang.bean.dto.DiaryDTO;
import com.wang.constant.Page;
import com.wang.dao.DAOFactory;
import com.wang.dao.DiaryDAO;
import com.wang.dao.jdbcimpl.JdbcDAOFactory;
import com.wang.service.DiaryListQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 日记数据集获取
 * <p>
 * 失败 Page.PAGE_USERHOME
 * 成功 Page.PAGE_DIARYSHOW
 *
 * @date 2018/5/24
 * @auther ten
 */
public class DiaryListQueryServiceImpl implements DiaryListQueryService {

    private Logger logger = LoggerFactory.getLogger(DiaryListQueryServiceImpl.class);

    public DiaryListQueryServiceImpl(){

    }

    @Override
    public ServiceResult execute(HttpServletRequest req, HttpServletResponse resp) {

//        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
//        Integer userId = userDTO.getUserId();

        Integer userId = 1;

        // 获取DAO实例
        DAOFactory factory = new JdbcDAOFactory();
        DiaryDAO dao = (DiaryDAO) factory.getDaoByTableName("diary");

        List<DiaryDO> diaryDOList = null;
        boolean success = false;
        try {
            diaryDOList = dao.queryDiaryList(userId);
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!success) {
            return new ServiceResult.Builder(false)
                    .errormsg("数据库查询异常")
                    .page(Page.PAGE_USERHOME)
                    .build();
        }

        // List<DiaryDTO>
        List<DiaryDTO> diaryList = new ArrayList<>(16);
        for (DiaryDO diaryDO : diaryDOList) {
            DiaryDTO diaryDTO = new DiaryDTO(
                    diaryDO.getDiaryId(),
                    diaryDO.getDiaryName(),
                    diaryDO.getdiaryText(),
                    diaryDO.getdiaryGmtModified());
            diaryList.add(diaryDTO);
        }

        req.getSession().setAttribute("diaryList", diaryList);
        logger.info("diary list query success");
        return new ServiceResult.Builder(true)
                .page(Page.PAGE_USERHOME)
                .build();
    }

    @Override
    public List<DiaryDO> queryDiaryListByUserId(int userId) {
        return null;
    }
}
