package kz.akello.project.superapp.maper;


import com.fasterxml.jackson.databind.util.JSONPObject;
import kz.akello.project.superapp.json.ExchangeRates;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    public void mapJsonToJavaObject(JSONPObject json, @MappingTarget ExchangeRates exchangeRates);

}
