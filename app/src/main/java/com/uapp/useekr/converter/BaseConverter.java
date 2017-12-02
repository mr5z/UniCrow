package com.uapp.useekr.converter;

/**
 * Created by root on 12/2/17.
 */

public interface BaseConverter<TSource, TDestination> {

    TSource fromValue(TDestination value);

    TDestination toValue(TSource value);
}
