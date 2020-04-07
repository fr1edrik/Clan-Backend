package com.example.ClanBackend.interfaces

interface ICommand<T> {
    fun execute() : T
}