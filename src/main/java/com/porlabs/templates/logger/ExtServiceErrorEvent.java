package com.porlabs.templates.logger;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

@Getter
@ToString
public class ExtServiceErrorEvent  extends  ErrorEvent{
    private String response;

    protected ExtServiceErrorEvent(Throwable th){
        super(th);
    }

    public static ExtServiceErrorEvent build(RestClientException th) {
        ExtServiceErrorEvent ev = new ExtServiceErrorEvent(th);
        try {
            ev.response = ((HttpClientErrorException)th).getResponseBodyAsString();
        }catch (Exception e) {/** Exception suppressed **/ }
        return ev;
    }

}
