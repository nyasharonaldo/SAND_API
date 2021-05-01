package com.example.sand;
import org.knowm.xchange.binance.dto.marketdata.KlineInterval;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private BinanceCandlestickRepository binanceCandlestickRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewCandlestick (
            @RequestParam String pair,
            @RequestParam  String interval,
            @RequestParam  long openTime,
            @RequestParam  BigDecimal open,
            @RequestParam  BigDecimal high,
            @RequestParam  BigDecimal low,
            @RequestParam  BigDecimal close,
            @RequestParam  BigDecimal volume,
            @RequestParam  long closeTime,
            @RequestParam  BigDecimal quoteAssetVolume,
            @RequestParam  long numberOfTrades,
            @RequestParam  BigDecimal takerBuyBaseAssetVolume,
            @RequestParam  BigDecimal takerBuyQuoteAssetVolume
    ) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        BinanceCandlestick c = new BinanceCandlestick(pair,
                interval,
                openTime,
                open,
                high,
                low,
                close,
                volume,
                closeTime,
                quoteAssetVolume,
                numberOfTrades,
                takerBuyBaseAssetVolume,
                takerBuyQuoteAssetVolume
        );
        binanceCandlestickRepository.save(c);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<BinanceCandlestick> getAllCandlesticks() {
        // This returns a JSON or XML with the users
        return binanceCandlestickRepository.findAll();
    }
}
