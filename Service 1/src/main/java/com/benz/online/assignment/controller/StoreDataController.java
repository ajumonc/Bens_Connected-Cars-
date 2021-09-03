package com.benz.online.assignment.controller;

import com.benz.online.assignment.constants.DataFormat;
import com.benz.online.assignment.model.StoreDataDAO;
import com.benz.online.assignment.utils.RabbitMQSender;
import com.benz.online.assignment.services.ReadDataService;
import com.benz.online.assignment.services.StoreDataService;
import com.benz.online.assignment.services.UpdateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@ComponentScan
public class StoreDataController {
	@Autowired
	RabbitMQSender rabbitMQSender;
	@Autowired
	private StoreDataService storeDataService;
	@Autowired
	private UpdateDataService updateDataService;
	@Autowired
	private ReadDataService readDataService;

	@RequestMapping(method = RequestMethod.PUT, value = "/service1/store-data",consumes = {MediaType.APPLICATION_JSON_VALUE})
	public String storeData(@RequestBody String requestBody, @RequestParam DataFormat fileType)  throws  Exception{
		if(storeDataService.storeData(requestBody,fileType))
			return "Store data Successfully";
		else
			return "Failed to update the data, Please try after sometime";
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/service1/update-data")
	public String updateData(@RequestBody String requestBody, @RequestParam DataFormat fileType) throws Exception {
		if(updateDataService.updateData(requestBody,fileType))
			return "Updated the data successfully";
		else
			return "Failed to update the data, Please try after sometime";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/service1/read-data")
	public String readData()  throws  Exception{
		String readData=readDataService.readFile();
		if(!readData.isEmpty())
			return readData;
		else
			return "Failed to read the data .Please try after sometime";
	}

}

