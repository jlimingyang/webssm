package com.item1024.Configs;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author 阳十三
 * @email wdful165177@gmail.com
 * @blog http://www.item1024.com
 * @date 2017/9/4
 */

public class ChooseDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        // TODO Auto-generated method stub
        return HandleDataSource.getDataSource();
    }
}
