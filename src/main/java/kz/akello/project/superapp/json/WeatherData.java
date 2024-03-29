package kz.akello.project.superapp.json;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherData {
  private Location location;
  private CurrentWeather current;

  @Getter
  @Setter
  public static class Location {
    private String name;
    private String region;
    private String country;
    private double lat;
    private double lon;
    private String tz_id;
    private long localtime_epoch;
    private String localtime;

  }

  public static class CurrentWeather {
    @JsonProperty("last_updated_epoch")
    private long lastUpdatedEpoch;

    @JsonProperty("last_updated")
    private String lastUpdated;

    @JsonProperty("temp_c")
    private double tempC;

    @JsonProperty("temp_f")
    private double tempF;

    @JsonProperty("is_day")
    private int isDay;

    private Condition condition;

    @JsonProperty("wind_mph")
    private double windMph;

    @JsonProperty("wind_kph")
    private double windKph;

    @JsonProperty("wind_degree")
    private int windDegree;

    @JsonProperty("wind_dir")
    private String windDir;

    @JsonProperty("pressure_mb")
    private double pressureMb;

    @JsonProperty("pressure_in")
    private double pressureIn;

    @JsonProperty("precip_mm")
    private double precipMm;

    @JsonProperty("precip_in")
    private double precipIn;

    private int humidity;
    private int cloud;

    @JsonProperty("feelslike_c")
    private double feelsLikeC;

    @JsonProperty("feelslike_f")
    private double feelsLikeF;

    @JsonProperty("vis_km")
    private double visibilityKm;

    @JsonProperty("vis_miles")
    private double visibilityMiles;

    private double uv;

    @JsonProperty("gust_mph")
    private double gustMph;

    @JsonProperty("gust_kph")
    private double gustKph;
  }

  @Getter
  @Setter
  public static class Condition {
    private String text;
    private String icon;
    private int code;

  }
}

