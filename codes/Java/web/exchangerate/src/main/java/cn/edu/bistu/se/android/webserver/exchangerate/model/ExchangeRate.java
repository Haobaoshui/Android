package cn.edu.bistu.se.android.webserver.exchangerate.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ExchangeRate {
    private String from;/*原币种*/
    private String to;/*目标币种*/
    private String rate;/*汇率结果 */
    private String update;/*数据更新时间*/


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
}
