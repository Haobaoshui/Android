package cn.edu.bistu.se.android.webserver.exchangerate.controller;


import cn.edu.bistu.se.android.webserver.exchangerate.model.ExchangeRate;
import cn.edu.bistu.se.android.webserver.exchangerate.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exchangerate/v1")
public class ExchangeRateController {

    ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @RequestMapping(value = "rate", method = RequestMethod.GET)
    public ExchangeRate getExchangeRate(@RequestParam(value = "scur")String scur, @RequestParam(value = "tcur")String tcur) {

        return exchangeRateService.getExchangeRate(scur,tcur);


    }
}
