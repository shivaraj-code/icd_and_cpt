package com.kavanant.codesystem.icd10;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "icd10_codes")
public class Icd10Code {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;

	@Column(name = "icd_order")
	public String icdOrder;

	@Column(name = "icd_code")
	public String icdCode;

	@Column(name = "icd_id")
	public Integer icdId;

	@Column(name = "type")
	public Character type;

	@Column(name = "short_desc")
	public String shortDesc;

	@Column(name = "med_desc")
	public String medDesc;

	@Column(name = "long_desc")
	public String longDesc;

	@Column(name = "ref_id", nullable = true)
	public Integer refId;

	@Column(name = "retired", nullable = true)
	public String retired;

	@Column(name = "version_state", nullable = true)
	public String versionState;

	@Column(name = "original_ref_id", nullable = true)
	public Integer originalRefId;

	@Column(name = "created_by", nullable = true)
	public Long createdBy;

	@Column(name = "created_date", nullable = true)
	public Date createdDate;

	@Column(name = "modified_by", nullable = true)
	public Long modifiedBy;

	@Column(name = "modified_date", nullable = true)
	public Date modifiedDate;

	@Column(name = "retired_on", nullable = true)
	public Date retiredOn;

	@Column(name = "retired_reason", nullable = true)
	public String retiredReason;
}
