package ro.nexttech.internship.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ro.nexttech.internship.domain.CompanyDetails;
import ro.nexttech.internship.exception.CompanyNotFound;
import ro.nexttech.internship.service.CallAnafService;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class CallAnafServiceImpl implements CallAnafService {

    private static final String AnafURL = "https://webservicesp.anaf.ro/PlatitorTvaRest/api/v4/ws/tva";

    @Override
    public CompanyDetails call(Integer CUI) throws RestClientException, CompanyNotFound {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request=generateRequest(CUI);
        var objectResultAsJsonStr =
                restTemplate.postForObject(AnafURL, request, String.class);
        JsonObject jsonObject = new Gson().fromJson(objectResultAsJsonStr, JsonObject.class);
        var result = new Gson().fromJson(jsonObject.get("found").getAsJsonArray(), JsonArray.class);
        var details=result.get(0).getAsJsonObject();
        if(details.get("denumire").getAsString().isEmpty())
            throw new CompanyNotFound("Company "+CUI+" not found !");
        CompanyDetails companyDetails = new CompanyDetails(details.get("cui").getAsString(), details.get("denumire").getAsString(), details.get("adresa").getAsString(), details.get("data").getAsString(), details.get("scpTVA").getAsBoolean());

        return companyDetails;
    }

    @Override
    public HttpEntity<String> generateRequest(Integer CUI) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        var headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        var object = new JsonObject();
        object.addProperty("cui", CUI);
        object.addProperty("data", dateFormat.format(new Date()));
        JsonArray jsonArray=new JsonArray();
        jsonArray.add(object);

        HttpEntity<String> request = new HttpEntity<>(jsonArray.toString(), headers);
        return request;
    }

}
