package com.lancoo.pojo;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "Lgdb_ResView_CB_SubVerQuesView")
public class LgdbResviewCbSubverquesview {
    @Column(name = "RESOURCE_ID")
    private String resourceId;

    @Column(name = "RESOURCE_NAME")
    private String resourceName;

    @Column(name = "RESOURCE_TYPE")
    private Long resourceType;

    @Column(name = "RESOURCE_LEVEL")
    private String resourceLevel;

    @Column(name = "STORE_DATE")
    private Date storeDate;

    @Column(name = "THEME_CODE")
    private String themeCode;

    @Column(name = "THEME_KERWORD_CODE")
    private String themeKerwordCode;

    @Column(name = "THEME_KERWORD_TEXT")
    private String themeKerwordText;

    @Column(name = "UPPER_KNLG_CODE")
    private String upperKnlgCode;

    @Column(name = "UPPER_KNLG_TEXT")
    private String upperKnlgText;

    @Column(name = "SUB_QUESTION_KN_CODE")
    private String subQuestionKnCode;

    @Column(name = "SUB_QUESTION_KN_MULTIPLE")
    private String subQuestionKnMultiple;

    @Column(name = "SUB_QUESTION_KN_TEXT")
    private String subQuestionKnText;

    @Column(name = "SUB_QUESTION_KN_UNIQUE")
    private String subQuestionKnUnique;

    @Column(name = "IMPOR_KN_UNIQUE")
    private String imporKnUnique;

    @Column(name = "MAIN_KN_UNIQUE")
    private String mainKnUnique;

    @Column(name = "TOPIC_UNIQUE")
    private String topicUnique;

    @Column(name = "THEME_TEXT")
    private String themeText;

    @Column(name = "IMPOR_KN_CODE")
    private String imporKnCode;

    @Column(name = "IMPOR_KN_TEXT")
    private String imporKnText;

    @Column(name = "MAIN_KN_CODE")
    private String mainKnCode;

    @Column(name = "MAIN_KN_TEXT")
    private String mainKnText;

    @Column(name = "TOPIC_CODE")
    private String topicCode;

    @Column(name = "TOPIC_TEXT")
    private String topicText;

    @Column(name = "RELATED_KNOWLEDGE_CODE")
    private String relatedKnowledgeCode;

    @Column(name = "RELATED_KNOWLEDGE_TEXT")
    private String relatedKnowledgeText;

    @Column(name = "THEMATIC_KNOWLEDGE_CODE")
    private String thematicKnowledgeCode;

    @Column(name = "THEMATIC_KNOWLEDGE_TEXT")
    private String thematicKnowledgeText;

    @Column(name = "THEMATIC_KN_MULTIPLE")
    private String thematicKnMultiple;

    @Column(name = "UNIT_NUM")
    private Long unitNum;

    @Column(name = "RESOURCE_SIZE")
    private BigDecimal resourceSize;

    @Column(name = "IS_EXPORT")
    private Integer isExport;

    @Column(name = "IS_CHECK_REPEATE")
    private Integer isCheckRepeate;

    @Column(name = "AUDIO_TEXT_CONTENT")
    private String audioTextContent;

    @Column(name = "GUID")
    private String guid;

    @Column(name = "IS_UPDATE")
    private Integer isUpdate;

    @Column(name = "SOURCE_LIBRARY")
    private String sourceLibrary;

    @Column(name = "RESOURCE_CLASS")
    private String resourceClass;

    @Column(name = "MD5_CODE")
    private String md5Code;

    @Column(name = "INSTITU_UNIT")
    private String instituUnit;

    @Column(name = "RES_LENGTH")
    private Long resLength;

    @Column(name = "DURATION_LENGTH")
    private BigDecimal durationLength;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "QUES_CONTENT")
    private String quesContent;

    @Column(name = "QUES_CONTENT_ORIGINAL")
    private String quesContentOriginal;

    @Column(name = "IS_EXSIT_MEDIA")
    private Long isExsitMedia;

    @Column(name = "DOWNLOAD_FLAG")
    private Long downloadFlag;

    @Column(name = "SEQUENCE")
    private Long sequence;

    @Column(name = "HEAT_NUM")
    private BigDecimal heatNum;

    @Column(name = "RERELIABILITY_LEVEL")
    private BigDecimal rereliabilityLevel;

    @Column(name = "RERELIABILITY_GRADE")
    private BigDecimal rereliabilityGrade;

    @Column(name = "DIFFCULTY_LEVEL")
    private BigDecimal diffcultyLevel;

    @Column(name = "DIFFCULTY_GRADE")
    private BigDecimal diffcultyGrade;

    @Column(name = "ABANDON_NUM")
    private Long abandonNum;

    @Column(name = "ABANDON_RATE")
    private BigDecimal abandonRate;

    @Column(name = "DISCRI_LEVEL")
    private BigDecimal discriLevel;

    @Column(name = "DISCRI_GRADE")
    private BigDecimal discriGrade;

    @Column(name = "LISTENING_GENRE")
    private Long listeningGenre;

    @Column(name = "LISTENING_NUM")
    private Long listeningNum;

    @Column(name = "LISTENING_LENGTH")
    private BigDecimal listeningLength;

    @Column(name = "IS_USEED")
    private Boolean isUseed;

    @Column(name = "QUESTION_TYPE_CODE")
    private String questionTypeCode;

    @Column(name = "QUES_CHILD_NUM")
    private Long quesChildNum;

    @Column(name = "QUES_CHILD_COUNT")
    private Long quesChildCount;

    @Column(name = "SENSITOMETRY_GRADE")
    private BigDecimal sensitometryGrade;

    @Column(name = "IS_DIFFICULT_QUES")
    private Integer isDifficultQues;

    @Column(name = "MEDIA_URL")
    private String mediaUrl;

    @Column(name = "SUB_OBJ")
    private Long subObj;

    @Column(name = "APPLY_TOTAL_TIME")
    private BigDecimal applyTotalTime;

    @Column(name = "SUBJECT_CODE")
    private String subjectCode;

    @Column(name = "ANS_TYPE")
    private Long ansType;

    @Column(name = "FILE_CONTENT")
    private String fileContent;

    /**
     * @return RESOURCE_ID
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * @return RESOURCE_NAME
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * @return RESOURCE_TYPE
     */
    public Long getResourceType() {
        return resourceType;
    }

    /**
     * @param resourceType
     */
    public void setResourceType(Long resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * @return RESOURCE_LEVEL
     */
    public String getResourceLevel() {
        return resourceLevel;
    }

    /**
     * @param resourceLevel
     */
    public void setResourceLevel(String resourceLevel) {
        this.resourceLevel = resourceLevel;
    }

    /**
     * @return STORE_DATE
     */
    public Date getStoreDate() {
        return storeDate;
    }

    /**
     * @param storeDate
     */
    public void setStoreDate(Date storeDate) {
        this.storeDate = storeDate;
    }

    /**
     * @return THEME_CODE
     */
    public String getThemeCode() {
        return themeCode;
    }

    /**
     * @param themeCode
     */
    public void setThemeCode(String themeCode) {
        this.themeCode = themeCode;
    }

    /**
     * @return THEME_KERWORD_CODE
     */
    public String getThemeKerwordCode() {
        return themeKerwordCode;
    }

    /**
     * @param themeKerwordCode
     */
    public void setThemeKerwordCode(String themeKerwordCode) {
        this.themeKerwordCode = themeKerwordCode;
    }

    /**
     * @return THEME_KERWORD_TEXT
     */
    public String getThemeKerwordText() {
        return themeKerwordText;
    }

    /**
     * @param themeKerwordText
     */
    public void setThemeKerwordText(String themeKerwordText) {
        this.themeKerwordText = themeKerwordText;
    }

    /**
     * @return UPPER_KNLG_CODE
     */
    public String getUpperKnlgCode() {
        return upperKnlgCode;
    }

    /**
     * @param upperKnlgCode
     */
    public void setUpperKnlgCode(String upperKnlgCode) {
        this.upperKnlgCode = upperKnlgCode;
    }

    /**
     * @return UPPER_KNLG_TEXT
     */
    public String getUpperKnlgText() {
        return upperKnlgText;
    }

    /**
     * @param upperKnlgText
     */
    public void setUpperKnlgText(String upperKnlgText) {
        this.upperKnlgText = upperKnlgText;
    }

    /**
     * @return SUB_QUESTION_KN_CODE
     */
    public String getSubQuestionKnCode() {
        return subQuestionKnCode;
    }

    /**
     * @param subQuestionKnCode
     */
    public void setSubQuestionKnCode(String subQuestionKnCode) {
        this.subQuestionKnCode = subQuestionKnCode;
    }

    /**
     * @return SUB_QUESTION_KN_MULTIPLE
     */
    public String getSubQuestionKnMultiple() {
        return subQuestionKnMultiple;
    }

    /**
     * @param subQuestionKnMultiple
     */
    public void setSubQuestionKnMultiple(String subQuestionKnMultiple) {
        this.subQuestionKnMultiple = subQuestionKnMultiple;
    }

    /**
     * @return SUB_QUESTION_KN_TEXT
     */
    public String getSubQuestionKnText() {
        return subQuestionKnText;
    }

    /**
     * @param subQuestionKnText
     */
    public void setSubQuestionKnText(String subQuestionKnText) {
        this.subQuestionKnText = subQuestionKnText;
    }

    /**
     * @return SUB_QUESTION_KN_UNIQUE
     */
    public String getSubQuestionKnUnique() {
        return subQuestionKnUnique;
    }

    /**
     * @param subQuestionKnUnique
     */
    public void setSubQuestionKnUnique(String subQuestionKnUnique) {
        this.subQuestionKnUnique = subQuestionKnUnique;
    }

    /**
     * @return IMPOR_KN_UNIQUE
     */
    public String getImporKnUnique() {
        return imporKnUnique;
    }

    /**
     * @param imporKnUnique
     */
    public void setImporKnUnique(String imporKnUnique) {
        this.imporKnUnique = imporKnUnique;
    }

    /**
     * @return MAIN_KN_UNIQUE
     */
    public String getMainKnUnique() {
        return mainKnUnique;
    }

    /**
     * @param mainKnUnique
     */
    public void setMainKnUnique(String mainKnUnique) {
        this.mainKnUnique = mainKnUnique;
    }

    /**
     * @return TOPIC_UNIQUE
     */
    public String getTopicUnique() {
        return topicUnique;
    }

    /**
     * @param topicUnique
     */
    public void setTopicUnique(String topicUnique) {
        this.topicUnique = topicUnique;
    }

    /**
     * @return THEME_TEXT
     */
    public String getThemeText() {
        return themeText;
    }

    /**
     * @param themeText
     */
    public void setThemeText(String themeText) {
        this.themeText = themeText;
    }

    /**
     * @return IMPOR_KN_CODE
     */
    public String getImporKnCode() {
        return imporKnCode;
    }

    /**
     * @param imporKnCode
     */
    public void setImporKnCode(String imporKnCode) {
        this.imporKnCode = imporKnCode;
    }

    /**
     * @return IMPOR_KN_TEXT
     */
    public String getImporKnText() {
        return imporKnText;
    }

    /**
     * @param imporKnText
     */
    public void setImporKnText(String imporKnText) {
        this.imporKnText = imporKnText;
    }

    /**
     * @return MAIN_KN_CODE
     */
    public String getMainKnCode() {
        return mainKnCode;
    }

    /**
     * @param mainKnCode
     */
    public void setMainKnCode(String mainKnCode) {
        this.mainKnCode = mainKnCode;
    }

    /**
     * @return MAIN_KN_TEXT
     */
    public String getMainKnText() {
        return mainKnText;
    }

    /**
     * @param mainKnText
     */
    public void setMainKnText(String mainKnText) {
        this.mainKnText = mainKnText;
    }

    /**
     * @return TOPIC_CODE
     */
    public String getTopicCode() {
        return topicCode;
    }

    /**
     * @param topicCode
     */
    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    /**
     * @return TOPIC_TEXT
     */
    public String getTopicText() {
        return topicText;
    }

    /**
     * @param topicText
     */
    public void setTopicText(String topicText) {
        this.topicText = topicText;
    }

    /**
     * @return RELATED_KNOWLEDGE_CODE
     */
    public String getRelatedKnowledgeCode() {
        return relatedKnowledgeCode;
    }

    /**
     * @param relatedKnowledgeCode
     */
    public void setRelatedKnowledgeCode(String relatedKnowledgeCode) {
        this.relatedKnowledgeCode = relatedKnowledgeCode;
    }

    /**
     * @return RELATED_KNOWLEDGE_TEXT
     */
    public String getRelatedKnowledgeText() {
        return relatedKnowledgeText;
    }

    /**
     * @param relatedKnowledgeText
     */
    public void setRelatedKnowledgeText(String relatedKnowledgeText) {
        this.relatedKnowledgeText = relatedKnowledgeText;
    }

    /**
     * @return THEMATIC_KNOWLEDGE_CODE
     */
    public String getThematicKnowledgeCode() {
        return thematicKnowledgeCode;
    }

    /**
     * @param thematicKnowledgeCode
     */
    public void setThematicKnowledgeCode(String thematicKnowledgeCode) {
        this.thematicKnowledgeCode = thematicKnowledgeCode;
    }

    /**
     * @return THEMATIC_KNOWLEDGE_TEXT
     */
    public String getThematicKnowledgeText() {
        return thematicKnowledgeText;
    }

    /**
     * @param thematicKnowledgeText
     */
    public void setThematicKnowledgeText(String thematicKnowledgeText) {
        this.thematicKnowledgeText = thematicKnowledgeText;
    }

    /**
     * @return THEMATIC_KN_MULTIPLE
     */
    public String getThematicKnMultiple() {
        return thematicKnMultiple;
    }

    /**
     * @param thematicKnMultiple
     */
    public void setThematicKnMultiple(String thematicKnMultiple) {
        this.thematicKnMultiple = thematicKnMultiple;
    }

    /**
     * @return UNIT_NUM
     */
    public Long getUnitNum() {
        return unitNum;
    }

    /**
     * @param unitNum
     */
    public void setUnitNum(Long unitNum) {
        this.unitNum = unitNum;
    }

    /**
     * @return RESOURCE_SIZE
     */
    public BigDecimal getResourceSize() {
        return resourceSize;
    }

    /**
     * @param resourceSize
     */
    public void setResourceSize(BigDecimal resourceSize) {
        this.resourceSize = resourceSize;
    }

    /**
     * @return IS_EXPORT
     */
    public Integer getIsExport() {
        return isExport;
    }

    /**
     * @param isExport
     */
    public void setIsExport(Integer isExport) {
        this.isExport = isExport;
    }

    /**
     * @return IS_CHECK_REPEATE
     */
    public Integer getIsCheckRepeate() {
        return isCheckRepeate;
    }

    /**
     * @param isCheckRepeate
     */
    public void setIsCheckRepeate(Integer isCheckRepeate) {
        this.isCheckRepeate = isCheckRepeate;
    }

    /**
     * @return AUDIO_TEXT_CONTENT
     */
    public String getAudioTextContent() {
        return audioTextContent;
    }

    /**
     * @param audioTextContent
     */
    public void setAudioTextContent(String audioTextContent) {
        this.audioTextContent = audioTextContent;
    }

    /**
     * @return GUID
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return IS_UPDATE
     */
    public Integer getIsUpdate() {
        return isUpdate;
    }

    /**
     * @param isUpdate
     */
    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    /**
     * @return SOURCE_LIBRARY
     */
    public String getSourceLibrary() {
        return sourceLibrary;
    }

    /**
     * @param sourceLibrary
     */
    public void setSourceLibrary(String sourceLibrary) {
        this.sourceLibrary = sourceLibrary;
    }

    /**
     * @return RESOURCE_CLASS
     */
    public String getResourceClass() {
        return resourceClass;
    }

    /**
     * @param resourceClass
     */
    public void setResourceClass(String resourceClass) {
        this.resourceClass = resourceClass;
    }

    /**
     * @return MD5_CODE
     */
    public String getMd5Code() {
        return md5Code;
    }

    /**
     * @param md5Code
     */
    public void setMd5Code(String md5Code) {
        this.md5Code = md5Code;
    }

    /**
     * @return INSTITU_UNIT
     */
    public String getInstituUnit() {
        return instituUnit;
    }

    /**
     * @param instituUnit
     */
    public void setInstituUnit(String instituUnit) {
        this.instituUnit = instituUnit;
    }

    /**
     * @return RES_LENGTH
     */
    public Long getResLength() {
        return resLength;
    }

    /**
     * @param resLength
     */
    public void setResLength(Long resLength) {
        this.resLength = resLength;
    }

    /**
     * @return DURATION_LENGTH
     */
    public BigDecimal getDurationLength() {
        return durationLength;
    }

    /**
     * @param durationLength
     */
    public void setDurationLength(BigDecimal durationLength) {
        this.durationLength = durationLength;
    }

    /**
     * @return FILE_PATH
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return QUES_CONTENT
     */
    public String getQuesContent() {
        return quesContent;
    }

    /**
     * @param quesContent
     */
    public void setQuesContent(String quesContent) {
        this.quesContent = quesContent;
    }

    /**
     * @return QUES_CONTENT_ORIGINAL
     */
    public String getQuesContentOriginal() {
        return quesContentOriginal;
    }

    /**
     * @param quesContentOriginal
     */
    public void setQuesContentOriginal(String quesContentOriginal) {
        this.quesContentOriginal = quesContentOriginal;
    }

    /**
     * @return IS_EXSIT_MEDIA
     */
    public Long getIsExsitMedia() {
        return isExsitMedia;
    }

    /**
     * @param isExsitMedia
     */
    public void setIsExsitMedia(Long isExsitMedia) {
        this.isExsitMedia = isExsitMedia;
    }

    /**
     * @return DOWNLOAD_FLAG
     */
    public Long getDownloadFlag() {
        return downloadFlag;
    }

    /**
     * @param downloadFlag
     */
    public void setDownloadFlag(Long downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    /**
     * @return SEQUENCE
     */
    public Long getSequence() {
        return sequence;
    }

    /**
     * @param sequence
     */
    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    /**
     * @return HEAT_NUM
     */
    public BigDecimal getHeatNum() {
        return heatNum;
    }

    /**
     * @param heatNum
     */
    public void setHeatNum(BigDecimal heatNum) {
        this.heatNum = heatNum;
    }

    /**
     * @return RERELIABILITY_LEVEL
     */
    public BigDecimal getRereliabilityLevel() {
        return rereliabilityLevel;
    }

    /**
     * @param rereliabilityLevel
     */
    public void setRereliabilityLevel(BigDecimal rereliabilityLevel) {
        this.rereliabilityLevel = rereliabilityLevel;
    }

    /**
     * @return RERELIABILITY_GRADE
     */
    public BigDecimal getRereliabilityGrade() {
        return rereliabilityGrade;
    }

    /**
     * @param rereliabilityGrade
     */
    public void setRereliabilityGrade(BigDecimal rereliabilityGrade) {
        this.rereliabilityGrade = rereliabilityGrade;
    }

    /**
     * @return DIFFCULTY_LEVEL
     */
    public BigDecimal getDiffcultyLevel() {
        return diffcultyLevel;
    }

    /**
     * @param diffcultyLevel
     */
    public void setDiffcultyLevel(BigDecimal diffcultyLevel) {
        this.diffcultyLevel = diffcultyLevel;
    }

    /**
     * @return DIFFCULTY_GRADE
     */
    public BigDecimal getDiffcultyGrade() {
        return diffcultyGrade;
    }

    /**
     * @param diffcultyGrade
     */
    public void setDiffcultyGrade(BigDecimal diffcultyGrade) {
        this.diffcultyGrade = diffcultyGrade;
    }

    /**
     * @return ABANDON_NUM
     */
    public Long getAbandonNum() {
        return abandonNum;
    }

    /**
     * @param abandonNum
     */
    public void setAbandonNum(Long abandonNum) {
        this.abandonNum = abandonNum;
    }

    /**
     * @return ABANDON_RATE
     */
    public BigDecimal getAbandonRate() {
        return abandonRate;
    }

    /**
     * @param abandonRate
     */
    public void setAbandonRate(BigDecimal abandonRate) {
        this.abandonRate = abandonRate;
    }

    /**
     * @return DISCRI_LEVEL
     */
    public BigDecimal getDiscriLevel() {
        return discriLevel;
    }

    /**
     * @param discriLevel
     */
    public void setDiscriLevel(BigDecimal discriLevel) {
        this.discriLevel = discriLevel;
    }

    /**
     * @return DISCRI_GRADE
     */
    public BigDecimal getDiscriGrade() {
        return discriGrade;
    }

    /**
     * @param discriGrade
     */
    public void setDiscriGrade(BigDecimal discriGrade) {
        this.discriGrade = discriGrade;
    }

    /**
     * @return LISTENING_GENRE
     */
    public Long getListeningGenre() {
        return listeningGenre;
    }

    /**
     * @param listeningGenre
     */
    public void setListeningGenre(Long listeningGenre) {
        this.listeningGenre = listeningGenre;
    }

    /**
     * @return LISTENING_NUM
     */
    public Long getListeningNum() {
        return listeningNum;
    }

    /**
     * @param listeningNum
     */
    public void setListeningNum(Long listeningNum) {
        this.listeningNum = listeningNum;
    }

    /**
     * @return LISTENING_LENGTH
     */
    public BigDecimal getListeningLength() {
        return listeningLength;
    }

    /**
     * @param listeningLength
     */
    public void setListeningLength(BigDecimal listeningLength) {
        this.listeningLength = listeningLength;
    }

    /**
     * @return IS_USEED
     */
    public Boolean getIsUseed() {
        return isUseed;
    }

    /**
     * @param isUseed
     */
    public void setIsUseed(Boolean isUseed) {
        this.isUseed = isUseed;
    }

    /**
     * @return QUESTION_TYPE_CODE
     */
    public String getQuestionTypeCode() {
        return questionTypeCode;
    }

    /**
     * @param questionTypeCode
     */
    public void setQuestionTypeCode(String questionTypeCode) {
        this.questionTypeCode = questionTypeCode;
    }

    /**
     * @return QUES_CHILD_NUM
     */
    public Long getQuesChildNum() {
        return quesChildNum;
    }

    /**
     * @param quesChildNum
     */
    public void setQuesChildNum(Long quesChildNum) {
        this.quesChildNum = quesChildNum;
    }

    /**
     * @return QUES_CHILD_COUNT
     */
    public Long getQuesChildCount() {
        return quesChildCount;
    }

    /**
     * @param quesChildCount
     */
    public void setQuesChildCount(Long quesChildCount) {
        this.quesChildCount = quesChildCount;
    }

    /**
     * @return SENSITOMETRY_GRADE
     */
    public BigDecimal getSensitometryGrade() {
        return sensitometryGrade;
    }

    /**
     * @param sensitometryGrade
     */
    public void setSensitometryGrade(BigDecimal sensitometryGrade) {
        this.sensitometryGrade = sensitometryGrade;
    }

    /**
     * @return IS_DIFFICULT_QUES
     */
    public Integer getIsDifficultQues() {
        return isDifficultQues;
    }

    /**
     * @param isDifficultQues
     */
    public void setIsDifficultQues(Integer isDifficultQues) {
        this.isDifficultQues = isDifficultQues;
    }

    /**
     * @return MEDIA_URL
     */
    public String getMediaUrl() {
        return mediaUrl;
    }

    /**
     * @param mediaUrl
     */
    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    /**
     * @return SUB_OBJ
     */
    public Long getSubObj() {
        return subObj;
    }

    /**
     * @param subObj
     */
    public void setSubObj(Long subObj) {
        this.subObj = subObj;
    }

    /**
     * @return APPLY_TOTAL_TIME
     */
    public BigDecimal getApplyTotalTime() {
        return applyTotalTime;
    }

    /**
     * @param applyTotalTime
     */
    public void setApplyTotalTime(BigDecimal applyTotalTime) {
        this.applyTotalTime = applyTotalTime;
    }

    /**
     * @return SUBJECT_CODE
     */
    public String getSubjectCode() {
        return subjectCode;
    }

    /**
     * @param subjectCode
     */
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    /**
     * @return ANS_TYPE
     */
    public Long getAnsType() {
        return ansType;
    }

    /**
     * @param ansType
     */
    public void setAnsType(Long ansType) {
        this.ansType = ansType;
    }

    /**
     * @return FILE_CONTENT
     */
    public String getFileContent() {
        return fileContent;
    }

    /**
     * @param fileContent
     */
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
}