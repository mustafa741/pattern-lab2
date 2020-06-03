package com.mustafa.patterns.pojo;

public interface Serializer<T> {

    String serialize(T object);

}
