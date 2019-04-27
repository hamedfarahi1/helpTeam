package com.kharazmi.helpdesk.Service;

public interface EmailService {
	void sendForgetMessage(String to,
                           String subject,
                           String text);
	}