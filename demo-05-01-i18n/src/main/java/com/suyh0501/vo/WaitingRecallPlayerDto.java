package com.suyh0501.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class WaitingRecallPlayerDto implements Serializable {
    private static final long serialVersionUID = 1L;

	@NotNull
	private Long id;

	private String projectId;

	private String mobile;

	private BigDecimal physicalRewards;

	private BigDecimal virtualRewards;

	private Integer priority;

	private String link;

	private Long employeeUserId;

	private Date taskDate;

	private Long resultRecordId;

	private Long creator;

	private Date createDate;

	private Long updater;

	private Date updateDate;


}