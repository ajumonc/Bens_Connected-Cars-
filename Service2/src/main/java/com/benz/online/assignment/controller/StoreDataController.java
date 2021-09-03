package com.benz.online.assignment.controller;

import com.benz.online.assignment.services.ReadDataService;
import com.benz.online.assignment.services.StoreOrUpdateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

/**
 * Read data from the files(CSV/XML)
 */
@RestController
@ComponentScan
public class StoreDataController {
	@Autowired
	private StoreOrUpdateDataService storeOrUpdateDataService;
	@Autowired
	private ReadDataService readDataService;
	@RequestMapping(method = RequestMethod.GET, value = "/service2/read-data")
	public byte[] readData()  throws  Exception{
		if(readDataService.readFile().length>0)
			return readDataService.readFile();
		else
			return new byte[0];
	}
}

