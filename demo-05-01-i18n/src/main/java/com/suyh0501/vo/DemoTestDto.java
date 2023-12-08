package com.suyh0501.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class DemoTestDto {
	@NotNull
	private Long id;

	private Date updateDate;
}