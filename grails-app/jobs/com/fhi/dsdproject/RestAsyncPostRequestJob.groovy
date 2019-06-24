package com.fhi.dsdproject

public class RestAsyncPostRequestJob {

	def restAsyncPostRequestService

	static triggers = {
		simple repeatInterval: 1000 * 15
	}

	void execute() {
		log.info("Starting RestAsyncPostRequestJob")
		restAsyncPostRequestService.sendRequests()
	}

}
