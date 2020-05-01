package com.example.ClanBackend.interfaces

interface ICommand<T> {
    fun execute(args: List<String> = emptyList()):T
}