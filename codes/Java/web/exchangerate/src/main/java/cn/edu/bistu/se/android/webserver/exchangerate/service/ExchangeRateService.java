package cn.edu.bistu.se.android.webserver.exchangerate.service;


import cn.edu.bistu.se.android.webserver.exchangerate.model.ExchangeRate;
import org.springframework.stereotype.Service;


@Service
public class ExchangeRateService {

    private ExchangeRate getUSD2CNY() {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setFrom("usd");
        exchangeRate.setTo("cny");
        exchangeRate.setRate("6.8264");
        exchangeRate.setUpdate("2020-09-02 16:47:37");
        return exchangeRate;
    }
    private ExchangeRate getCNY2USD() {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setFrom("cny");
        exchangeRate.setTo("usd");
        exchangeRate.setRate("0.1465");
        exchangeRate.setUpdate("2020-09-02 16:47:37");
        return exchangeRate;
    }

    public ExchangeRate getExchangeRate(String scur, String tcur) {
        switch (scur) {
            case "usd":
                if ("cny".equals(tcur)) {
                    return getUSD2CNY();
                }
                return null;

            case "cny":
                if ("usd".equals(tcur)) {
                    return getCNY2USD();
                }
                return null;

            default:
                return null;
        }
    }
}
