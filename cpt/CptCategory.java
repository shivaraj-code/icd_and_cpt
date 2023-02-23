package com.kavanant.codesystem.cpt;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class CptCategory {

	@Id
	public int id;
	public String code;
	public String short_name;
	public String description;
	public Integer ishcpcs;
	public Integer ref_id;
	public String retired;
	public String version_state;
	public Integer original_ref_id;
	public Integer created_by;
	public Date created_date;
	public Integer modified_by;
	public Date modified_date;
	public Date retired_on;
	public String retired_reason;
	public String cptmajorcategory;
	public String cptminorcategory;

}
