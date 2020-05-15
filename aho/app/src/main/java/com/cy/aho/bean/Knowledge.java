package com.cy.aho.bean;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Knowledge {
	@PrimaryKey
	@NonNull
	private String id;
	private String title;
	private String typeId;
	private String content;
	private String upDateTime;

	public Knowledge(){}

	@NonNull
	public String getId() {
		return id;
	}

	public void setId(@NonNull String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUpDateTime() {
		return upDateTime;
	}

	public void setUpDateTime(String upDateTime) {
		this.upDateTime = upDateTime;
	}
}
