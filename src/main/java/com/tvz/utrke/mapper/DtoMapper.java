package com.tvz.utrke.mapper;

abstract class DtoMapper<Class, DtoClass> {

    abstract public DtoClass map(Class clazz);
}
