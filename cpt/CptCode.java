package com.kavanant.codesystem.cpt;

//import java.sql.Timestamp;
import java.util.Date;
//import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name = "cpt_short")
public class CptCode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;

	@Column(name = "code")
	public String code;

	@Column(name = "short_name")
	public String shortName;

	@Column(name = "description")
	public String description;

	@Column(name = "ishcpcs")
	public Integer isHcpcs;

	@Column(name = "ref_id", nullable = true)
	public Integer refId;

	@Column(name = "retired", nullable = true)
	public String retired;

	@Column(name = "version_state", nullable = true)
	public String versionState;

	@Column(name = "original_ref_id", nullable = true)
	public Integer originalRefId;

	@Column(name = "created_by", nullable = true)
	public Integer createdBy;

	// @CreationTimestamp
	// @Temporal(TemporalType.DATE)
	// @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = true)
	public Date createdDate;

	@Column(name = "modified_by", nullable = true)
	public Integer modifiedBy;

	// @CreationTimestamp
	@Column(name = "modified_date", nullable = true)
	public Date modifiedDate;

	@Column(name = "retired_on", nullable = true)
	public Date retiredOn;

	@Column(name = "retired_reason", nullable = true)
	public String retiredReason;
}
