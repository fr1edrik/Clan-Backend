package com.example.ClanBackend.interfaces

interface ICommand {
    fun execute() : List<String>
}