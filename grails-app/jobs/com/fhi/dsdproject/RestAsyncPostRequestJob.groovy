package com.fhi.dsdproject

/**
 *  Periodicky vykonávaný "job" ktorý sa stará o to aby
 *  sa znovu skúšali zaslať neúspešné požiadavky na iné uzly
 *
 * **/


public class RestAsyncPostRequestJob {

	def restAsyncPostRequestService

	static triggers = {
		simple repeatInterval: 1000 * 15  // kazdych 15 000 ms, cize 15 sekund
	}

	void execute() {
		log.info("Starting RestAsyncPostRequestJob")
		restAsyncPostRequestService.sendRequests()
	}

}
