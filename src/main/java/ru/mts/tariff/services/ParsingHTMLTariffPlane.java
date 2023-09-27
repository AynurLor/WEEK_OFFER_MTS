package ru.mts.tariff.services;

import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mts.tariff.dao.TariffRepository;
import ru.mts.tariff.model.Tariff;

@Service
public class ParsingHTMLTariffPlane implements Parsing<String> {
  private final TariffRepository tariffRepository;

  @Autowired
  public ParsingHTMLTariffPlane(TariffRepository tariffRepository) {
    this.tariffRepository = tariffRepository;
  }

  @Override
  public List<String> parsingFile(String url) throws IOException {
    var document = Jsoup.connect(url).get();

    var plan = url.split("/");

    Elements tariffElements = document.select("div.card.card__wrapper");

    for (var tariffElement : tariffElements) {

      String tariffName = tariffElement.select("div.card-title.card-title__margin").text();
      String tariffDescription = tariffElement.select("div.card-description").text();
      String tariffPrice = tariffElement.select("span.price-text").text();
      Tariff tariff = new Tariff(tariffName, tariffDescription, tariffPrice, plan[plan.length - 1]);
      tariffRepository.save(tariff);
    }

    return null;
  }
}
