package com.amc.mapper;

import com.amc.web.domain.ErrorConfig;
import com.amc.web.domain.maptype.HourDataType;
import com.amc.web.domain.maptype.MinuteDataType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ErrorMapper {

    /**
     * 错误上报
     *
     * @param errorConfig
     * @return
     */
    int save(ErrorConfig errorConfig);


    List<ErrorConfig> list(@Param("pid") String pid, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<ErrorConfig> listByType(@Param("pid") String pid, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("type") String type);

    ErrorConfig getErrorConfigById(@Param("id") String id);

    // 这个错误不用管
    // @MapKey("id")
    // 默认是以列名为key
    // https://www.bmabk.com/index.php/post/110716.html
    // 你可以根据这个文章,来关闭这种错误提示
    Map<String, Integer> selectErrorCountByNum(@Param("pid") String pid, @Param("errorMsg") String errorMsg, @Param("errorId") String errorId);

    List<HourDataType> selectErrorCountListByHour(@Param("pid") String pid,
                                                  @Param("day") String day,
                                                  @Param("errorMsg") String errorMsg);

    List<MinuteDataType> selectErrorCountListByMinute(@Param("pid") String pid,
                                                      @Param("errorMsg") String errorMsg,
                                                      @Param("day") String day);
}
