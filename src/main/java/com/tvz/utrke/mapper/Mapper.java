package com.tvz.utrke.mapper;

abstract class Mapper <Class, DtoClass> {

    abstract public DtoClass map(Class clazz);
}
