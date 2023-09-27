package ru.mts.tariff.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;

import java.awt.*;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mts.tariff.dao.TariffRepository;
import ru.mts.tariff.model.Tariff;
import ru.mts.tariff.services.ParsingHTMLTariffPlane;


@Route("tariffPlane")
public class TariffView extends VerticalLayout {
    private final TariffRepository tariffRepository;
    private final ParsingHTMLTariffPlane parsingHTML;
    private Grid<Tariff> grid = new Grid<>(Tariff.class);

    @Autowired
    public TariffView(TariffRepository tariffRepository, ParsingHTMLTariffPlane parsingHTML) throws IOException {
        this.tariffRepository = tariffRepository;
        this.parsingHTML = parsingHTML;
        dataInitialization();

        var tariffIter = tariffRepository.findAll();
        var ListTariff = StreamSupport.stream(tariffIter.spliterator(), false)
                .collect(Collectors.toList());
        grid.setItems(ListTariff);

    // Кнопка "Парсить"
    com.vaadin.flow.component.button.Button parseButton = new Button("Parsing");
        parseButton.addClickListener(e -> {
            try {
                dataInitialization();
                var updatedTariffs = tariffRepository.findAll();
                grid.setItems(StreamSupport.stream(updatedTariffs.spliterator(), false)
                        .collect(Collectors.toList()));
                getUI().ifPresent(ui -> ui.getPage().reload()); // Перезагрузка страницы
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        add(grid, parseButton);
    }

    private void dataInitialization() throws IOException {
        parsingHTML.parsingFile("https://moskva.mts.ru/personal/mobilnaya-svyaz/tarifi/vse-tarifi/mobile");
        parsingHTML.parsingFile("https://moskva.mts.ru/personal/mobilnaya-svyaz/tarifi/vse-tarifi/mobile-tv-inet");
        parsingHTML.parsingFile("https://moskva.mts.ru/personal/mobilnaya-svyaz/tarifi/vse-tarifi/watches-and-modems");
        parsingHTML.parsingFile("https://moskva.mts.ru/personal/mobilnaya-svyaz/tarifi/vse-tarifi/telefon");
    }
}

