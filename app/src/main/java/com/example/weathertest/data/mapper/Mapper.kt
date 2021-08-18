package com.example.weathertest.data.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}