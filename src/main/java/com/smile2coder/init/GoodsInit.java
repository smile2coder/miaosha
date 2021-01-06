package com.smile2coder.init;

import com.smile2coder.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author zxt
 * @date 12/28/20
 * @desc
 */
@Component
public class GoodsInit implements ApplicationRunner {

    @Autowired
    private StockService stockService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        stockService.init();
    }
}
