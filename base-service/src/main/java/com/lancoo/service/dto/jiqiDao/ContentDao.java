package com.lancoo.service.dto.jiqiDao;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author KCWang
 * @date 2021/8/3 17:31
 * @Email:KCWang@aliyun.com
 */

@Slf4j
@Data
public class ContentDao {

    public static String TYPE_A = "<TContent>" +
            "<Quesbody />" +
            "<QuesArticle audio=\"\" orgname=\"\" time=\"\" />" +
            "<QuesChild index=\"1\" answertype=\"1\">" +
            "<QueStem position=\"0\" />" +
            "<QuesOptionAsk audio=\"\" orgname=\"\"></QuesOptionAsk>" +
            "<QuesAnswer></QuesAnswer>" +
            "<QuesAnalyze></QuesAnalyze>" +
            "</QuesChild>" +
            "</TContent>";
    public static String TYPE_N = "<TContent>" +
            "<Quesbody />" +
            "<QuesArticle audio=\"\" orgname=\"\" time=\"\" />" +
            "<QuesChild index=\"1\" answertype=\"2\">" +
            "<QueStem position=\"0\" />" +
            "<QuesOptionAsk audio=\"\" orgname=\"\"></QuesOptionAsk>" +
            "<QuesAnswer></QuesAnswer>" +
            "<QuesAnalyze />" +
            "<QuesOption />" +
            "</QuesChild>" +
            "</TContent>";
    public static String TYPE_M = "<TContent>" +
            "<Quesbody />" +
            "<QuesArticle audio=\"\" orgname=\"\" time=\"\" />" +
            "<QuesChild index=\"1\" answertype=\"32\">" +
            "<QueStem position=\"0\" />" +
            "<QuesOptionAsk audio=\"\" orgname=\"\"></QuesOptionAsk>" +
            "<QuesAnswer></QuesAnswer>" +
            "<QuesAnalyze></QuesAnalyze>" +
            "</QuesChild>" +
            "</TContent>";
    public static String TYPE_G = "<TContent><Quesbody /><QuesArticle audio=\"\" orgname=\"\" time=\"\" />" +
            "<QuesChild index=\"1\" answertype=\"4\">" +
            "<QueStem position=\"0\" />" +
            "<QuesOptionAsk audio=\"\" orgname=\"\">" +
            "</QuesOptionAsk>" +
            "<QuesAnswer></QuesAnswer>" +
            "<QuesAnalyze></QuesAnalyze>" +
            "<QuesOption />" +
            "</QuesChild>" +
            "</TContent>";
    public static String TYPE_V = "<TContent><Quesbody /><QuesArticle audio=\"\" orgname=\"\" time=\"\" />" +
            "<QuesChild index=\"1\" answertype=\"4\">" +
            "<QueStem position=\"0\" />" +
            "<QuesOptionAsk audio=\"\" orgname=\"\">" +
            "</QuesOptionAsk>" +
            "<QuesAnswer></QuesAnswer>" +
            "<QuesAnalyze></QuesAnalyze>" +
            "<QuesOption />" +
            "</QuesChild>" +
            "</TContent>";
}
