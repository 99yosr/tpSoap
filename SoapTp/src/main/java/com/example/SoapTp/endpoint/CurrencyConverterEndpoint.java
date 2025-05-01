package com.example.SoapTp.endpoint;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import
        org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import
        org.springframework.ws.server.endpoint.annotation.RequestPayload;
import
        org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.example.SoapTp.generatedsources.jaxb.com.example.currency.ConvertCurrencyRequest;
import com.example.SoapTp.generatedsources.jaxb.com.example.currency.ConvertCurrencyResponse;
import org.springframework.ws.server.endpoint.annotation.*;
@Endpoint
public class CurrencyConverterEndpoint {
    private static final String NAMESPACE_URI =
            "http://example.com/currency";
    @PayloadRoot(namespace = NAMESPACE_URI, localPart =
            "ConvertCurrencyRequest")
    @ResponsePayload
    public ConvertCurrencyResponse convertCurrency(@RequestPayload
                                                   ConvertCurrencyRequest request) {
        ConvertCurrencyResponse response = new
                ConvertCurrencyResponse();
// Taux de conversion fictif de 0,9 pour la démonstration
        double conversionRate = 0.9;
        double convertedAmount = request.getAmount() *
                conversionRate;
        response.setConvertedAmount(convertedAmount);
        return response;
    }
}
