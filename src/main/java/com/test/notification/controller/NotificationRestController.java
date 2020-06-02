package com.test.notification.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.notification.config.AppPropertiesConfig;
import com.test.notification.redis.repo.RedisUtil;
import com.test.notification.request.dto.NotificationRequestDTO;
import com.test.notification.response.dto.ResponseBase;
import com.test.notification.util.AppConstants;

import lombok.extern.log4j.Log4j2;

/* 
 * Notification API
 * 
 * Send Notification 
 * POST
 * http://localhost:8086/v1/notification/send-notification
 * 
 * 
 */
@RestController
@RequestMapping(AppConstants.NOTIFICATION)
@Log4j2
public class NotificationRestController {

	@Autowired
	private RedisUtil<String> redisUtil;
	
	@Autowired
	private AppPropertiesConfig config;

	
	@PostMapping(path = "/send-notification", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBase sendNotification(@RequestBody(required = true) NotificationRequestDTO request) {
		//Send to middleware
		ResponseBase response=null;
		try {
		redisUtil.pushToQueue(config.getQueue(), request.toString());
		response=ResponseBase.builder().httpStatus(HttpStatus.ACCEPTED).build();//Default acceptance message
		}catch(Exception e) {
			log.error("Could not send Message ",e);
			response=ResponseBase.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).
					message("Unable to send message Please try again").status(false).build();
		}
		return response;
	}

	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBase getAllNotifications(@RequestParam(required = false, name = "pageNumber", defaultValue = "1") int page,
			@RequestParam(required = false, name = "pageSize", defaultValue = "" + AppConstants.DEFAULT_SIZE) int size,
			@RequestParam(required = false, name = "txnId") String txntId,
			@RequestParam(required = false, name = "orderBy") String orderBy) {
		orderBy = orderBy == null ? "" : orderBy;
		Map<String, String> orderByMap = Arrays.asList(orderBy.split(",")).stream().filter(x -> x.strip().length() > 0)
				.collect(Collectors.toMap(x -> x.split("-")[0], x -> x.split("-")[1]));
		//TODO: Query underlying service for getall notificatioins
		return null;
	}

	@GetMapping(path = "/{txnId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseBase getById(@PathVariable String txnId) {
		
		//TODO: Query underlying service for getByID notificatioins
		return null;
	}


}
