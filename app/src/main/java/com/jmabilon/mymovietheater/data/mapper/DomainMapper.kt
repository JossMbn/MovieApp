package com.jmabilon.mymovietheater.data.mapper

interface DomainMapper<T : Any, Domain : Any> {

    fun toDomain(value: T): Domain
}