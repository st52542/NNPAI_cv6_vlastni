package cz.vlastni.eshop.service;

import cz.vlastni.eshop.entity.Doprava;

import java.util.Map;

public interface INakupService {
    Map<Doprava, Integer> getKosik();

    void checkout(String platba, String doprava, String uzivatel);
}
