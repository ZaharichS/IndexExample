package org.example;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CashRegister {

    private String numflight; // номер рейса

    private String trip; // маршрут

    private String stopoverPoints; // пункты промежуточной посадки

    private String timeFlight; // время отправления

    private String dayOfflight; // дни отправления

    private String numAvblSeatsFl; // Количество свободных мест на каждом рейсе
}
