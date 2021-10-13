package com.lancoo.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "Lgdb_ResCatalog_Private")
public class LgdbRescatalogPrivate {

	@Id
	@Column(name = "FIXED_ID")
	private String fixedId;

	@Column(name = "QUESTION_TYPE_CODE")
	private String questionTypeCode;

	@Column(name = "QUES_CONTENT")
	private String quesContent;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "CREATE_USER")
	private String createUser;

	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	@Column(name = "SUBJECT")
	private String subject;

	@Column(name = "DIFFCULT")
	private String diffcult;

	@Column(name = "IMPOR_KN_CODE")
	private String imporKnCode;

	@Column(name = "IMPOR_KN_TEXT")
	private String imporKnText;

	@Column(name = "FILE_PATH")
	private String filePath;
	// 试题状态（0未提交；1已提交待审核；2驳回；3通过）
	@Column(name = "QUES_STATUS")
	private Integer quesStatus;

	@Column(name = "REJECTED_REASON")
	private String rejectedReason;

	@Column(name = "MediaUrl")
	private String mediaurl;

	/**
	 * @return FIXED_ID
	 */
	public String getFixedId() {
		return fixedId;
	}

	/**
	 * @param fixedId
	 */
	public void setFixedId(String fixedId) {
		this.fixedId = fixedId;
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
	 * @return CREATE_DATE
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return CREATE_USER
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return UPDATE_DATE
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return SUBJECT
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return DIFFCULT
	 */
	public String getDiffcult() {
		return diffcult;
	}

	/**
	 * @param diffcult
	 */
	public void setDiffcult(String diffcult) {
		this.diffcult = diffcult;
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
	 * @return QUES_STATUS
	 */
	public Integer getQuesStatus() {
		return quesStatus;
	}

	/**
	 * @param quesStatus
	 */
	public void setQuesStatus(Integer quesStatus) {
		this.quesStatus = quesStatus;
	}

	public String getRejectedReason() {
		return rejectedReason;
	}

	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}

	public String getMediaurl() {
		return mediaurl;
	}

	public void setMediaurl(String mediaurl) {
		this.mediaurl = mediaurl;
	}
}