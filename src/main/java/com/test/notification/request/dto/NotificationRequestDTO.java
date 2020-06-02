package com.test.notification.request.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRequestDTO implements Serializable {

	private static final long serialVersionUID = 2233758122215768642L;
	private String messageBody;
	private String subject;// Optional if text Notification
	private String sendToId; // User Id

	
	@Override
	public String toString() {
		return this.sendToId+","+this.messageBody+","+this.subject;
	}
}