package com.olx.entity;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "olxBlacklistToken")
public class BlacklistTokenDocument {

	@Id
	private ObjectId _id;
	private String token;

	public BlacklistTokenDocument() {
		// TODO Auto-generated constructor stub
	}

	public BlacklistTokenDocument(ObjectId _id, String token) {
		super();
		this._id = _id;
		this.token = token;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "BlacklistTokenDocument [_id=" + _id + ", token=" + token + "]";
	}

}
