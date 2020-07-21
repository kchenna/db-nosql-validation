package com.kc.batch.configuration;

import org.springframework.batch.item.ItemProcessor;

import com.kc.batch.dao.entity.Voltage;

import java.math.BigDecimal;

public class VoltageProcessor implements ItemProcessor<Voltage, Voltage>{

    @Override
    public Voltage process(final Voltage voltage) {
    	System.out.println("voltage "+voltage.getVolt());
        final BigDecimal volt = voltage.getVolt();
        final double time = voltage.getTime();

        final Voltage processedVoltage = new Voltage();
        processedVoltage.setVolt(volt);
        processedVoltage.setTime(time);
        return processedVoltage;
    }
}
