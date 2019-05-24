package com.fhi.dsdproject

public class RestAsyncPostRequestJob {

	def restAsyncPostRequestService

	static triggers = {
		simple repeatInterval: 1000 * 30
	}

	void execute() {
		log.info("Starting RestAsyncPostRequestJob")
		restAsyncPostRequestService.sendRequests()
	}

}
