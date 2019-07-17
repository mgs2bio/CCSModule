package com.in28minutes.springboot.microservice.example.currencyconversion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.in28minutes.springboot.microservice.example.currencyconversion.aop.LogExecutionTime;
@RefreshScope
@RestController
public class CurrencyConversionController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	@Autowired
	private DiscoveryClient discoveryClient;
	@Value("${user.role:default role}")
    private String role;
	@Value("${common_user.role:default common role}")
    private String commonRole;
	
	

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		List<ServiceInstance> instances = discoveryClient.getInstances("FOREX-SERVICE");

		ServiceInstance serviceInstance = instances.get(0);
		logger.info("serviceInstance:{}" + serviceInstance);
		String baseUrl = serviceInstance.getUri().toString();
		baseUrl = baseUrl + "/currency-exchange/from/{from}/to/{to}";
      
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				baseUrl, CurrencyConversionBean.class,          //Check this link to see how to integrate rest template with Ribbon --https://spring.io/guides/gs/client-side-load-balancing/ 
				uriVariables);

		CurrencyConversionBean response = responseEntity.getBody();

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort(), response.getIp());
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	@CrossOrigin(origins="http://127.0.0.1:5500")
	@LogExecutionTime
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) throws InterruptedException {
		logger.info("from:{}"+ from + " to:{}" + to);
		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

		logger.info("{}", response);

		return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort(), response.getIp());
	}
	
	@GetMapping("/whoami/{username}")
	public String whoami(@PathVariable("username") String userName) {
		return String.format("Hello! You're %s and you'll become a(n) %s and %s ...\n", userName, role, commonRole);
	}

}