package com.in28minutes.springboot.microservice.example.forex;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForexController {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired
  private Environment environment;
  
  @Autowired
  private ExchangeValueRepository repository;
  
  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public ExchangeValue retrieveExchangeValue
    (@PathVariable String from, @PathVariable String to){
	  logger.info("received:{}" + from + "and {}" + to);
    ExchangeValue exchangeValue = 
        repository.findByFromAndTo(from, to);
    try {
    	exchangeValue.setIp(InetAddress.getLocalHost().getHostAddress());
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    exchangeValue.setPort(
        Integer.parseInt(environment.getProperty("local.server.port")));
    logger.info("exchangeValue {}" + exchangeValue.toString());
    return exchangeValue;
  }
}